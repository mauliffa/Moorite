package com.example.moorite.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.example.moorite.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private var binding : ActivitySettingBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnLanguange?.setOnClickListener {
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}