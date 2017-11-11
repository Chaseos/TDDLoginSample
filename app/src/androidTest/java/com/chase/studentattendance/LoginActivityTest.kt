package com.chase.studentattendance

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    private val activityTestRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    @Test
    fun checkUserNameEditTextIsDisplayed() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.username_textinput)).check(matches(isDisplayed()))
    }

    @Test
    fun checkErrorMessageIsDisplayedForEmptyData() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.login_button)).check(matches(isDisplayed())).perform(click())
        onView(withText("Please check Username or Password.")).check(matches(isDisplayed()))
    }

    @Test
    fun checkLoginSuccess() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.username_textinput)).perform(typeText("chase"))
        onView(withId(R.id.password_textinput)).perform(typeText("tdd"))
        onView(withId(R.id.login_button)).check(matches(isDisplayed())).perform(click())
        onView(withText("Login successful.")).check(matches(isDisplayed()))
    }
}