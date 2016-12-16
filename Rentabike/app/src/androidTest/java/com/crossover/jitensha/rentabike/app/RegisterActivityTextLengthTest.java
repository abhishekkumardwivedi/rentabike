package com.crossover.jitensha.rentabike.app;


import com.crossover.jitensha.rentabike.LoginActivity;
import com.crossover.jitensha.rentabike.R;
import com.crossover.jitensha.rentabike.RegisterActivity;

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
public class RegisterActivityTextLengthTest {

    @Rule
    public ActivityTestRule<RegisterActivity> mActivityRule
            = new ActivityTestRule<>(RegisterActivity.class);

    /**
     * Test for login form text entry maximum length
     * email sample is more then 128 char but entered value will be only 128 char
     * password sample is more then 64 char but entered value will be only 32 char
     */
    @Test
    public void testRegisterTextEntryLength() {
        // Type text and then press the button.
        String string128char = "thistextshouldnotbemorethen128character@themaxlength............" +
                "thistextshouldnotbemorethen128character@themaxlength............";

        String string32char = "thistextshouldnotbemorethen32ch.";

        String extralength = "xxxxxxxxxxxxxxxxxxxxxxxx";

        String testEmailSample = string128char + extralength;

        String testPasswordSample = string32char + extralength;

        onView(withId(R.id.edit_text_email))
                .perform(typeText(testEmailSample), closeSoftKeyboard()); // more then 128 char input
        onView(withId(R.id.edit_text_password))
                .perform(typeText(testPasswordSample), closeSoftKeyboard()); // more then 32 char input

        onView(withId(R.id.edit_text_email))
                .check(matches(withText((string128char)))); // only initial 128 char will be accepted

        onView(withId(R.id.edit_text_password))
                .check(matches(withText((string32char)))); // only initial 32 char will be accepted

    }
}