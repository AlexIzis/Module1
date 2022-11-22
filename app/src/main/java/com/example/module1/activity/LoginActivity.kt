package com.example.module1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.module1.R
import com.jakewharton.rxbinding.widget.RxTextView
import rx.Observable
import rx.Subscription

class LoginActivity : AppCompatActivity() {
    private var inputEmail: String = ""
    private var inputPassword: String = ""
    private lateinit var disposableBus: Subscription

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

        analyzeLoginData(emailObservable, passwordObservable, loginButton)
    }

    private fun analyzeLoginData(
        emailObservable: Observable<CharSequence>,
        passwordObservable: Observable<CharSequence>,
        loginButton: Button
    ) {
        disposableBus = Observable.combineLatest(
            emailObservable, passwordObservable
        ) { email, password ->
            inputEmail = email.toString()
            inputPassword = password.toString()
            (email.isNotEmpty() && email.length >= 6) &&
                    (password.isNotEmpty() && password.length >= 6)
        }.subscribe {
            loginButton.isEnabled = it
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableBus.unsubscribe()
    }
}