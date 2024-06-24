package com.example.funvacation.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.funvacation.R
import com.example.funvacation.data.DestinationData
import com.example.funvacation.databinding.ActivityMainBinding
import com.example.funvacation.ui.adapter.ListDestinationAdapter
import com.example.funvacation.ui.profile.ProfileActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val list = ArrayList<DestinationData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.ivProfile.setOnClickListener {
            val intent = Intent(this@MainActivity,ProfileActivity::class.java)
            startActivity(intent)
        }
        binding.rvVacation.setHasFixedSize(true)
        list.addAll(getListVacation())
        showRecyclerList()
    }

    private fun getListVacation(): ArrayList<DestinationData> {
        val dataName = resources.getStringArray(R.array.place_name)
        val dataPlace = resources.getStringArray(R.array.place_position)
        val dataDescription = resources.getStringArray(R.array.place_description)
        val dataPhoto = resources.obtainTypedArray(R.array.place_photo)
        val dataLink = resources.getStringArray(R.array.place_link)
        val listData = ArrayList<DestinationData>()
        for (i in dataName.indices) {
            val data = DestinationData(dataName[i],dataPlace[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataLink[i])
            listData.add(data)
        }
        return listData
    }

    private fun showRecyclerList() {
        binding.rvVacation.layoutManager = LinearLayoutManager(applicationContext)
        val listVacationAdapter = ListDestinationAdapter(list)
        binding.rvVacation.adapter = listVacationAdapter
    }
}