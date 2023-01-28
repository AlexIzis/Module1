package com.example.module1.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import com.example.module1.FragmentNavigation
import com.example.module1.R
import com.example.module1.filter.FilterFragment
import com.example.module1.news.ui.theme.Module1Theme

class NewsComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Module1Theme {
                MainScreen()
            }
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
                FragmentNavigation().addFragment(
                    fragmentManager = FragmentActivity().supportFragmentManager,
                    fragmentContainer = R.id.fragmentContainerView,
                    fragment = FilterFragment()
                )
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
