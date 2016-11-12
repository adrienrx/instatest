package net.sparkeek.instatest.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by adrienrx on 29/09/2016.
 */

public class ToolBox {
    public static final String PREFS_DISPLAY_NAME = "fidprefs";
    public static SharedPreferences getSharedPref(Context context){
        return context.getSharedPreferences(PREFS_DISPLAY_NAME, MODE_PRIVATE);
    }

    public static String getUserToken(Context context){
        return getSharedPref(context).getString("insta_key", "error");
    }
}
