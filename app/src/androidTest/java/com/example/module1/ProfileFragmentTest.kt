package com.example.module1

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.module1.profile.ProfileFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProfileFragmentTest {

    @Test
    fun checkPencilButton() {
        val fragment = launchFragmentInContainer<ProfileFragment>()
        fragment.moveToState(Lifecycle.State.STARTED)
        Espresso.onView(withId(R.id.imageView4)).perform(click())
    }
}
