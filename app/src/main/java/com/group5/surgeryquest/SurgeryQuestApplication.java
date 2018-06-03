package com.group5.surgeryquest;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Copyright Nathan Chanthamontry 03/27/2018. All rights reserved.
 *
 *  Class that holds Global Variable needed for PIN number
 */

public class SurgeryQuestApplication extends Application {

    public static final String PIN_NOT_SET = "PIN_NOT_SET";

    // Get the parent pin from SharedPreferences
    public String getPinNum() {
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(this);
        return pref.getString("PinNum", PIN_NOT_SET);
    }

    // Set the parent pin in SharedPreferences.
    public void setPinNum(String pin){
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("PinNum", pin);
        edit.apply();
    }
}
