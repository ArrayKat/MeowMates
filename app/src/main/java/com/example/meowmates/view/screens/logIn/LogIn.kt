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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.meowmates.view.navigation.NavigationRoutes
import com.example.meowmates.view.ui.theme.MeowMatesTheme

@Composable
fun LogIn (navHostController: NavHostController) {
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
                    value = "",
                    onValueChange = {},
                    shape = RoundedCornerShape(10.dp)
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                        .background(MeowMatesTheme.colors.container, shape = RoundedCornerShape(10.dp)),
                    placeholder = { Text(text = "Пароль", color = MeowMatesTheme.colors.text, style = MeowMatesTheme.fonts.textWatermark) },
                    value = "",
                    onValueChange = {},
                    shape = RoundedCornerShape(10.dp)
                )

                Button(
                    onClick={ /* Действие при нажатии на кнопку */ },
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