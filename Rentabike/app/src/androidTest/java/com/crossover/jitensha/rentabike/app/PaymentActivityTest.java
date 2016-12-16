package com.crossover.jitensha.rentabike.app;


import com.crossover.jitensha.rentabike.PaymentActivity;
import com.crossover.jitensha.rentabike.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.os.SystemClock;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class PaymentActivityTest {

    @Rule
    public IntentsTestRule<PaymentActivity> mActivity
            = new IntentsTestRule<PaymentActivity>(PaymentActivity.class);

    /**
     * Test for payment submit
     */
    @Test
    public void testPaymentSubmit() {
        // Type text and then press the button.
        onView(withId(R.id.text_number))
                .perform(typeText("1234123412341234"), closeSoftKeyboard());
        onView(withId(R.id.text_name))
                .perform(typeText("Hayao Miyazaki"), closeSoftKeyboard());
        onView(withId(R.id.text_expiry))
                .perform(typeText("11/20"), closeSoftKeyboard());
        onView(withId(R.id.text_cvc))
                .perform(typeText("754"), closeSoftKeyboard());
        onView(withId(R.id.button_submit)).perform(click());

        //wait for network delay
        SystemClock.sleep(3000);
    }

    /**
     * Test for payment submit
     */
    @Test
    public void testPaymentCancel() {
        // Type text and then press the button.
        onView(withId(R.id.text_number))
                .perform(typeText("1234567812345678"), closeSoftKeyboard());
        onView(withId(R.id.button_cancel)).perform(click());
    }
}