package com.example.module1.filter

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.module1.R
import com.example.module1.news.NewsComposeActivity

class FilterComposeActivity : ComponentActivity() {

    private val categories = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Preview
    @Composable
    fun MainScreen() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            CustomToolbar()
            ListOfCheckBox()
        }
    }

    @Composable
    fun CustomToolbar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.leaf)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {
                this@FilterComposeActivity.finish()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = contDescImg,
                    tint = Color.White
                )
            }
            Text(
                text = stringResource(id = R.string.filter),
                color = Color.White,
                fontSize = 21.sp,
                fontFamily = FontFamily(Font(R.font.officinasansextraboldscc))
            )
            IconButton(onClick = {
                val intent = Intent(
                    this@FilterComposeActivity,
                    NewsComposeActivity::class.java
                )
                intent.putExtra(filterKey, categories)
                setResult(RESULT_OK, intent)
                this@FilterComposeActivity.finish()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = contDescImg,
                    tint = Color.White
                )
            }
        }
    }

    @Composable
    fun ListOfCheckBox() {
        val checkedStateChildren = remember {
            mutableStateOf(false)
        }
        val checkedStateAdults = remember {
            mutableStateOf(false)
        }
        val checkedStateElderly = remember {
            mutableStateOf(false)
        }
        val checkedStateAnimals = remember {
            mutableStateOf(false)
        }
        val checkedStateEvents = remember {
            mutableStateOf(false)
        }

        val fontSizeCheckBox = 16.sp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checkedStateChildren.value,
                    onCheckedChange = {
                        categories.add("children")
                        checkedStateChildren.value = it
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorResource(id = R.color.leaf)
                    )
                )
                Text(
                    text = "Дети",
                    fontSize = fontSizeCheckBox
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checkedStateAdults.value,
                    onCheckedChange = {
                        categories.add("adults")
                        checkedStateAdults.value = it
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorResource(id = R.color.leaf)
                    )
                )
                Text(
                    text = "Взрослые",
                    fontSize = fontSizeCheckBox
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checkedStateElderly.value,
                    onCheckedChange = {
                        categories.add("elderly")
                        checkedStateElderly.value = it
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorResource(id = R.color.leaf)
                    )
                )
                Text(
                    text = "Пожилые",
                    fontSize = fontSizeCheckBox
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checkedStateAnimals.value,
                    onCheckedChange = {
                        categories.add("animals")
                        checkedStateAnimals.value = it
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorResource(id = R.color.leaf)
                    )
                )
                Text(
                    text = "Животные",
                    fontSize = fontSizeCheckBox
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checkedStateEvents.value,
                    onCheckedChange = {
                        categories.add("events")
                        checkedStateEvents.value = it
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorResource(id = R.color.leaf)
                    )
                )
                Text(
                    text = "События",
                    fontSize = fontSizeCheckBox
                )
            }
        }
    }

    companion object {
        private const val contDescImg = "img"
        const val filterKey = "cat"
    }
}
