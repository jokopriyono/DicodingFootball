package com.jokopriyono.dicodingfootball

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.jokopriyono.dicodingfootball.R.id.*
import com.jokopriyono.dicodingfootball.feature.main.MainActivity
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
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
        pressBack()

        onView(withId(menu_team)).perform(click())
        Thread.sleep(1000)
        onView(withId(recycler_team)).check(matches(isDisplayed()))
        onView(withId(recycler_team)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(menu_favorite)).perform(click())
        Thread.sleep(1000)
        pressBack()
        Thread.sleep(1000)
        pressBack()
        pressBack()

        onView(withId(menu_fav)).perform(click())

        onView(withId(recycler_match)).check(matches(isDisplayed()))
        onView(withId(recycler_match)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(1000)
        pressBack()
        onView(allOf(withText("TEAM"))).perform(click())
        onView(withId(recycler_team)).check(matches(isDisplayed()))
        onView(withId(recycler_team)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(1000)
        pressBack()
    }
}