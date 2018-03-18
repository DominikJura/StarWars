package co.uk.androidrecruitmenttask.main.robot

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToHolder
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.v7.widget.RecyclerView
import android.view.View
import co.uk.androidrecruitmenttask.BaseRobot
import co.uk.androidrecruitmenttask.R
import co.uk.androidrecruitmenttask.feature.main.ui.adapters.viewholder.PeopleViewHolder
import co.uk.androidrecruitmenttask.starships.robot.StarshipsRobot
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher

fun main(func: MainRobot.() -> Unit) = MainRobot().apply(func)

class MainRobot : BaseRobot() {

    companion object {
        const val STAR_WARS_JEDI_TEXT = "StarWarsJedi"
    }

    init {
        isDisplayed(R.id.main_people_recycler)
        isDisplayed(R.id.main_root_view)
    }

    fun scrollFromFirstToLastStarWarsPerson(index: Int) {
        for (i in 0..index) {
            isDisplayed(R.id.main_people_recycler)
            // unfortunately onScroll Listener is not called by contrib Espresso
            onView(withId(R.id.main_people_recycler)).perform(scrollToPosition<PeopleViewHolder>(i))
            checkElement(i)
        }
    }

    private fun checkElement(position: Int) {
        isTextDisplayed("$STAR_WARS_JEDI_TEXT $position")
    }

    fun clickItem(position: Int,func: StarshipsRobot.() -> Unit): StarshipsRobot {
        checkElement(position)
        onView(withId(R.id.main_people_recycler)).perform(
                scrollToHolder(CustomViewHolderMatcher()).atPosition(position), click())
        return StarshipsRobot().apply(func)
    }

    private class CustomViewHolderMatcher(
            private val itemMatcher: Matcher<View> = Matchers.any(View::class.java)
    ) : TypeSafeMatcher<RecyclerView.ViewHolder>() {

        override fun describeTo(description: Description) {
            description.appendText("is assignable from CustomViewHolder")
        }

        override fun matchesSafely(viewHolder: RecyclerView.ViewHolder): Boolean =
                PeopleViewHolder::class.java.isAssignableFrom(viewHolder.javaClass)
                        && itemMatcher.matches(viewHolder.itemView)
    }
}
