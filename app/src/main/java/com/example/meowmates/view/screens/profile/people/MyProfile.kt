package com.example.meowmates.view.screens.profile.people


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.meowmates.R
import com.example.meowmates.model.database.Users
import com.example.meowmates.view.components.CatCard
import com.example.meowmates.view.components.CustomTextField
import com.example.meowmates.view.ui.theme.MeowMatesTheme

@Composable
fun MyProfile(navHostController: NavHostController, viewModel: MyProfileViewModel = hiltViewModel()) {

    Box(
        modifier = Modifier
            .background(MeowMatesTheme.colors.background)
    ) {

        LazyColumn(modifier = Modifier.padding( bottom = 100.dp, start = 16.dp, end = 16.dp)) {

            item {
                LaunchedEffect(Unit) {
                    viewModel.GetUser()
                }
                ProfileImage(viewModel)
            }
            item {
                CustomTextField(
                    "Фамилия", viewModel.surname, { viewModel.surname.value = it }
                )
                CustomTextField(
                    "Имя", viewModel.name, { viewModel.name.value = it }
                )
                CustomTextField(
                    "Отчество", viewModel.patro, { viewModel.patro.value = it }
                )
                CustomTextField(
                    "Телефон", viewModel.telephone, { viewModel.telephone.value = it }
                )
                CustomTextField(
                        "Дата рождения", viewModel.birthdate, { viewModel.birthdate.value = it }
                )
            }

            item {
                Button(
                    onClick={ viewModel.saveChanges(navHostController) },
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
fun ProfileImage(viewModel: MyProfileViewModel){
    viewModel.GetUser()
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
        Image(
            painter = painterResource(id = R.drawable.user_picture_defult_black),
            contentDescription = "Изображение пользователя",
            modifier = Modifier.padding(25.dp).fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )

    }

}