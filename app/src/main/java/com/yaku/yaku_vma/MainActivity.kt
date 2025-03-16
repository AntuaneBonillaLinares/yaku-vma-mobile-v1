package com.yaku.yaku_vma

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yaku.yaku_vma.presentation.view.auth.WelcomeFragment
import com.yaku.yaku_vma.presentation.view.auth.LoginFragment
import com.yaku.yaku_vma.presentation.view.screens.home.Home

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val sharedPref = getSharedPreferences("UserSession", MODE_PRIVATE)
            val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)

            val navigateTo = intent.getStringExtra("navigateTo")

            val initialFragment = when (navigateTo) {
                "home" -> Home()
                "login" -> LoginFragment()
                "welcome" -> WelcomeFragment()
                else -> if (isLoggedIn) Home() else WelcomeFragment()
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, initialFragment)
                .commit()
        }
    }
}
