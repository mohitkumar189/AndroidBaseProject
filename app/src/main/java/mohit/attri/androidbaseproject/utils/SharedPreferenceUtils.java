package mohit.attri.androidbaseproject.utils;

import android.content.Context;
import android.content.SharedPreferences;

import mohit.attri.androidbaseproject.interfaces.AppConstants;

/**
 * Created by mohitattri on 5/24/17.
 */

public class SharedPreferenceUtils implements AppConstants {


    static SharedPreferenceUtils sharedPreferenceUtils;
    static SharedPreferences sharedPreferences;

    static Context context;

    private SharedPreferenceUtils() {

    }

    public static SharedPreferenceUtils getInstance(Context context) {
        if (sharedPreferenceUtils == null) {
            sharedPreferenceUtils = new SharedPreferenceUtils();
        }

        if (SharedPreferenceUtils.context == null) {
            SharedPreferenceUtils.context = context;
        }
        return sharedPreferenceUtils;
    }

    protected SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(AppConstants.APP_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    public void putString(String key, String value) {
        getSharedPreferences().edit().putString(key, value).commit();
    }

    public String getString(String key) {
        return getSharedPreferences().getString(key, "");
    }


    public void putBoolean(String key, boolean value) {
        getSharedPreferences().edit().putBoolean(key, value).commit();

    }
    public boolean getBoolean(String key) {
        return getSharedPreferences().getBoolean(key, false);
    }

    public void putInteger(String key, int value) {
        getSharedPreferences().edit().putInt(key, value).commit();
    }

    public int getInteger(String key) {
        return getSharedPreferences().getInt(key, 0);
    }

    public void clearAll() {
        getSharedPreferences().edit().clear().commit();
    }


}
