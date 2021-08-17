package com.example.puasareminder.util

import androidx.room.TypeConverter
import com.example.puasareminder.model.Puasa
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DatabaseConverter {
    var gson = Gson()

    @TypeConverter
    fun productToString(puasa: Puasa): String{
        return gson.toJson(puasa)
    }

    @TypeConverter
    fun stringToMenu(data: String): Puasa{
        val listType = object : TypeToken<Puasa>(){}.type
        return gson.fromJson(data, listType)
    }

}