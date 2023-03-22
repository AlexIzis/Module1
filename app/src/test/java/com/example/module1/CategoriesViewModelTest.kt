package com.example.module1

import androidx.lifecycle.viewModelScope
import com.example.module1.categories.CategoriesIntent
import com.example.module1.categories.CategoriesViewModel
import com.example.module1.categories.CategoryStoreImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.* // ktlint-disable no-wildcard-imports
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class CategoriesViewModelTest {

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
        val store = mock<CategoryStoreImpl>()
        val vm = CategoriesViewModel(store)
        vm.handleIntent(CategoriesIntent.StartScreen)
        Mockito.verify(store, Mockito.times(1)).getDataFromDB(
            dispatcher = Dispatchers.IO,
            scope = vm.viewModelScope
        )
    }
}
