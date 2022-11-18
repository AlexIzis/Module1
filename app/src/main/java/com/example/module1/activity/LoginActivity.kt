package com.example.module1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.example.module1.R
import com.jakewharton.rxbinding.widget.RxTextView
import rx.Observable


private const val MAIL_KEY = "mail"
private const val PASSWORD_KEY = "pass"
class LoginActivity : AppCompatActivity() {
    private var inputEmail: String = ""
    private var inputPassword: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailText: EditText = findViewById(R.id.inputLoginEdit)
        val passwordText: EditText = findViewById(R.id.inputLoginPasswordEdit)

        val loginButton: Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            val intent = Intent(applicationContext, CategoriesActivity::class.java)
            startActivity(intent)
        }

        val emailObservable = RxTextView.textChanges(emailText)
        val passwordObservable = RxTextView.textChanges(passwordText)

        if (savedInstanceState != null) {
            inputEmail = savedInstanceState.getString(MAIL_KEY).toString()
            inputPassword = savedInstanceState.getString(PASSWORD_KEY).toString()
            emailText.setText(inputEmail)
            passwordText.setText(inputPassword)
        }

        Observable.combineLatest(
            emailObservable, passwordObservable
        ) { email, password ->
            inputEmail = email.toString()
            inputPassword = password.toString()
            (!TextUtils.isEmpty(email) && email.length >= 6) &&
                    (!TextUtils.isEmpty(password) && password.length >= 6)
        }.subscribe {
            loginButton.isEnabled = it
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(MAIL_KEY, inputEmail)
        outState.putString(PASSWORD_KEY, inputPassword)
    }
}