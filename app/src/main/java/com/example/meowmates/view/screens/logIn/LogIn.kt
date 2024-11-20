package com.example.meowmates.view.screens.logIn

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.meowmates.R
import com.example.meowmates.view.navigation.NavigationRoutes
import com.example.meowmates.view.ui.theme.MeowMatesTheme
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun LogIn (navHostController: NavHostController, viewModel: LogInViewModel = hiltViewModel()) {
    var passwordVisible = remember { mutableStateOf(false) }
    //var passwordVisibility: Boolean by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MeowMatesTheme.colors.background),

        ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 58.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){


                Text(
                    text = "Log In",
                    color = MeowMatesTheme.colors.title,
                    fontSize = 64.sp,
                    style = MeowMatesTheme.fonts.title
                )

                // Отступ перед полями ввода
                Spacer(modifier = Modifier.height(43.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                        .background(MeowMatesTheme.colors.container, shape = RoundedCornerShape(10.dp)),
                    placeholder = { Text(text = "Почта", color = MeowMatesTheme.colors.text, style = MeowMatesTheme.fonts.textWatermark) },
                    value = viewModel.emailUser.value,
                    onValueChange = { newEmail ->
                        viewModel.emailUser.value= newEmail},
                    shape = RoundedCornerShape(10.dp),
                    textStyle = TextStyle( // Задаем цвет и стиль текста здесь
                        color = MeowMatesTheme.colors.text, // Цвет текста при вводе
                        fontFamily = MeowMatesTheme.fonts.textWatermark.fontFamily, // Шрифт
                        fontWeight = MeowMatesTheme.fonts.textWatermark.fontWeight, // Жирность шрифта
                        fontSize = MeowMatesTheme.fonts.textWatermark.fontSize
                    )
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                        .background(MeowMatesTheme.colors.container, shape = RoundedCornerShape(10.dp)),
                    placeholder = { Text(text = "Пароль", color = MeowMatesTheme.colors.text, style = MeowMatesTheme.fonts.textWatermark) },
                    value = viewModel.passwordUser.value,
                    onValueChange = {newPassw -> viewModel.passwordUser.value = newPassw},
                    shape = RoundedCornerShape(10.dp),
                    textStyle = TextStyle( // Задаем цвет и стиль текста здесь
                        color = MeowMatesTheme.colors.text, // Цвет текста при вводе
                        fontFamily = MeowMatesTheme.fonts.textWatermark.fontFamily, // Шрифт
                        fontWeight = MeowMatesTheme.fonts.textWatermark.fontWeight, // Жирность шрифта
                        fontSize = MeowMatesTheme.fonts.textWatermark.fontSize
                    ),
                    visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val icon = if (passwordVisible.value) {
                            ImageVector.vectorResource(id = R.drawable.eye_open_icon)

                        } else {
                            ImageVector.vectorResource(id = R.drawable.eye_closed_icon)
                        }
                        IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                            Icon(imageVector = icon, contentDescription = null, tint = MeowMatesTheme.colors.activIcon, modifier = Modifier.size(30.dp).padding(end = 5.dp))
                        }
                    }

                )

                Button(
                    onClick={ viewModel.LogIn(navHostController) },
                    modifier=Modifier
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                        .background(MeowMatesTheme.colors.container, shape = RoundedCornerShape(10.dp))
                        .fillMaxWidth() // Занять всю ширину, как текстовые поля
                        .height(50.dp), // Высота кнопки, чтобы соответствовать текстовым полям
                    colors= ButtonDefaults.run { buttonColors(MeowMatesTheme.colors.button) },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text="Войти", color= MeowMatesTheme.colors.inversionText, style = MeowMatesTheme.fonts.textWatermark)
                }
                Text(
                    text = "Нет аккаунта? Зарегистрироваться",
                    color = MeowMatesTheme.colors.text,
                    style = MeowMatesTheme.fonts.defaultText,
                    modifier = Modifier.clickable { navHostController.navigate(NavigationRoutes.SIGNUP) }.padding(top = 20.dp)
                )

            }
        }
    }
}