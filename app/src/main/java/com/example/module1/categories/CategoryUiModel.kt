package com.example.module1.categories

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "category_table")
data class CategoryUiModel(
    @ColumnInfo(name = "img") val img: String,
    @PrimaryKey val text: String,
    @ColumnInfo(name = "value") val value: String
) : Parcelable