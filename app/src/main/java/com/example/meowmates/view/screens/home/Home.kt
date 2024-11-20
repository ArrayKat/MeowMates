package com.example.meowmates.view.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
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
import coil.compose.rememberImagePainter
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.meowmates.R
import com.example.meowmates.model.Cats
import com.example.meowmates.view.ui.theme.MeowMatesTheme
import dagger.hilt.android.lifecycle.HiltViewModel


//@Composable
//fun Home(navHostController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
//    val scrollState = rememberScrollState()
//    val cats = viewModel.catsList
//
//    Box(
//        modifier = Modifier
//            .background(MeowMatesTheme.colors.background)
//            .fillMaxSize()
//    ) {
//        Text(
//            text = "Home Screen",
//            fontSize = 20.sp,
//            color = MeowMatesTheme.colors.text,
//            modifier = Modifier.padding(20.dp)
//        )
//
//
//        Column(modifier = Modifier.padding(16.dp)) {
//            val cats = viewModel.catsList.value
//            for (cat in cats) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 8.dp)
//                        .background(MeowMatesTheme.colors.container),
//
//                ) {
//                    Row(modifier = Modifier.padding(16.dp)) {
//                        //здесь должна быть картинка
////                        try{
////                            val painter = rememberImagePainter(cat.image_url)
////                        }
////                        catch(e:Exception){
////                            Log.d("HomeError","что то не так с картинкой")
////                        }
//
//
//                        val imageState = rememberAsyncImagePainter(
//                            model = ImageRequest.Builder(LocalContext.current).data(cat.image_url)
//                                .size(Size.ORIGINAL).build()
//                        ).state
//                        if (imageState is AsyncImagePainter.State.Error) {
//                            Box(
//                                modifier = Modifier
//                                    .fillMaxHeight()
//                                    .width(200.dp),
//                                contentAlignment = Alignment.Center
//                            ) {
//                                CircularProgressIndicator()
//                            }
//                        }
//
//                        if (imageState is AsyncImagePainter.State.Success) {
//                            Image(
//                                modifier = Modifier
//                                    .fillMaxHeight()
//                                    .width(200.dp),
//                                painter = imageState.painter,
//                                contentDescription = "",
//                                contentScale = ContentScale.Crop
//                            )
//                        }
//                        Spacer(modifier = Modifier.width(16.dp))
//                        Column {
//                            Text(
//                                text = cat.name_cat.ifEmpty { "Неизвестный котик" },
//                                fontSize = 16.sp,
//                                color = MeowMatesTheme.colors.text,
//                                style = MeowMatesTheme.fonts.textWatermark,
//                                modifier = Modifier.padding(bottom = 4.dp)
//                            )
//
//                            // Отображение других характеристик кота
//                            Text(
//                                text = "Возраст: ${cat.age} лет",
//                                fontSize = 14.sp,
//                                color = MeowMatesTheme.colors.text,
//                                style = MeowMatesTheme.fonts.defaultText,
//                                modifier = Modifier.padding(bottom = 4.dp)
//                            )
//
//                            Text(
//                                text = "Порода: ${cat.breed_id}", // Замените на реальное название породы, если доступно
//                                fontSize = 14.sp,
//                                color = MeowMatesTheme.colors.text,
//                                style = MeowMatesTheme.fonts.defaultText,
//                                modifier = Modifier.padding(bottom = 4.dp)
//                            )
//
//                            Text(
//                                text = "Вес: ${cat.weight} кг",
//                                fontSize = 14.sp,
//                                color = MeowMatesTheme.colors.text,
//                                style = MeowMatesTheme.fonts.defaultText,
//                                modifier = Modifier.padding(bottom = 4.dp)
//                            )
//
//                        }
//                        // Иконка "Избранное"
//                        Icon(
//                            imageVector = ImageVector.vectorResource(id = R.drawable.favorites_icon),
//                            contentDescription = "Добавить в избранное",
//                            modifier = Modifier
//                                .size(20.dp)
//                                .clickable { /* Обработка клика */ },
//                            tint = MeowMatesTheme.colors.activIcon,
//                        )
//
//                    }
//
//                }
//            }
//        }
//    }
//}

@Composable
fun Home(navHostController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    val cats = viewModel.catsList.value

    Box(
        modifier = Modifier
            .background(MeowMatesTheme.colors.background)
            .fillMaxSize()
    ) {
        Text(
            text = "Home Screen",
            fontSize = 20.sp,
            color = MeowMatesTheme.colors.text,
            modifier = Modifier.padding(20.dp)
        )

        LazyColumn(modifier = Modifier.padding( bottom = 100.dp, start = 16.dp, end = 16.dp, top = 50.dp )) {
            items(cats) { cat ->
                CatCard(cat)
            }
        }
    }
}

@Composable
fun CatCard(cat: Cats) {
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
                is AsyncImagePainter.State.Error -> {
                    Box(
                        modifier = Modifier.size(100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is AsyncImagePainter.State.Success -> {
                    Image(
                        painter = imageState,
                        contentDescription = "Изображение кота",
                        modifier = Modifier.size(130.dp).clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop,
                    )
                }
                else -> {
                    // В этом состоянии можно добавить индикатор загрузки или пустое место.
                    Box(modifier = Modifier.size(100.dp))
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
                    text = "Порода: ${cat.breed_id}",
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

            // Иконка "Избранное"
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.favorites_icon),
                contentDescription = "Добавить в избранное",
                modifier = Modifier.size(40.dp).padding(end = 16.dp, top = 16.dp).clickable { /* Обработка клика */ },
                tint = MeowMatesTheme.colors.activIcon,
            )
        }
    }
}
