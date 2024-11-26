package com.example.meowmates.view.screens.message.ListChats

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
import com.example.meowmates.view.navigation.NavigationRoutes
import com.example.meowmates.view.screens.favorites.FavoritesViewModel
import com.example.meowmates.view.ui.theme.MeowMatesTheme


@Composable
fun ListChats (navHostController: NavHostController, viewModel: ListChatsViewModel = hiltViewModel()) {
    var listChat = viewModel.chatsList.value
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
                    .data(listChat) // Предполагается, что у вас есть поле image_url в модели Cat
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

            }
        }
    }
}