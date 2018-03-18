package co.uk.androidrecruitmenttask

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isClickable
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.isEnabled
import android.support.test.espresso.matcher.ViewMatchers.isRoot
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.not
import android.support.v7.widget.RecyclerView
import android.view.View
import co.uk.androidrecruitmenttask.feature.main.ui.adapters.viewholder.PeopleViewHolder
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.any
import org.hamcrest.TypeSafeMatcher

abstract class BaseRobot {

    init {
        onView(isRoot()).perform(waitFor(100L))
    }

    protected fun isDisplayed(id: Int) {
        onView(withId(id)).check(matches(isDisplayed()))
    }

    protected fun isTextDisplayed(id: Int) {
        onView(withText(id)).check(matches(isDisplayed()))
    }

    protected fun isTextDisplayed(text: String) {
        onView(withText(text)).check(matches(isDisplayed()))
    }

    protected fun isDisabled(id: Int) {
        onView(withId(id)).check(matches(not(isEnabled())))
    }

    protected fun performClick(id: Int) {
        isDisplayed(id)
        isClickable(id)
        onView(withId(id)).perform(click())
    }

    protected fun isEnabled(id: Int) {
        onView(withId(id)).check(matches(isEnabled()))
    }

    private fun isClickable(id: Int) {
        onView(withId(id)).check(matches(isClickable()))
    }

    fun back() {
        Espresso.pressBack()
    }
}