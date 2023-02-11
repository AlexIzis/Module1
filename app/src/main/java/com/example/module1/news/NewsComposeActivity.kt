package com.example.module1.news

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
import com.example.module1.event.EventComposeActivity
import com.example.module1.news.ui.theme.Module1Theme
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

class NewsComposeActivity : ComponentActivity() {

    private var listNews = arrayListOf(
        UpdateNews(
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
        ),
        UpdateNews(
            1,
            "Конкурс по вокальному пению в детском доме №6",
            R.drawable.avatar_2,
            "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
            1699999999002,
            "Благотворительный Фонд «Счастливый Мир»",
            "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
            listOf("+7 (937) 037 37-73", "+7 (937) 016 16-16"),
            "Напишите нам",
            listOf(R.drawable.avatar_1, R.drawable.avatar_3),
            "Перейти на сайт организаии",
            listOf("adults", "elderly")
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Module1Theme {
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
        ) {
            CustomToolbar()
            ListOfNews()
        }
    }

    @Composable
    fun CustomToolbar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.leaf)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(id = R.string.news),
                color = Color.White,
                fontSize = 21.sp,
                fontFamily = FontFamily(
                    Font(R.font.officinasansextraboldscc)
                )
            )
            IconButton(
                onClick = {
                    /*FragmentNavigation().addFragment(
                        fragmentManager = FragmentActivity().supportFragmentManager,
                        fragmentContainer = R.id.fragmentContainerView,
                        fragment = FilterFragment()
                    )*/
                },
                modifier = Modifier.padding(end = 14.dp, top = 8.dp, bottom = 8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = "img",
                    tint = Color.White
                )
            }
        }
    }

    @Composable
    fun ListOfNews() {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.light_grey_two)),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            items(listNews) { new ->
                New(newsUIModel = new)
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    @Composable
    fun New(newsUIModel: UpdateNews) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(top = 12.dp)
                .clickable {
                    val intent = Intent(this, EventComposeActivity::class.java)
                    intent.putExtra("new", newsUIModel)
                    startActivity(intent)
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(
                    id = newsUIModel.img
                ),
                contentDescription = "img",
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )
            Text(
                text = newsUIModel.label,
                fontSize = 23.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 38.dp, end = 38.dp)
            )
            Image(
                painter = painterResource(
                    id = R.drawable.ic_decor
                ),
                contentDescription = "img"
            )
            Text(
                text = newsUIModel.description,
                modifier = Modifier.padding(top = 10.dp, start = 26.dp, end = 26.dp),
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .background(color = colorResource(id = R.color.leaf)),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.ic_calendar_today
                    ),
                    contentDescription = "img",
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                    colorFilter = ColorFilter.tint(color = Color.White)
                )
                Text(
                    text = SimpleDateFormat("MMMM dd, yyyy").format(Date(newsUIModel.time)),
                    modifier = Modifier.padding(top = 4.dp, start = 4.dp),
                    color = Color.White
                )
            }
        }
    }
}

@Parcelize
data class UpdateNews(
    val id: Int,
    val label: String,
    val img: Int,
    val description: String,
    val time: Long,
    val organization: String,
    val address: String,
    val numberList: List<String>,
    val email: String,
    val imgOptionally: List<Int>,
    val site: String,
    val categories: List<String>
) : Parcelable


