package com.example.meowmates.view.screens.profile.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.meowmates.R
import com.example.meowmates.view.components.CatCard
import com.example.meowmates.view.components.MyCatCard
import com.example.meowmates.view.navigation.NavigationRoutes
import com.example.meowmates.view.ui.theme.MeowMatesTheme


@Composable
fun MainProfile(controller: NavHostController, viewModel: MainProfileViewModel = hiltViewModel()) {
    LazyColumn(
        modifier = Modifier
            .background(MeowMatesTheme.colors.background)
            .fillMaxSize()
            .padding(bottom = 100.dp, start = 16.dp, end = 16.dp)
    ) {
        item {
            viewModel.GetData() // Вызываем загрузку данных
            viewModel.User.value?.let { user ->
                // Загрузка изображения кота из Supabase с помощью Coil
                val imageState = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(user.image_url) // Предполагается, что у вас есть поле image_url в модели User
                        .size(Size.ORIGINAL)
                        .build()
                )

                // Проверка состояния загрузки изображения
                when (imageState.state) {
                    is AsyncImagePainter.State.Success -> {
                        Image(
                            painter = imageState,
                            contentDescription = "Изображение пользователя",
                            modifier = Modifier.padding(25.dp).fillMaxWidth().clip(CircleShape),
                            contentScale = ContentScale.Crop,
                        )
                    }
                    else -> {
                        Box(
                            modifier = Modifier.size(100.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                Text(
                    text = "${user.surname} ${user.name} ${user.patronymic}",
                    fontSize = 26.sp,
                    style = MeowMatesTheme.fonts.textWatermark,
                    color = MeowMatesTheme.colors.text,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth().padding(bottom = 10.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = user.telephone.toString(),
                    fontSize = 18.sp,
                    color = MeowMatesTheme.colors.text,
                    modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth(),
                    style = MeowMatesTheme.fonts.defaultText,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = user.birthday.toString(),
                    fontSize = 18.sp,
                    color = MeowMatesTheme.colors.text,
                    modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth(),
                    style = MeowMatesTheme.fonts.defaultText,
                    textAlign = TextAlign.Center
                )
            } ?: run {
                // Отображаем сообщение о загрузке или ошибке
                Text(
                    text = "Загрузка данных...",
                    fontSize = 20.sp,
                    color = MeowMatesTheme.colors.text,
                    modifier = Modifier.padding(20.dp)
                )
            }
        }

        item {
            Button(
                onClick={ viewModel.navigateToMyProfile(controller) },
                modifier=Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth() // Занять всю ширину
                    .height(60.dp), // Высота кнопки
                colors= ButtonDefaults.buttonColors(MeowMatesTheme.colors.container),
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically // Центрируем элементы по вертикали
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.edit_icon),
                        contentDescription = "Изменить профиль",
                        modifier = Modifier.size(24.dp), // Уменьшаем размер иконки для лучшего отображения
                        tint = MeowMatesTheme.colors.activIcon,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Изменить профиль",
                        color=MeowMatesTheme.colors.text,
                        style=MeowMatesTheme.fonts.textWatermark,
                        fontSize=20.sp
                    )
                }
            }
        }

        item {
            Button(
                onClick={ viewModel.navigateToLogIn(controller) },
                modifier=Modifier
                    .padding(vertical=10.dp)
                    .fillMaxWidth() // Занять всю ширину
                    .height(60.dp), // Высота кнопки
                colors= ButtonDefaults.buttonColors(MeowMatesTheme.colors.container),
                shape=RoundedCornerShape(10.dp)
            ) {
                Row(
                    verticalAlignment=Alignment.CenterVertically // Центрируем элементы по вертикали
                ) {
                    Icon(
                        imageVector=ImageVector.vectorResource(id=R.drawable.exit_icon),
                        contentDescription="Выйти",
                        modifier=Modifier.size(24.dp), // Уменьшаем размер иконки для лучшего отображения
                        tint=MeowMatesTheme.colors.activIcon,
                    )
                    Spacer(modifier=Modifier.width(8.dp))
                    Text(
                        text="Выйти из профиля",
                        color=MeowMatesTheme.colors.text,
                        style=MeowMatesTheme.fonts.textWatermark,
                        fontSize=20.sp
                    )
                }
            }
        }

        item {
            Text(
                text="Мои коты:",
                fontSize=26.sp,
                color=MeowMatesTheme.colors.text,
                style=MeowMatesTheme.fonts.textWatermark,
                modifier=Modifier.padding(10.dp)
            )
        }

        items(viewModel.catList.value) { cat ->
            MyCatCard(cat,cat.id,controller) // Отображаем карточки котов пользователя
        }

        item {
            Button(
                onClick={
                    controller.navigate(NavigationRoutes.CATPROFILE + "/${0}")
                },
                modifier=Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth() // Занять всю ширину, как текстовые поля
                    .height(46.dp), // Высота кнопки, чтобы соответствовать текстовым полям
                colors= ButtonDefaults.run { buttonColors(MeowMatesTheme.colors.button) },
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text="+",
                    color=MeowMatesTheme.colors.inversionText,
                    style = MeowMatesTheme.fonts.textWatermark,
                    fontSize = 30.sp,
                )
            }
        }
    }
}