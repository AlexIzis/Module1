package com.example.module1.news

import android.content.Context
import android.util.Log
import com.example.module1.retrofit.Common
import com.example.module1.room.AppDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsStoreImpl(private val context: Context) : NewsStore {
    private val newsStoreFlow = MutableStateFlow<List<NewsUIModel>>(emptyList())
    private lateinit var database: AppDatabase
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

    override fun getDataFromDB(scope: CoroutineScope, dispatcher: CoroutineDispatcher) {
        database = AppDatabase.getDataBase(context)
        scope.launch(dispatcher) {
            val news = database.newDao().getNews()
            if (news.isEmpty()) {
                getDataFromServer(scope, dispatcher)
            } else {
                newsStoreFlow.emit(news)
            }
        }
    }

    override fun getDataFromServer(scope: CoroutineScope, dispatcher: CoroutineDispatcher) {
        Common().retrofitServicesNews.getNewsList()
            .enqueue(object : Callback<MutableList<NewsUIModel>> {
                override fun onResponse(
                    call: Call<MutableList<NewsUIModel>>,
                    response: Response<MutableList<NewsUIModel>>
                ) {
                    val list = if (response.body() == null) {
                        Log.d("errorNetworkNews", response.toString())
                        listNews
                    } else {
                        response.body() as List<NewsUIModel>
                    }
                    scope.launch {
                        newsStoreFlow.emit(list)
                        withContext(dispatcher) {
                            for (news in list) {
                                database.newDao().insertNews(news)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<MutableList<NewsUIModel>>, t: Throwable) {
                    Log.d("errorNetworkNews", t.toString())
                }

            })
    }

    override fun getFlow(): Flow<List<NewsUIModel>> = newsStoreFlow
}
