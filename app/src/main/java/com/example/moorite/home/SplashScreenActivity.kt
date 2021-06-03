package com.example.moorite.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moorite.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private var _binding: ActivitySplashScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logo.alpha = 0f
        binding.logo.animate().setDuration(3000).alpha(1f).withEndAction {
            val showLogo = Intent (this, MainActivity::class.java)
            startActivity(showLogo)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}