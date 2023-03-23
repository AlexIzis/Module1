package com.example.module1

import androidx.lifecycle.viewModelScope
import com.example.module1.news.NewsIntent
import com.example.module1.news.NewsStoreImpl
import com.example.module1.news.NewsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class NewsViewModelTest {
    @ExperimentalCoroutinesApi
    val testDispatcher = UnconfinedTestDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setupDispatcher() {
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `handle Intent`() = runTest {
        val store = mock<NewsStoreImpl>()
        val vm = NewsViewModel(store)
        vm.handleIntent(NewsIntent.StartScreen)
        Mockito.verify(store, Mockito.times(1)).getDataFromDB(
            dispatcher = Dispatchers.IO,
            scope = vm.viewModelScope
        )
    }
}
