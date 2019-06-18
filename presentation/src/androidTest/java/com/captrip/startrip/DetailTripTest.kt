package com.captrip

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.captrip.detail.DetailActivity
import org.junit.Rule
import org.junit.Test
import java.lang.Thread.sleep

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DetailTripTest {

    @Rule
    @JvmField
    var mainActivityTestRule = ActivityTestRule(DetailActivity::class.java, false, false)

    @Test
    fun openTrip() {
        startDetailActivity(1)
    }

    @Test
    fun openTripSuccess() {
        startDetailActivity(1)
        // TODO Replace with IdleResources
        sleep(3000)
        onView(withId(R.id.detailContent)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun openTripFailed() {
        startDetailActivity(-1)
        // TODO Replace with IdleResources
        sleep(3000)
        onView(withId(R.id.statusRetry)).check(matches(ViewMatchers.isDisplayed()))
    }

    private fun startDetailActivity(id: Int) {
        val intent = Intent().putExtra(EXTRA_ID, id)
        mainActivityTestRule.launchActivity(intent)
    }

    companion object {
        private const val EXTRA_ID = "extra_id"
    }
}
