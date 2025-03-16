package com.yaku.yaku_vma.presentation.view.auth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.yaku.yaku_vma.MainActivity
import com.yaku.yaku_vma.R

class SplashActivity : AppCompatActivity() {

    private lateinit var logo: ImageView
    private lateinit var logotipo: ImageView
    private lateinit var logotipo2: TextView
    private lateinit var eslogan: TextView

    private val handler = Handler(Looper.getMainLooper())
    private var textIndex = 0
    private lateinit var fullEsloganText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        setContentView(R.layout.activity_splash)

        logo = findViewById(R.id.logo)
        logotipo = findViewById(R.id.logotipo)
        logotipo2 = findViewById(R.id.logotipo_2)
        eslogan = findViewById(R.id.eslogan)

        val zoomAnim = AnimationUtils.loadAnimation(this, R.anim.zoom_logo)
        val slideLeftAnim = AnimationUtils.loadAnimation(this, R.anim.banner_in_left)
        val slideRightAnim = AnimationUtils.loadAnimation(this, R.anim.banner_in_right)

        logo.startAnimation(zoomAnim)
        logotipo.startAnimation(slideLeftAnim)
        logotipo2.startAnimation(slideRightAnim)

        fullEsloganText = getString(R.string.eslogan)
        eslogan.text = ""
        mostrarTextoEslogan()

        handler.postDelayed({
            val sharedPreferences: SharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)
            val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            intent.putExtra("navigateTo", if (isLoggedIn) "home" else "welcome")

            startActivity(intent)
            finish()
        }, 3000)
    }

    private fun mostrarTextoEslogan() {
        if (textIndex <= fullEsloganText.length) {
            eslogan.text = fullEsloganText.substring(0, textIndex)
            textIndex++
            handler.postDelayed({ mostrarTextoEslogan() }, 70)
        }
    }
}
