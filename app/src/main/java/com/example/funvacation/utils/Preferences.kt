package com.example.funvacation.utils

import android.content.Context
import android.content.SharedPreferences

class Preferences {
    companion object {
        const val APP = "APP"
        fun initPref(context : Context, name: String): SharedPreferences {
            return context.getSharedPreferences(name, Context.MODE_PRIVATE)
        }
    }

    fun initPref(context : Context, name: String): SharedPreferences {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    private fun editorPref(context : Context, name : String): SharedPreferences.Editor{
        val sharedPref = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        return sharedPref.edit()
    }

    fun saveStart(nilai: Boolean, context : Context){
        val saveStart = editorPref(context, APP)
        saveStart.putBoolean("START", nilai)
        saveStart.apply()
    }

    fun getStart(context: Context): Boolean {
        val sharedPref = initPref(context, APP)
        return sharedPref.getBoolean("START", false)
    }
}