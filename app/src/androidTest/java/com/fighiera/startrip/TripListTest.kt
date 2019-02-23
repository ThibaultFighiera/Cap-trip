package com.fighiera.startrip

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.fighiera.startrip.detail.DetailActivity
import com.fighiera.startrip.list.TripListActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Thread.sleep

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TripListTest {

    @Rule
    @JvmField
    var mainActivityTestRule = ActivityTestRule(TripListActivity::class.java, false, true)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun openTripList() {
        // TODO Replace with IdleResources
        sleep(3000)
        onView(withId(R.id.tripList))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1), ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(DetailActivity::class.java.name))

    }

    @Test
    fun openTripListLoading() {
        onView(withId(R.id.statusLoader)).check(matches(isDisplayed()))
    }
}
