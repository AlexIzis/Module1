package com.example.module1.retrofit

import android.util.Log
import com.example.module1.news.NewsStoreImpl
import com.example.module1.news.NewsUIModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Request {
    private lateinit var list: List<NewsUIModel>

    fun startRequest() {
        val service = Common.retrofitServices
        service.getNewsList().enqueue(object : Callback<MutableList<NewsUIModel>> {
            override fun onResponse(
                call: Call<MutableList<NewsUIModel>>,
                response: Response<MutableList<NewsUIModel>>
            ) {
                if (response.body() == null) {
                    Log.d("errorNetwork", response.toString())
                } else {
                    list = response.body() as List<NewsUIModel>
                }
            }

            override fun onFailure(call: Call<MutableList<NewsUIModel>>, t: Throwable) {
                Log.d("errorNetwork", t.toString())
                list = NewsStoreImpl().getNews()
            }

        })
    }
}
