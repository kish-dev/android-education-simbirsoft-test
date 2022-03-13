package com.education.test.simbirsoft.data.db

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.time.Instant
import java.time.format.DateTimeFormatter

@Entity(
    tableName = "events",
    indices = [Index(
        value = arrayOf("date_start"),
        unique = true
    ), Index(value = arrayOf("date_finish"), unique = true)]
)

data class EventEntity(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    val id: Long?,

    @ColumnInfo(name = "date_start")
    val dateStart: String,

    @ColumnInfo(name = "date_finish")
    val dateFinish: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,
)

data class Event(
    val id: Long?,
    val unixDateStart: String,
    val unixDateFinish: String,
    val dateStart: String,
    val dateFinish: String,
    val name: String,
    val description: String,
)

fun String.simpleParseUnixTime(): String {
    return ((this.toLong() - this.toLong() / 86400L * 86400L) / 3600).toString()
}

fun EventEntity.mapToDomain(): Event {
    return Event(
        id = id,
        unixDateStart = dateStart,
        unixDateFinish = dateFinish,
        dateStart = "${dateStart.simpleParseUnixTime()}:00",
        dateFinish = "${dateFinish.simpleParseUnixTime()}:00",
        name = name,
        description = description
    )
}

fun Event.toEntity(): EventEntity {
    return EventEntity(
        id = null,
        dateStart = unixDateStart,
        dateFinish = unixDateFinish,
        name = name,
        description = description
    )
}

class JSONConverterEvent {
    @TypeConverter
    fun fromEventEntity(event: EventEntity?): String {
        val gson = Gson()
        val type: Type = object : TypeToken<EventEntity?>() {}.type
        return gson.toJson(event, type)
    }

    @TypeConverter
    fun toEventEntity(event: String?): EventEntity? {
        val gson = Gson()
        val type: Type = object : TypeToken<EventEntity?>() {}.type
        return gson.fromJson(event, type)
    }
}