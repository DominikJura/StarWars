package co.uk.androidrecruitmenttask

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers.isRoot
import android.view.View
import org.hamcrest.Matcher

fun waitFor(millis: Long): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> = isRoot()

        override fun getDescription(): String = "Wait for $millis milliseconds."

        override fun perform(uiController: UiController, view: View) {
            uiController.loopMainThreadForAtLeast(millis)
        }
    }
}