package mohit.attri.androidbaseproject.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import mohit.attri.androidbaseproject.interfaces.AppConstants;

/**
 * Created by mohitattri on 5/24/17.
 */

public class Validator {
    private static Validator validator;

    private Validator() {

    }

    public static Validator getInstance() {
        if (validator == null) {
            validator = new Validator();
        }
        return validator;
    }

    public final boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public final boolean isValidEmail(String email) {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()) {
            return true;
        }
        return false;
    }

    public final boolean isValidMobile(String number) {
        if (number.length() != 10) {
            return false;
        }
        if (number.charAt(0) == '0') {
            return false;
        }
        return true;
    }

    public final boolean isValidName(String name) {
        if (name.length() != 4) {
            return false;
        }
        return true;
    }

    public final boolean isValidPassword(String password) {
        if (password.length() != 4) {
            return false;
        }
        return true;
    }
    public final boolean isValidString(String val) {
        return (val != null && !"".equals(val) && !"null".equals(val.toLowerCase()));
    }

    public final boolean isStringSame(String str1, String str2) {
        if (str1 == null && str2 == null) return true;
        if (str1 != null && str1.equals(str2)) return true;
        return str2 != null && str2.equals(str1);
    }

    public final boolean isExternalURL(String str) {
        if (str == null) return false;
        return str.indexOf("http://") == 0 || str.indexOf("https://") == 0 || str.indexOf("www.") == 0;
    }

    public static boolean isLogin(Context context) {

        return SharedPreferenceUtils.getInstance(context).getSharedPreferences().getBoolean(AppConstants.IS_LOGIN, false);
    }
}
