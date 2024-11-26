package com.example.meowmates.view.screens.message.Chat

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.meowmates.R
import com.example.meowmates.model.database.Users
import com.example.meowmates.view.components.CustomTextField
import com.example.meowmates.view.ui.theme.MeowMatesTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds




@Composable
fun Chat (navHostController: NavHostController, sender : String, recipient:String, viewModel: ChatViewModel = hiltViewModel()) {
    var text by remember { mutableStateOf("")}
    val messageList by viewModel.messageList.collectAsState()
    //val messageList by viewModel.messageList.collectAsState(initial = emptyList())
    val context = LocalContext.current
    var sendUsr = Users()
    var recipientUsr = Users()

    LaunchedEffect(Unit) {
        sendUsr = viewModel.GetUser(sender)
        recipientUsr = viewModel.GetUser(recipient)
        while (true){
            delay(5.seconds)
            viewModel.GetMessage(sendUsr, recipientUsr)
        }
    }
    LaunchedEffect(viewModel.isError) {
        viewModel.isError.collect{ error ->
            error?.let {
                delay(2.seconds)
                if(error==true) {
                    Toast.makeText(context, "Проверьте подключение к Интернету", Toast.LENGTH_LONG).show()
                }
                else{
                    text = ""
                }
            }

        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MeowMatesTheme.colors.background)
    ) {
        Spacer(Modifier.padding(vertical = 30.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .weight(3f)
                .clip(RoundedCornerShape(20.dp))
                .background(MeowMatesTheme.colors.background)

        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(MeowMatesTheme.colors.container)
            ) {


                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(4f)
                        .padding(start = 10.dp)
                ) {
                    Spacer(Modifier.padding(vertical = 10.dp))

                    Text(
                        text = viewModel.RecipientFIO,
                        textAlign = TextAlign.Center,
                        style = MeowMatesTheme.fonts.textWatermark,
                        color = MeowMatesTheme.colors.text
                    )

                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(
                    messageList
                ) { message ->

                    if (message == messageList[0]) {
                        Spacer(Modifier.padding(vertical = 15.dp))
                    }
                    if (message.sender_id == sendUsr.id) {
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Spacer(modifier = Modifier.weight(1f))
                            Box(
                                contentAlignment = Alignment.Center,

                                modifier = Modifier
                                    .weight(2f)
                                    .clip(
                                        RoundedCornerShape(
                                            topStart = 20.dp,
                                            bottomStart = 20.dp
                                        )
                                    )
                                    .background(MeowMatesTheme.colors.container)
                                    .padding(vertical = 20.dp)

                            ) {
                                Text(
                                    text = message.text,
                                    style = MeowMatesTheme.fonts.defaultText,
                                    color = MeowMatesTheme.colors.text,
                                    textAlign = TextAlign.Center
                                )
                            }

                        }
                        Spacer(modifier = Modifier.padding(vertical = 10.dp))

                    } else {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .weight(2f)
                                    .clip(RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp))
                                    .background(MeowMatesTheme.colors.container)
                                    .padding(vertical = 20.dp)

                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp),
                                    text = message.text,
                                    style = MeowMatesTheme.fonts.defaultText,
                                    color = MeowMatesTheme.colors.text,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))

                        }
                        Spacer(modifier = Modifier.padding(vertical = 10.dp))

                    }

                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 30.dp, end = 30.dp, bottom = 50.dp, top = 20.dp)
        ) {
            CustomTextField(
                "Введите сообщение...", viewModel.textInput, {viewModel.textInput.value = it }
            )

            IconButton(
                onClick = {
                    viewModel.SendMessage(sendUsr)

                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(vertical = 20.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(MeowMatesTheme.colors.background)
            ) {
                Icon(
                    modifier = Modifier
                        .width(60.dp),
                    painter = painterResource(id = R.drawable.send_message),
                    contentDescription = ""
                )
            }
        }
    }
}