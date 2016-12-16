package com.crossover.jitensha.rentabike.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by abhishek on 15/12/16.
 */

public class SessionManager {
    // Shared preferences file name
    private static final String PREF_NAME = "rentabike_preferences";
    private static final String KEY_TOKEN = "token";
    private static String TAG = SessionManager.class.getSimpleName();
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    public SessionManager(Context context) {
        this.context = context;
        pref = this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Save access token
     */
    public void saveAccessToken(String token) {
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }

    /**
     * Get access token
     */
    public String getAccessToken() {
        return pref.getString(KEY_TOKEN, null);
    }
}
