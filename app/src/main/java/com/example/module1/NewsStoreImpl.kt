package com.example.module1

import com.example.module1.news.NewsUIModel

class NewsStoreImpl : NewsStore {
    private var listNews = arrayListOf(
        NewsUIModel(
            0,
            "Спонсоры отремонтируют школу-интернат",
            "@drawable/avatar_1",
            "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
            1699999999002,
            "Благотворительный Фонд «Счастливый Мир»",
            "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
            listOf("+7 (937) 037 37-73", "+7 (937) 016 16-16"),
            "Напишите нам",
            listOf("@drawable/avatar_2", "@drawable/avatar_3"),
            "Перейти на сайт организаии",
            listOf("children")
        ),
        NewsUIModel(
            1,
            "Конкурс по вокальному пению в детском доме №6",
            "@drawable/avatar_2",
            "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
            1699999999002,
            "Благотворительный Фонд «Счастливый Мир»",
            "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
            listOf("+7 (937) 037 37-73", "+7 (937) 016 16-16"),
            "Напишите нам",
            listOf("@drawable/avatar_1", "@drawable/avatar_3"),
            "Перейти на сайт организаии",
            listOf("adults", "elderly")
        )
    )

    override fun getNews(): List<NewsUIModel> = listNews
}
