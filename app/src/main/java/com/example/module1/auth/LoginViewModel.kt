package com.example.module1.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

/**
 * ViewModel класс для ComposeLoginActivity
 */
class LoginViewModel : ViewModel() {

    private var _email = mutableStateOf("")
    val email: State<String> = _email

    private var _password = mutableStateOf("")
    val password: State<String> = _password

    fun updateEmail(email: String) {
        _email.value = email
    }

    fun updatePassword(password: String) {
        this._password.value = password
    }

    fun enabledButton(): Boolean {
        return (_email.value.length >= 5) && (_password.value.length >= 5)
    }
}