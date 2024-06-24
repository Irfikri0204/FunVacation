package com.example.funvacation.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DestinationData(
    val name : String,
    val tempat : String,
    val deskripsi : String,
    val photo : Int,
    val link : String
) : Parcelable
