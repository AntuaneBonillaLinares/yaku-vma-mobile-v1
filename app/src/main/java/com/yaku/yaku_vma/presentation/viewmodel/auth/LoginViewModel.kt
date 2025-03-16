package com.yaku.yaku_vma.presentation.viewmodel.auth

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val validUsername = "admin"
    private val validPassword = "admin123"

    fun isLoginValid(username: String, password: String): Boolean {
        return username == validUsername && password == validPassword
    }
}
