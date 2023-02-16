package com.example.module1.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.material.* // ktlint-disable no-wildcard-imports
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.module1.R
import com.example.module1.activity.CategoriesActivity

/**
 * Класс ComposeLoginActivity
 * Служит для отображения экрана авторизации
 */
class ComposeLoginActivity : ComponentActivity() {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Preview
    @Composable
    fun MainScreen() {
        val horizontalPadding = 56.dp
        val verticalPadding = 40.dp

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            CustomToolbar()
            Text(
                text = stringResource(id = R.string.string_with_social),
                modifier = Modifier.padding(
                    start = horizontalPadding,
                    end = horizontalPadding,
                    top = verticalPadding
                ),
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
            Socials()
            Text(
                text = stringResource(id = R.string.string_with_app),
                modifier = Modifier.padding(
                    start = horizontalPadding,
                    end = horizontalPadding,
                    top = verticalPadding
                ),
                fontSize = 14.sp
            )
            UserTextInput()
            UnderlinedStrings()
        }
    }

    @Composable
    fun CustomToolbar() {
        Row(
            modifier = Modifier
                .background(color = colorResource(id = mainColor))
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = contDescImg,
                tint = Color.White,
                modifier = Modifier.padding(14.dp)
            )

            Text(
                text = stringResource(id = R.string.login),
                color = Color.White,
                fontSize = 21.sp,
                fontFamily = FontFamily(Font(R.font.officinasansextraboldscc))
            )
        }
    }

    @Composable
    fun Socials() {
        val horizontalPadding = 60.dp
        val verticalPadding = 20.dp

        Row(
            modifier = Modifier
                .padding(start = horizontalPadding, end = horizontalPadding, top = verticalPadding)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_vk),
                contentDescription = contDescImg
            )
            Image(
                painter = painterResource(id = R.drawable.ic_fb),
                contentDescription = contDescImg
            )
            Image(
                painter = painterResource(id = R.drawable.ic_ok),
                contentDescription = contDescImg
            )
        }
    }

    @Composable
    fun UserTextInput() {
        val context = LocalContext.current
        val mainVerticalPadding = 32.dp
        val sideVerticalPadding = 10.dp

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = viewModel.email.value,
                modifier = Modifier
                    .padding(top = mainVerticalPadding)
                    .background(color = Color.White),
                label = {
                    Text(
                        text = stringResource(id = R.string.e_mail),
                        color = colorResource(id = mainColor)
                    )
                },
                onValueChange = {
                    viewModel.updateEmail(it)
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = colorResource(id = mainColor),
                    cursorColor = colorResource(id = mainColor)
                )
            )
            TextField(
                value = viewModel.password.value,
                modifier = Modifier
                    .padding(top = sideVerticalPadding)
                    .background(color = Color.White),
                label = {
                    Text(
                        text = stringResource(id = R.string.password),
                        color = colorResource(id = mainColor)
                    )
                },
                onValueChange = {
                    viewModel.updatePassword(it)
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = colorResource(id = mainColor),
                    cursorColor = colorResource(id = mainColor)
                )
            )
            Button(
                onClick = {
                    val intent = Intent(context, CategoriesActivity::class.java)
                    startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = mainColor)),
                enabled = viewModel.enabledButton()
            ) {
                Text(
                    text = stringResource(id = R.string.button_login),
                    color = Color.White
                )
            }
        }
    }

    @Composable
    fun UnderlinedStrings() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.forgot_password),
                color = colorResource(id = mainColor),
                textDecoration = TextDecoration.Underline
            )
            Text(
                text = stringResource(id = R.string.registration),
                color = colorResource(id = mainColor),
                textDecoration = TextDecoration.Underline
            )
        }
    }

    companion object {
        private const val contDescImg = "img"
        private const val mainColor = R.color.leaf
    }
}
