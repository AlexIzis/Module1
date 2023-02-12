package com.example.module1.news

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "news_table")
data class NewsUIModel(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "label") val label: String,
    @ColumnInfo(name = "img") val img: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "time") val time: Long,
    @ColumnInfo(name = "organization") val organization: String,
    @ColumnInfo(name = "address") val address: String,
    @TypeConverters(ListStringTypeConverter::class)
    @ColumnInfo(name = "numberList") val numberList: List<String>,
    @ColumnInfo(name = "email") val email: String,
    @TypeConverters(ListIntTypeConverter::class)
    @ColumnInfo(name = "imgOptionally") val imgOptionally: List<Int>,
    @ColumnInfo(name = "site") val site: String,
    @TypeConverters(ListStringTypeConverter::class)
    @ColumnInfo(name = "categories") val categories: List<String>
) : Parcelable