package com.example.meowmates

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.meowmates.domain.utils.PrefManager
import com.example.meowmates.domain.utils.PrefManager.currentUser
import com.example.meowmates.view.navigation.NavigationRoutes
import com.example.meowmates.view.screens.favorites.Favorites
import com.example.meowmates.view.screens.favorites.FavoritesViewModel
import com.example.meowmates.view.screens.home.Home
import com.example.meowmates.view.screens.home.HomeViewModel
import com.example.meowmates.view.screens.logIn.LogInViewModel
import com.example.meowmates.view.screens.signUp.SignUp
import com.example.meowmates.view.screens.signUp.SignUpViewModel
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var controller: NavHostController
    private lateinit var context: Context
    private lateinit var scenario: ActivityScenario<ComponentActivity>

    @Composable
    fun launch() {
        context = LocalContext.current
        PrefManager.init(context)
        controller = rememberNavController()
    }

    @Before
    fun setup() {
        composeTestRule.setContent {
            launch()
        }
        scenario = ActivityScenario.launch(ComponentActivity::class.java)
    }

    @After
    fun tearDown(){
        scenario.close()
    }

    lateinit var signUpViewModel: SignUpViewModel
    lateinit var logInViewModel: LogInViewModel
    lateinit var homeViewModel: HomeViewModel
    lateinit var favoritesViewModel: FavoritesViewModel

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.meowmates", appContext.packageName)
    }

    @Test
    fun regScreenIsDisplayed() {
        scenario.onActivity {
            it.setContent {
                signUpViewModel = SignUpViewModel()
                SignUp(controller, signUpViewModel)
            }
        }
        composeTestRule.onNodeWithText("Sign Up").isDisplayed()
        composeTestRule.onNodeWithText("Зарегистрироваться").assertIsDisplayed()
    }

    @Test
    fun regScreenButtonIsEnabled() {
        scenario.onActivity {
            it.setContent {
                signUpViewModel = SignUpViewModel()
                TestNavigation(NavigationRoutes.SIGNUP)
            }
        }
        composeTestRule.onNodeWithText("Зарегистрироваться").assertIsEnabled()
    }

    @Test
    fun logInScreenGoToHome() {
        scenario.onActivity {
            it.setContent {
                homeViewModel = HomeViewModel()
                logInViewModel = LogInViewModel()
                logInViewModel.emailUser.value = "user12@mail.ru"
                logInViewModel.passwordUser.value = "user12"
                logInViewModel.LogIn(controller,context)
                TestNavigation(NavigationRoutes.HOME)
            }
        }
        //Произошел переход на другую страницу
        composeTestRule.onNodeWithText("Главная").isDisplayed()
    }

    @Test
    fun menuScreenFavorites() {
        scenario.onActivity {
            it.setContent {
                favoritesViewModel = FavoritesViewModel()
                homeViewModel = HomeViewModel()
                currentUser = "95c28f9a-d823-4219-a7ba-ba1e09fed9ee"
                TestNavigation(NavigationRoutes.FAVORITES)
            }
        }
        composeTestRule.onNodeWithText("Избранное").isDisplayed()
    }

    @Composable
    fun TestNavigation(start: String) {
        NavHost(navController = controller, startDestination  = start) {

            composable(NavigationRoutes.SIGNUP) {
                SignUp(controller, signUpViewModel)
            }

            composable(NavigationRoutes.HOME) {
                Home(controller, homeViewModel)
            }

            composable(NavigationRoutes.FAVORITES) {
                Favorites(controller,favoritesViewModel)
            }
        }
    }

}