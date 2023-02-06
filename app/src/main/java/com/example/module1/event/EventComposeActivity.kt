package com.example.module1.event

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.module1.R
import com.example.module1.event.ui.theme.Module1Theme
import com.example.module1.news.UpdateNews

class EventComposeActivity : ComponentActivity() {

    private /*lateinit*/ var news: UpdateNews = UpdateNews(
        0,
        "Спонсоры отремонтируют школу-интернат",
        R.drawable.avatar_1,
        "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
        1699999999002,
        "Благотворительный Фонд «Счастливый Мир»",
        "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
        listOf("+7 (937) 037 37-73", "+7 (937) 016 16-16"),
        "Напишите нам",
        listOf(R.drawable.avatar_2, R.drawable.avatar_3),
        "Перейти на сайт организаии",
        listOf("children")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*news = intent.parcelable("new")!!*/
        setContent {
            Module1Theme {
                MainScreen()
            }
        }
    }

    private inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
        SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
    }

    @Preview
    @Composable
    fun MainScreen() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomToolbar()
            DetailNew()
        }
    }

    @Composable
    fun CustomToolbar() {
        val scroll = rememberScrollState()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.leaf)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "img",
                tint = Color.White,
                modifier = Modifier.padding(10.dp)
            )
            Text(
                text = news.label,
                color = Color.White,
                fontSize = 21.sp,
                maxLines = 1,
                modifier = Modifier
                    .width(300.dp)
                    .horizontalScroll(scroll),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(
                    Font(R.font.officinasansextraboldscc)
                )
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_share),
                contentDescription = "img",
                tint = Color.White,
                modifier = Modifier.padding(10.dp)
            )
        }
    }

    @Composable
    fun DetailNew() {
        Column(
            modifier = Modifier.fillMaxSize().background(color = Color.White)
        ) {

        }
    }
}
