package com.example.meowmates.view.screens.profile.cat

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.meowmates.R
import com.example.meowmates.view.components.CatCard
import com.example.meowmates.view.components.CustomTextField
import com.example.meowmates.view.screens.profile.people.MyProfileViewModel
import com.example.meowmates.view.screens.profile.people.ProfileImage
import com.example.meowmates.view.ui.theme.MeowMatesTheme


@Composable
fun CatProfile(navHostController: NavHostController, IdCat : Int, viewModel: CatProfileViewModel = hiltViewModel()) {
    Log.d("CatProfile", "Получили кота: "+ IdCat)
    viewModel.currentIdCat.value = IdCat

    Column (
        modifier = Modifier
            .background(MeowMatesTheme.colors.background)
            .fillMaxSize()
    ) {

        LazyColumn(
            modifier = Modifier.padding(
                bottom = 100.dp,
                start = 16.dp,
                end = 16.dp
            )
        ) {

            item {
                LaunchedEffect(Unit) {
                    viewModel.GetCat()
                }
                Box(
                    modifier = Modifier.fillMaxWidth().padding(top = 20.dp, bottom = 15.dp),
                    contentAlignment = Alignment.BottomCenter
                ){

                    ProfileImage(viewModel)
                }
            }
            item {
                CustomTextField(
                    "Имя", viewModel.name, { viewModel.name.value = it }
                )
                CustomTextField(
                    "Пол", viewModel.gender, { viewModel.gender.value = it }
                )
                CustomTextField(
                    "Возраст", viewModel.age, { viewModel.age.value = it }
                )
                CustomTextField(
                    "Порода", viewModel.breed, { viewModel.breed.value = it }
                )
                CustomTextField(
                    "Вес", viewModel.weight, { viewModel.weight.value = it }
                )
                CustomTextField(
                    "Описание", viewModel.descriptor, { viewModel.descriptor.value = it }
                )
            }
            item {
                Button(
                    onClick={ viewModel.SaveChangeCat(navHostController) },
                    modifier=Modifier
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                        .background(MeowMatesTheme.colors.container, shape = RoundedCornerShape(10.dp))
                        .fillMaxWidth() // Занять всю ширину, как текстовые поля
                        .height(50.dp), // Высота кнопки, чтобы соответствовать текстовым полям
                    colors= ButtonDefaults.run { buttonColors(MeowMatesTheme.colors.button) },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text="Сохранить", color= MeowMatesTheme.colors.inversionText, style = MeowMatesTheme.fonts.textWatermark)
                }
            }
        }
    }
}
@Composable
fun ProfileImage(viewModel: CatProfileViewModel){
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(viewModel.image_url.value)
            .size(Size.ORIGINAL)
            .build()
    ).state

    if (imageState is AsyncImagePainter.State.Success) {
        Image(
            painter = imageState.painter,
            contentDescription = "Изображение пользователя",
            modifier = Modifier.padding(25.dp).fillMaxWidth().clip(CircleShape),
            contentScale = ContentScale.Crop,
        )
    }
    if(imageState is AsyncImagePainter.State.Error) {

        Box(
            modifier = Modifier.size(335.dp).fillMaxWidth().clip(
                RoundedCornerShape(15.dp)).background(MeowMatesTheme.colors.container ),
        ) {
            Image(
                painter = painterResource(id = R.drawable.cat_icon_default),
                contentDescription = "Изображение кота",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }

    }

}