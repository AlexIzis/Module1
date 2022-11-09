package com.example.module1.categories

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryUiModel(val img: String, val text: String, val value: String) : Parcelable