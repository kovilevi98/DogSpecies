package hu.bme.aut.dogspecies.datasource.database

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class Converters {

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @TypeConverter
    fun jsonToList(json: String): List<String> = moshi.fromJson<List<String>>(json).orEmpty()

    @TypeConverter
    fun listToJson(list: List<String>): String = moshi.toJson(list)

    @TypeConverter
    fun jsonToListInt(json: String): List<Int> = moshi.fromJson<List<Int>>(json).orEmpty()

    @TypeConverter
    fun intListToJson(list: List<Int>): String = moshi.toJson(list)
}

inline fun <reified T> Moshi.fromJson(json: String) = this.adapter(T::class.java).fromJson(json)

inline fun <reified T> Moshi.toJson(value: T): String = this.adapter(T::class.java).toJson(value)