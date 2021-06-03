package com.example.moorite.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moorite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val sectionsPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        binding?.viewPager?.adapter = sectionsPagerAdapter
        binding?.tabs?.setupWithViewPager(binding?.viewPager)

        binding?.btnFav?.setOnClickListener {
            startActivity(Intent(this, Class.forName("com.example.favorite.FavoriteActivity")))
        }
        binding?.btnSetting?.setOnClickListener {
            val moveIntent = Intent(this@MainActivity, SettingActivity::class.java)
            startActivity(moveIntent)
        }

        supportActionBar?.elevation = 0f
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}