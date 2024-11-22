package com.example.meowmates.view.screens.home

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.meowmates.R
import com.example.meowmates.model.customModel.CustomCats
import com.example.meowmates.model.database.Cats
import com.example.meowmates.view.components.CatCard
import com.example.meowmates.view.ui.theme.MeowMatesTheme


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

