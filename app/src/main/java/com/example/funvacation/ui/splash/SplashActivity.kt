package com.example.funvacation.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.funvacation.R
import com.example.funvacation.databinding.ActivitySplashBinding
import com.example.funvacation.ui.main.MainActivity
import com.example.funvacation.ui.start.GetStartedActivity
import com.example.funvacation.utils.Preferences


@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySplashBinding
    private lateinit var intent : Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Handler().postDelayed({
            intent = if(Preferences().getStart(applicationContext)) {
                Intent(
                    this@SplashActivity,
                    MainActivity::class.java
                )
            } else {
                Intent(
                    this@SplashActivity,
                    GetStartedActivity::class.java
                )
            }
            startActivity(intent)
            finish()
        }, 2500)
    }
}