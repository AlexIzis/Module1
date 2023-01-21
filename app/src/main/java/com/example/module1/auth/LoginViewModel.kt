package com.example.module1.auth

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private var _email: String = ""
    val email = _email

    private var _password: String = ""
    val password = _password

    fun updateValues(email: String, password: String) {
        _email = email
        _password = password
    }
}