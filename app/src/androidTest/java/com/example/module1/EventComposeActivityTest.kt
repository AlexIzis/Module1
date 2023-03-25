package com.example.module1

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import com.example.module1.event.EventComposeActivity
import org.junit.Rule
import org.junit.Test

class EventComposeActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<EventComposeActivity>()

    @Test
    fun testButtonClick() {
        val button = composeTestRule.onNode(hasTestTag("TestTagEventText"), true)
        button.assertIsDisplayed()
        button.performClick()
    }
}
