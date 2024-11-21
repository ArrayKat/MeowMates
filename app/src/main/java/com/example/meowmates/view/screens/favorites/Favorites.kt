package com.example.meowmates.view.screens.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.meowmates.view.components.CatCard
import com.example.meowmates.view.ui.theme.MeowMatesTheme

@Composable
fun Favorites (navHostController: NavHostController, viewModel: FavoritesViewModel = hiltViewModel()) {

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