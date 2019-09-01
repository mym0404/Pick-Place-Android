package korea.seoul.pickple

import android.animation.ValueAnimator
import android.graphics.Color
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.math.abs
import kotlin.random.Random

@RunWith(AndroidJUnit4::class)
class ILoveInstrumentationTest {

    @Test
    fun `이테스트는현란합니다`() {
        val message = "Hi I am MJStudio.\nI am very boring now."

        val scenario = ActivityScenario.launch(MainActivity::class.java)

        scenario.onActivity {
        }

        scenario.moveToState(Lifecycle.State.RESUMED)

        val tvInteraction = onView(withId(R.id.textView)).perform(SetTextViewText(message))

        Thread.sleep((message.count() + 3) * 600L)

        tvInteraction.check(
            matches(
                allOf(
                    isDisplayed(),
                    withText(message)
                )
            )
        )
    }

    class SetTextViewText(private val message: String) : ViewAction {
        override fun getDescription(): String {
            return "This is just a joke"
        }

        override fun getConstraints(): Matcher<View> {
            return ViewMatchers.isAssignableFrom(TextView::class.java)
        }

        override fun perform(uiController: UiController?, view: View?) {
            (view as? TextView)?.let { tv ->
                tv.text = message
            }

            ValueAnimator.ofInt(0, message.count()).apply {

                addUpdateListener {
                    val currentLength = it.animatedValue as Int
                    (view as? TextView)?.let { tv ->
                        tv.text = message.take(currentLength)
                        tv.setTextColor(
                            Color.rgb(
                                Random.nextInt(256),
                                Random.nextInt(256),
                                Random.nextInt(256)
                            )
                        )
                        tv.textSize = abs(Random.nextFloat() * 30) + 14
                    }
                }

                duration = message.count() * 600L
                interpolator = LinearInterpolator()
                start()
            }
        }
    }
}
