package com.example.module1.news

data class NewsUIModel(
    val id: Int,
    val label: String,
    val img: String,
    val description: String,
    val time: Long,
    val organization: String,
    val address: String,
    val numberList: List<String>,
    val email: String,
    val imgOpt: List<String>,
    val site: String,
    val categories: List<String>
)