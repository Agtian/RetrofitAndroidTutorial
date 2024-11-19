package com.test.retrofitandroidtutorial

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.retrofitandroidtutorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetAllUser.setOnClickListener {
            startActivity(Intent(this@MainActivity, GetAllUserActivity::class.java))
        }

        binding.btnGetUser.setOnClickListener {
            startActivity(Intent(this@MainActivity, GetUserActivity::class.java))
        }

    }
}