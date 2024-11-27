package com.example.meowmates.view.screens.message.ListChats

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.input.TextFieldCharSequence
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.meowmates.view.MainViewModel
import com.example.meowmates.view.screens.home.HomeViewModel
import com.example.meowmates.view.ui.theme.MeowMatesTheme

@Composable
fun Chat(navHostController: NavHostController, viewModel: MainViewModel = hiltViewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp)
            .background(MeowMatesTheme.colors.container, shape = RoundedCornerShape(16.dp)) // Закругление углов
            .clip(RoundedCornerShape(16.dp)) // Применение закругления к Box

    ) {
        Row{
            // Загрузка изображения кота из Supabase с помощью Coil
            Text(
                text = "Сообющения пустые...",
                color = Color(0xFFE0C6B5),
                style = MeowMatesTheme.fonts.textWatermark
            )
        }
    }
}