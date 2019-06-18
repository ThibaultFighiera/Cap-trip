package com.captrip

import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.rule.ActivityTestRule
import com.captrip.list.TripListActivity
import com.captrip.splash.SplashScreen
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
class SplashScreenTest {

    @Rule
    @JvmField
    var splashScreenTestRule = ActivityTestRule(SplashScreen::class.java, false, true)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun openSplashScreen() {
        // TODO Replace with IdleResources
        sleep(3000)
        Intents.intended(IntentMatchers.hasComponent(TripListActivity::class.java.name))
    }
}
