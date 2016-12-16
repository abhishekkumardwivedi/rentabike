package com.crossover.jitensha.rentabike.app;


import com.crossover.jitensha.rentabike.PaymentActivity;
import com.crossover.jitensha.rentabike.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class PaymentActivityTextLengthTest {

    @Rule
    public ActivityTestRule<PaymentActivity> mActivityRule
            = new ActivityTestRule<>(PaymentActivity.class);

    /**
     * Test for payment form text entry maximum length
     * number sample is more then 16 char but entered value will be only 16 char
     * Code sample is more then 3 char but entered value will be only 3 char
     */
    @Test
    public void testRegisterTextEntryLength() {
        // Type text and then press the button.
        String string16char = "1234567812345678";

        String string3char = "123";

        String extralength = "9999999";

        String testNumberSample = string16char + extralength;

        String testCvcSample = string3char + extralength;

        onView(withId(R.id.text_number))
                .perform(typeText(testNumberSample), closeSoftKeyboard()); // more then 16 char input
        onView(withId(R.id.text_cvc))
                .perform(typeText(testCvcSample), closeSoftKeyboard()); // more then 3 char input

        onView(withId(R.id.text_number))
                .check(matches(withText(string16char))); // only initial 16 char will be accepted

        onView(withId(R.id.text_cvc))
                .check(matches(withText((string3char)))); // only initial 3 char will be accepted
    }
}