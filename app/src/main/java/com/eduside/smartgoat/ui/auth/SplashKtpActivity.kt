package com.eduside.smartgoat.ui.auth


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.eduside.smartgoat.databinding.ActivitySplashKtpBinding
import com.eduside.smartgoat.ui.auth.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashKtpActivity : AppCompatActivity() {
    private var _binding : ActivitySplashKtpBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashKtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            initUi()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun initUi() {
        Handler(Looper.getMainLooper()).postDelayed({
           startActivity(Intent(this, LoginActivity::class.java))
            finish()
        },2000)

    }
}

