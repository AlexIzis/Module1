package com.example.module1

import com.example.module1.filter.FilterViewModel
import org.junit.Assert
import org.junit.Test

class FilterViewModelTest {
    @Test
    fun `test update state children true`() {
        val vm = FilterViewModel()
        vm.updateStateChildren(true)
        Assert.assertEquals(true, vm.checkedStateChildren.value)
        Assert.assertEquals(1, vm.returnCategories().size)
    }

    @Test
    fun `test update state children false`() {
        val vm = FilterViewModel()
        vm.updateStateChildren(true)
        vm.updateStateChildren(false)
        Assert.assertEquals(false, vm.checkedStateChildren.value)
        Assert.assertEquals(0, vm.returnCategories().size)
    }
}
