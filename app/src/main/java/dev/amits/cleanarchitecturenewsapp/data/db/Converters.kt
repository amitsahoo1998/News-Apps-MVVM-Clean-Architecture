package dev.amits.cleanarchitecturenewsapp.data.db

import androidx.room.TypeConverter
import dev.amits.cleanarchitecturenewsapp.data.model.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source) : String{
        return source.name
    }

    @TypeConverter
    fun toSource(name : String) : Source{
        return Source(name , name)
    }

}