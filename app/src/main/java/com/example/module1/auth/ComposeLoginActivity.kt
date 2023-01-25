package com.example.module1.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    Row() {

    }
}
