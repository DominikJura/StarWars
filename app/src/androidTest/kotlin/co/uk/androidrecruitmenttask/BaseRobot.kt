package co.uk.androidrecruitmenttask

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.isRoot
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText

abstract class BaseRobot {

    init {
        onView(isRoot()).perform(waitFor(100L))
    }

    protected fun isDisplayed(id: Int) {
        onView(withId(id)).check(matches(isDisplayed()))
    }

    protected fun isTextDisplayed(text: String) {
        onView(withText(text)).check(matches(isDisplayed()))
    }

    fun back() {
        Espresso.pressBack()
    }
}