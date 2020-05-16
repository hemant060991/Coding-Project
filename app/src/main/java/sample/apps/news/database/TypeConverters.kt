package sample.apps.news.database

import androidx.room.TypeConverter
import sample.apps.news.entity.Source

class TypeConverters {

    @TypeConverter
    fun stringToSource(string: String?) =
        Source(
            id = null,
            name = string
        )

    @TypeConverter
    fun sourceToString(source: Source?): String = source?.name ?: ""

}