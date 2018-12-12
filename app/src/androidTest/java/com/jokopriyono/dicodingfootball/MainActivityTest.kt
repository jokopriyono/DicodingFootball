package com.jokopriyono.dicodingfootball

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.jokopriyono.dicodingfootball.R.id.*
import com.jokopriyono.dicodingfootball.feature.main.MainActivity
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testFavoritBehavior() {
        onView(withText(R.string.app_name)).check(matches(isDisplayed()))
        Thread.sleep(3000)

        onView(withId(spinner_league)).check(matches(isDisplayed()))
        onView(withId(spinner_league)).perform(click())
        onData(anything()).atPosition(4).perform(click())

        Thread.sleep(3000)
        onView(withId(recycler)).check(matches(isDisplayed()))
        onView(withId(recycler)).perform(scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(withId(recycler)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))

        onView(withId(menu_favorite)).perform(click())
        Thread.sleep(1000)
        onView(withText(R.string.added_fav)).inRoot(withDecorView(not(activityRule.activity.window.decorView))).check(matches(isDisplayed()))

        pressBack()
        onView(withId(menu_fav)).perform(click())

        onView(withId(recycler)).check(matches(isDisplayed()))
        onView(withId(recycler)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }
}