package bel.ink.bel.testcalc

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//for testing string entering
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

	@Rule
	@JvmField
	val rule = ActivityTestRule(MainActivity::class.java)

	@Test
	fun clearPreviousTest() {
		onView(ViewMatchers.withText("2")).perform(ViewActions.click())
		onView(ViewMatchers.withText("+")).perform(ViewActions.click())
		onView(ViewMatchers.withText("2")).perform(ViewActions.click())
		onView(ViewMatchers.withId(R.id.btClearLast)).perform(ViewActions.click())
		onView(ViewMatchers.withId(R.id.calculator_display)).check(ViewAssertions.matches(ViewMatchers.withText("2 +")))
		onView(ViewMatchers.withId(R.id.btClearLast)).perform(ViewActions.click())
		onView(ViewMatchers.withId(R.id.calculator_display)).check(ViewAssertions.matches(ViewMatchers.withText("2")))
	}

	@Test
	fun enterNumbersTest() {
		onView(ViewMatchers.withText("2")).perform(ViewActions.click())
		onView(ViewMatchers.withText("2")).perform(ViewActions.click())
		onView(ViewMatchers.withText("+")).perform(ViewActions.click())
		onView(ViewMatchers.withText("2")).perform(ViewActions.click())
		onView(ViewMatchers.withText("2")).perform(ViewActions.click())
		onView(ViewMatchers.withText("=")).perform(ViewActions.click())
		onView(ViewMatchers.withId(R.id.calculator_display)).check(ViewAssertions.matches(ViewMatchers.withText("22 + 22 = 44.0")))
	}
}