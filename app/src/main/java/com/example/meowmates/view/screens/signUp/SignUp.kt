package com.example.meowmates.view.screens.signUp

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField


import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.meowmates.view.navigation.NavigationRoutes
import com.example.meowmates.view.ui.theme.MeowMatesTheme


@Composable
fun SignUp (navHostController: NavHostController, viewModel: SignUpViewModel = hiltViewModel()) {
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
                text = "Sign Up",
                color = Color(0xFFE0C6B5),
                fontSize = 64.sp,
            )

            // Отступ перед полями ввода
            Spacer(modifier = Modifier.height(43.dp))

                // Поля для ввода данных
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                        .background(MeowMatesTheme.colors.container),
                    placeholder = { Text("Фамилия", color = MeowMatesTheme.colors.inversionText, style = MeowMatesTheme.fonts.textWatermark) },
                    value = viewModel.email.value,
                    onValueChange = {viewModel.email.value = it},
                    shape = RoundedCornerShape(10.dp)
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                        .background(MeowMatesTheme.colors.container),
                    placeholder = { Text("Имя", color = MeowMatesTheme.colors.inversionText, style = MeowMatesTheme.fonts.textWatermark) },
                    value = "",
                    onValueChange = {},
                    shape = RoundedCornerShape(10.dp)
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                        .background(MeowMatesTheme.colors.container),
                    placeholder = { Text("Отчество", color = MeowMatesTheme.colors.inversionText, style = MeowMatesTheme.fonts.textWatermark) },
                    value = "",
                    onValueChange = {},
                    shape = RoundedCornerShape(10.dp)
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                        .background(MeowMatesTheme.colors.container),
                    placeholder = { Text("Телефон", color = MeowMatesTheme.colors.inversionText, style = MeowMatesTheme.fonts.textWatermark) },
                    value = "",
                    onValueChange = {},
                    shape = RoundedCornerShape(10.dp)
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                        .background(MeowMatesTheme.colors.container),
                    placeholder = { Text("Дата рождения", color = MeowMatesTheme.colors.inversionText, style = MeowMatesTheme.fonts.textWatermark) },
                    value = "",
                    onValueChange = {},
                    shape = RoundedCornerShape(10.dp)
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                        .background(MeowMatesTheme.colors.container),
                    placeholder = { Text("Почта", color = MeowMatesTheme.colors.inversionText, style = MeowMatesTheme.fonts.textWatermark) },
                    value = "",
                    onValueChange = {},
                    shape = RoundedCornerShape(10.dp)
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                        .background(MeowMatesTheme.colors.container),
                    placeholder = { Text("Пароль", color = MeowMatesTheme.colors.inversionText, style = MeowMatesTheme.fonts.textWatermark) },
                    value = "",
                    onValueChange = {},
                    shape = RoundedCornerShape(10.dp)
                )

                Button(
                    onClick={ /* Действие при нажатии на кнопку */ },
                    modifier=Modifier
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                        .fillMaxWidth() // Занять всю ширину, как текстовые поля
                        .height(50.dp), // Высота кнопки, чтобы соответствовать текстовым полям
                    colors= ButtonDefaults.run { buttonColors(MeowMatesTheme.colors.button) },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text="Зарегистрироваться", color=MeowMatesTheme.colors.inversionText, style = MeowMatesTheme.fonts.textWatermark)
                }
            }
            Text(
                text = "Есть аккаунт? Войти",
                color = Color(0xFFE0C6B5),
                fontSize = 16.sp,
                modifier = Modifier.clickable { navHostController.navigate(NavigationRoutes.LOGIN) }.padding(top = 20.dp)
            )
        }
    }
}