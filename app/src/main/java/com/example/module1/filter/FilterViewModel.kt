package com.example.module1.filter

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class FilterViewModel : ViewModel() {
    private val listOfCategory = arrayListOf<String>()

    private val _checkedStateChildren = mutableStateOf(false)
    val checkedStateChildren: State<Boolean> = _checkedStateChildren

    private val _checkedStateAdults = mutableStateOf(false)
    val checkedStateAdults: State<Boolean> = _checkedStateAdults

    private val _checkedStateElderly = mutableStateOf(false)
    val checkedStateElderly: State<Boolean> = _checkedStateElderly

    private val _checkedStateAnimals = mutableStateOf(false)
    val checkedStateAnimals: State<Boolean> = _checkedStateAnimals

    private val _checkedStateEvents = mutableStateOf(false)
    val checkedStateEvents: State<Boolean> = _checkedStateEvents

    fun returnCategories(): ArrayList<String> {
        return listOfCategory
    }

    fun updateStateChildren(value: Boolean) {
        if (value) {
            listOfCategory.add("children")
        } else {
            listOfCategory.remove("children")
        }
        _checkedStateChildren.value = value
    }

    fun updateStateAdults(value: Boolean) {
        if (value) {
            listOfCategory.add("adults")
        } else {
            listOfCategory.remove("adults")
        }
        _checkedStateAdults.value = value
    }

    fun updateStateElderly(value: Boolean) {
        if (value) {
            listOfCategory.add("elderly")
        } else {
            listOfCategory.remove("elderly")
        }
        _checkedStateElderly.value = value
    }

    fun updateStateAnimals(value: Boolean) {
        if (value) {
            listOfCategory.add("animals")
        } else {
            listOfCategory.remove("animals")
        }
        _checkedStateAnimals.value = value
    }

    fun updateStateEvents(value: Boolean) {
        if (value) {
            listOfCategory.add("events")
        } else {
            listOfCategory.remove("events")
        }
        _checkedStateEvents.value = value
    }
}
