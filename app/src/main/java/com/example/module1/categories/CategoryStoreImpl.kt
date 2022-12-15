package com.example.module1.categories

import android.util.Log
import com.example.module1.retrofit.Common
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryStoreImpl: CategoryStore {
    private val categoriesStoreFlow = MutableStateFlow<List<CategoryUiModel>>(emptyList())
    private var listCategories = arrayListOf(
        CategoryUiModel("@drawable/little", "Дети", "children"),
        CategoryUiModel("@drawable/dad", "Взрослые", "adults"),
        CategoryUiModel("@drawable/granny", "Пожилые", "elderly"),
        CategoryUiModel("@drawable/cat", "Животные", "animals"),
        CategoryUiModel("@drawable/event", "События", "events")
    )

    override fun getList(vmScope: CoroutineScope) {
        Common().retrofitServiceCategories.getCategoriesList().enqueue(object : Callback<MutableList<CategoryUiModel>>{
            override fun onResponse(
                call: Call<MutableList<CategoryUiModel>>,
                response: Response<MutableList<CategoryUiModel>>
            ) {
                val list = if(response.body() == null) {
                    Log.d("errorNetworkNews", response.toString())
                    listCategories
                } else {
                    response.body() as List<CategoryUiModel>
                }
                vmScope.launch {
                    categoriesStoreFlow.emit(list)
                }
            }

            override fun onFailure(call: Call<MutableList<CategoryUiModel>>, t: Throwable) {
                Log.d("errorNetworkCategories", t.toString())
            }

        })
    }
    override fun getFlow(): Flow<List<CategoryUiModel>> = categoriesStoreFlow
}
