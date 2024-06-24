package com.example.funvacation.ui.detail

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.funvacation.R
import com.example.funvacation.databinding.ActivityDetailBinding

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tName  = intent.getStringExtra(EXTRA_NAME)
        val tImg = intent.getIntExtra(EXTRA_PHOTO,0)
        val tLocation = intent.getStringExtra(EXTRA_LOCATION)
        val tDesc = intent.getStringExtra(EXTRA_DESC)

        binding.apply {
            tvNama.text = tName
            tvLocation.text = tLocation
            tvDesc.text = tDesc
            ivTempat.setImageResource(tImg)
            btnBack.setOnClickListener {
                onBackPressed()
            }
            btnShare.setOnClickListener {
                val b = BitmapFactory.decodeResource(resources,tImg)
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                val path = MediaStore.Images.Media.insertImage(
                    contentResolver,
                    b,
                    "Title",
                    null
                )
                val uri = Uri.parse(path)
                intent.putExtra(Intent.EXTRA_STREAM,uri)
                intent.putExtra(Intent.EXTRA_TEXT, "${tName}\n\n${tLocation}\n\n${tDesc}")
                intent.type="image/*"
                val shareIntent = Intent.createChooser(intent, "Bagikan Informasi")
                startActivity(shareIntent)
            }
            binding.btnBrowser.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(intent.getStringExtra(EXTRA_LINK)))
                startActivity(intent)
            }
        }
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_PHOTO = "extra_photo"
        const val EXTRA_LOCATION = "extra_location"
        const val EXTRA_DESC = "extra_description"
        const val EXTRA_LINK = "extra_link"
    }
}