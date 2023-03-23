package com.example.module1

import com.example.module1.auth.LoginViewModel
import org.junit.Assert
import org.junit.Test

class LoginViewModelTest {

    @Test
    fun `test update email`() {
        val vm = LoginViewModel()
        vm.updateEmail(email = "qwerty")
        val expected = "qwerty"
        val actual = vm.email.value
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test update password`() {
        val vm = LoginViewModel()
        vm.updatePassword(password = "qwerty")
        val expected = "qwerty"
        val actual = vm.password.value
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test enable button fun true`() {
        val vm = LoginViewModel()
        vm.updateEmail(email = "qwerty")
        vm.updatePassword(password = "qwerty")
        val expected = true
        val actual = vm.enabledButton()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test enable button fun false`() {
        val vm = LoginViewModel()
        vm.updateEmail(email = "abc")
        vm.updatePassword(password = "qwerty")
        val expected = false
        val actual = vm.enabledButton()
        Assert.assertEquals(expected, actual)
    }
}
