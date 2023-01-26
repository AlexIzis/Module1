package com.example.module1.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.module1.R
import com.example.module1.activity.CategoriesActivity

class ComposeLoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Preview
@Composable
fun MainScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        CustomToolbar()
        Text(
            text = stringResource(id = R.string.string_with_social),
            modifier = Modifier.padding(start = 56.dp, end = 56.dp, top = 40.dp),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
        )
        Socials()
        Text(
            text = stringResource(id = R.string.string_with_app),
            modifier = Modifier.padding(start = 56.dp, end = 56.dp, top = 40.dp),
            fontSize = 14.sp
        )
        UserTextInput()
    }
}

@Composable
fun CustomToolbar() {
    Row(
        modifier = Modifier
            .background(color = colorResource(id = R.color.leaf))
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = "img",
            tint = Color.White,
            modifier = Modifier.padding(start = 14.dp, top = 14.dp, bottom = 14.dp),
        )

        Text(
            text = stringResource(id = R.string.login),
            color = Color.White,
            fontSize = 21.sp,
            fontFamily = FontFamily(Font(R.font.officinasansextraboldscc)),
        )
    }
}

@Composable
fun Socials() {
    Row(
        modifier = Modifier
            .padding(start = 60.dp, end = 60.dp, top = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(painter = painterResource(id = R.drawable.ic_vk), contentDescription = "img")
        Image(painter = painterResource(id = R.drawable.ic_fb), contentDescription = "img")
        Image(painter = painterResource(id = R.drawable.ic_ok), contentDescription = "img")
    }
}

@Composable
fun UserTextInput() {
    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val buttonEnable = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = email.value,
            modifier = Modifier
                .padding(top = 32.dp)
                .background(color = Color.White),
            label = { Text(text = stringResource(id = R.string.e_mail)) },
            onValueChange = {
                email.value = it
            })
        TextField(
            value = password.value,
            modifier = Modifier
                .padding(top = 10.dp)
                .background(color = Color.White),
            label = { Text(text = stringResource(id = R.string.password)) },
            onValueChange = {
                password.value = it
            })
        Button(
            onClick = {
                context.startActivity(Intent(context, CategoriesActivity::class.java))
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.leaf)),
            enabled = (email.value.length >= 5) && (password.value.length >= 5)
        ) {
            Text(
                text = stringResource(id = R.string.button_login),
                color = Color.White
            )
        }
    }
}
