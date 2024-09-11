package com.dzcoding.newsapp.Prefrence

import android.content.Context

object NewsPreferences {
    fun SaveSahredPrefences(context:Context,prefrencesKey:String,value:Boolean){
        val SharedPrefrences = context.getSharedPreferences(prefrencesKey,Context.MODE_PRIVATE).edit()
        SharedPrefrences.putBoolean(prefrencesKey,value).apply()
    }
    fun getSharedPrefrences(context: Context,prefrencesKey: String):Boolean{
        val SharedPrefrences = context.getSharedPreferences(prefrencesKey,Context.MODE_PRIVATE)
        return SharedPrefrences.getBoolean(prefrencesKey,false)
    }
}