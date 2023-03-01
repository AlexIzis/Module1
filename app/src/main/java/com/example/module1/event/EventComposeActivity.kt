package com.example.module1.event

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.material.* // ktlint-disable no-wildcard-imports
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.module1.R
import com.example.module1.news.NewsUIModel
import java.text.SimpleDateFormat
import java.util.*

class EventComposeActivity : ComponentActivity() {

    private lateinit var news: NewsUIModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        news = intent.parcelable(intentKey)!!
        setContent {
            MainScreen()
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
            IconButton(
                onClick = {
                    this@EventComposeActivity.finish()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = contDescImg,
                    tint = Color.White,
                    modifier = Modifier.padding(10.dp)
                )
            }

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
                contentDescription = contDescImg,
                tint = Color.White,
                modifier = Modifier.padding(10.dp)
            )
        }
    }

    @SuppressLint("SimpleDateFormat")
    @Composable
    fun DetailNew() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Text(
                text = news.label,
                color = Color.Black,
                modifier = Modifier.padding(top = 16.dp, start = 20.dp, end = 20.dp),
                fontSize = 21.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, start = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_calendar_today),
                    contentDescription = contDescImg
                )
                Text(
                    text = SimpleDateFormat("MMMM dd, yyyy").format(Date(news.time)),
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            Text(
                text = news.organization,
                color = Color.Black,
                modifier = Modifier.padding(start = 20.dp, top = 12.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, start = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_nav),
                    contentDescription = contDescImg
                )
                Text(
                    text = news.address,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, start = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_phone),
                    contentDescription = contDescImg
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = news.numberList[0],
                        color = Color.Black,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                    Text(
                        text = news.numberList[1],
                        color = Color.Black,
                        modifier = Modifier.padding(start = 10.dp, top = 10.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, start = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_mail),
                    contentDescription = contDescImg
                )
                Text(
                    text = stringResource(id = R.string.any_questions),
                    color = Color.Black,
                    modifier = Modifier.padding(start = 10.dp)
                )
                Text(
                    text = news.email,
                    color = colorResource(id = R.color.leaf),
                    modifier = Modifier.padding(start = 10.dp),
                    textDecoration = TextDecoration.Underline
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 26.dp),
                verticalAlignment = Alignment.Top
            ) {
                Image(
                    painter = painterResource(
                        id = resources.getIdentifier(
                            news.img,
                            defType,
                            applicationContext.packageName
                        )
                    ),
                    contentDescription = contDescImg
                )
                Column(
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Image(
                        painter = painterResource(
                            id = resources.getIdentifier(
                                news.imgOptionally[0],
                                defType,
                                applicationContext.packageName
                            )
                        ),
                        contentDescription = contDescImg
                    )
                    Image(
                        painter = painterResource(
                            id = resources.getIdentifier(
                                news.imgOptionally[1],
                                defType,
                                applicationContext.packageName
                            )
                        ),
                        contentDescription = contDescImg,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
            }
            Text(
                text = news.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 20.dp, end = 20.dp),
                textAlign = TextAlign.Left
            )
            Text(
                text = news.site,
                color = colorResource(id = R.color.leaf),
                modifier = Modifier.padding(start = 20.dp, top = 10.dp, end = 10.dp),
                textDecoration = TextDecoration.Underline
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Gray)
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.avatar_1),
                    contentDescription = contDescImg
                )
                Image(
                    painter = painterResource(id = R.drawable.avatar_2),
                    contentDescription = contDescImg
                )
                Image(
                    painter = painterResource(id = R.drawable.avatar_3),
                    contentDescription = contDescImg
                )
                Text(
                    text = stringResource(id = R.string._102),
                    modifier = Modifier.padding(start = 5.dp)
                )
            }

            val openDialog = remember {
                mutableStateOf(false)
            }
            val sum = remember {
                mutableStateOf("")
            }
            Text(
                text = "Помочь деньгами",
                color = colorResource(id = R.color.leaf),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .clickable {
                        openDialog.value = true
                    },
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )

            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(
                            text = "Спасибо за решение помочь!"
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    text = {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Введите сумму пожертвования")
                            TextField(
                                value = sum.value,
                                onValueChange = {
                                    sum.value = it
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.White,
                                    focusedIndicatorColor = colorResource(id = R.color.leaf),
                                    cursorColor = colorResource(id = R.color.leaf)
                                ),
                                label = {
                                    Text(
                                        text = "Сумма",
                                        color = colorResource(id = R.color.leaf)
                                    )
                                }
                            )
                        }
                    },
                    buttons = {
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Button(
                                onClick = {
                                    openDialog.value = false
                                },
                                colors = ButtonDefaults.buttonColors(Color.White),
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Text(
                                    text = "Отмена",
                                    color = colorResource(id = R.color.leaf)
                                )
                            }
                            Button(
                                onClick = {
                                    Toast.makeText(
                                        this@EventComposeActivity,
                                        "Money",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                                colors = ButtonDefaults.buttonColors(Color.White),
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Text(
                                    text = "Перевести",
                                    color = colorResource(id = R.color.leaf)
                                )
                            }
                        }
                    }
                )
            }
        }
    }

    private fun enableButton(sum: Int): Boolean {
        return (sum > 0) && (sum < 9999999)
    }

    companion object {
        private const val contDescImg = "img"
        private const val defType = "drawable"
        const val intentKey = "new"
    }
}
