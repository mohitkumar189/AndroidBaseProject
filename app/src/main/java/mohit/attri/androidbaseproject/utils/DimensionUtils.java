package mohit.attri.androidbaseproject.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import mohit.attri.androidbaseproject.base_project_application.AndroidBaseProjectApplication;

/**
 * Created by mohitattri on 5/24/17.
 */

public class DimensionUtils {

    public static float getDeviceDensity() {
        Context context = AndroidBaseProjectApplication.getInstance().getApplicationContext();
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return (metrics.densityDpi / 160f);

    }

    public static int dpToPixels(float dp) {
        Context context = AndroidBaseProjectApplication.getInstance().getApplicationContext();
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        Float px = dp * (metrics.densityDpi / 160f);
        return px.intValue();
    }

    public static float pixelsToDp(float px) {
        Context context = AndroidBaseProjectApplication.getInstance().getApplicationContext();
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }
    public static int toPixels(Context context, int dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }
}
