package com.example.module1

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import com.example.module1.news.NewsComposeActivity
import org.junit.Rule
import org.junit.Test

class NewsComposeActivityTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<NewsComposeActivity>()

    @Test
    fun testButtonClickNews() {
        val button = composeTestRule.onNode(hasTestTag("TestTagNewsButton"), true)
        button.assertIsDisplayed()
        button.performClick()
    }

    @Test
    fun testIconButtonClickNews() {
        val button = composeTestRule.onNode(hasTestTag("TestTagNewsIconButton"), true)
        button.assertIsDisplayed()
        button.performClick()
    }
}
