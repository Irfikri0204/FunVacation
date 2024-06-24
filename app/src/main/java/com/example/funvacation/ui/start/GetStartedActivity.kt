package com.example.funvacation.ui.start

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.funvacation.R
import com.example.funvacation.databinding.ActivityGetStartedBinding
import com.example.funvacation.ui.main.MainActivity
import com.example.funvacation.utils.Preferences

class GetStartedActivity : AppCompatActivity() {
    private lateinit var binding : ActivityGetStartedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnStart.setOnClickListener {
            saveStart()
            nextActivity()
        }
    }
    private fun saveStart(){
        Preferences().saveStart(true, applicationContext)
    }

    private fun nextActivity(){
        val intent = Intent(this@GetStartedActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}