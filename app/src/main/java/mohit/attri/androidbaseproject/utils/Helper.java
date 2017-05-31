package mohit.attri.androidbaseproject.utils;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.Random;

/**
 * Created by mohitattri on 5/24/17.
 */

public class Helper {

    private static Helper helper;

    private Helper() {

    }

    public static Helper getInstance() {
        if (helper == null) {
            helper = new Helper();
        }
        return helper;
    }

    public final long generateRandomNoTimeMillis() {

        int max = 9999;
        int min = 1000;
        Date date = new Date();
        Random random = new Random();
        Log.e("random no", "" + date.getTime() + random.nextInt((max - min) + 1) + min);

        return date.getTime() + random.nextInt((max - min) + 1) + min;

    }

    public final long generateRandomPassword() {

        int max = 99999;
        int min = 10000;
        Date date = new Date();
        Random random = new Random();
        Log.e("random no", "" + date.getTime() + random.nextInt((max - min) + 1) + min);

        return date.getTime() + random.nextInt((max - min) + 1) + min;

    }


    public final byte[] getImageBytes(Bitmap bmp) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return imageBytes;
    }

    public static Integer getInteger(String val) {
        Integer integer = null;
        try {
            integer = Integer.parseInt(val);
        } catch (Exception e) {

        }
        return integer;
    }

    public static Boolean getBoolean(String val) {
        try {
            return Boolean.parseBoolean(val);
        } catch (Exception e) {

        }
        return null;
    }

    public static Double getDouble(String val) {
        try {
            return Double.parseDouble(val);
        } catch (Exception e) {

        }
        return null;
    }

    public static Long getLong(String val) {
        try {
            return Long.parseLong(val);
        } catch (Exception e) {

        }
        return null;
    }

    public static boolean isValidString(String val) {
        return (val != null && !"".equals(val) && !"null".equals(val.toLowerCase()));
    }

    public static boolean isStringSame(String str1, String str2) {
        if (str1 == null && str2 == null) return true;
        if (str1 != null && str1.equals(str2)) return true;
        return str2 != null && str2.equals(str1);
    }

    public static boolean isExternalURL(String str) {
        if (str == null) return false;
        return str.indexOf("http://") == 0 || str.indexOf("https://") == 0 || str.indexOf("www.") == 0;
    }

}
