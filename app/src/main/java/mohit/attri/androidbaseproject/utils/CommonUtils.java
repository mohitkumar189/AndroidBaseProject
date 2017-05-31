package mohit.attri.androidbaseproject.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

import mohit.attri.androidbaseproject.interfaces.AppConstants;

/**
 * Created by mohitattri on 5/25/17.
 */

public class CommonUtils implements AppConstants {
    private static final String TAG = "CommonUtils";
    private Dialog customDialog;
    private static CommonUtils commonUtils = null;

    private CommonUtils() {

    }

    public static final CommonUtils getInstance() {
        if (commonUtils == null) {
            commonUtils = new CommonUtils();
        }
        return commonUtils;
    }

    public final ProgressDialog getProgressDialog(Context ctx) {
        Logger.info(TAG, "Progress dialog is returned:" + ctx);
        return new ProgressDialog(ctx);
    }

    public final void showProgressDialog(ProgressDialog pdialog, String title, String message, boolean cancelable) {
        if (pdialog != null && !pdialog.isShowing()) {
            if (Validator.getInstance().isValidString(title)) pdialog.setTitle(title);
            if (Validator.getInstance().isValidString(message)) pdialog.setMessage(message);
            if (!cancelable) pdialog.setCancelable(false);
            pdialog.show();
            Logger.info(TAG, "progress dialog is showing");
        }
    }

    public final void cancelProgressDialog(ProgressDialog pdialog) {
        if (pdialog != null) pdialog.dismiss();
    }
}
