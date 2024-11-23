package com.example.meowmates.view.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.meowmates.R
import com.example.meowmates.model.customModel.CustomCats
import com.example.meowmates.view.navigation.NavigationRoutes
import com.example.meowmates.view.screens.profile.cat.CatProfile
import com.example.meowmates.view.screens.profile.cat.CatProfileViewModel
import com.example.meowmates.view.screens.profile.main.MainProfileViewModel
import com.example.meowmates.view.ui.theme.MeowMatesTheme
import io.ktor.client.plugins.convertLongTimeoutToIntWithInfiniteAsZero


@Composable
fun MyCatCard(cat: CustomCats, idCatDB:Int, controller: NavHostController, viewModel: MainProfileViewModel= hiltViewModel()) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(MeowMatesTheme.colors.container, shape = RoundedCornerShape(16.dp)) // Закругление углов
            .clip(RoundedCornerShape(16.dp)) // Применение закругления к Box

    ) {
        Row{
            // Загрузка изображения кота из Supabase с помощью Coil
            val imageState = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(cat.image_url) // Предполагается, что у вас есть поле image_url в модели Cat
                    .size(Size.ORIGINAL)
                    .build()
            )

            // Проверка состояния загрузки изображения
            when (imageState.state) {
                is AsyncImagePainter.State.Success -> {
                    Image(
                        painter = imageState,
                        contentDescription = "Изображение кота",
                        modifier = Modifier.size(120.dp).clip(RoundedCornerShape(16.dp)),
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

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f).padding(16.dp)) {
                Text(
                    text = cat.name_cat.ifEmpty { "Неизвестный котик" },
                    fontSize = 16.sp,
                    color = MeowMatesTheme.colors.text,
                    style = MeowMatesTheme.fonts.textWatermark,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                // Отображение других характеристик кота
                Text(
                    text = "Возраст: ${cat.age} лет",
                    fontSize = 14.sp,
                    color = MeowMatesTheme.colors.text,
                    style = MeowMatesTheme.fonts.defaultText,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Text(
                    text = "Порода: ${cat.breed_name}",
                    fontSize = 14.sp,
                    color = MeowMatesTheme.colors.text,
                    style = MeowMatesTheme.fonts.defaultText,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Text(
                    text = "Вес: ${cat.weight} кг",
                    fontSize = 14.sp,
                    color = MeowMatesTheme.colors.text,
                    style = MeowMatesTheme.fonts.defaultText,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }

            // Иконка "Изменить"
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.edit_icon),
                contentDescription = "Изменить",
                modifier = Modifier.size(40.dp).padding(end = 16.dp, top = 16.dp).clickable {
                    Log.d("MyCatCard", "ID: ${idCatDB} name: ${cat.name_cat}")
                    controller.navigate(NavigationRoutes.CATPROFILE + "/${cat.id}")
                },
                tint = MeowMatesTheme.colors.activIcon,
            )

        }
    }
}