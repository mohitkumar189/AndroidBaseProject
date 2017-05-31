package mohit.attri.androidbaseproject.network;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import mohit.attri.androidbaseproject.base_project_application.AndroidBaseProjectApplication;
import mohit.attri.androidbaseproject.utils.CommonUtils;
import mohit.attri.androidbaseproject.utils.Logger;

/**
 * Created by mohitattri on 5/25/17.
 */

public class VolleyNetworkRequestHandler {

    private static final String TAG = "VolleyNetworkRequestHandler";
    private static Context mctx;
    private static VolleyNetworkRequestHandler volleyNetworkRequestHandler;
    private static UrlResponseListener urlResponseListener;
    private static ProgressDialog progressDialog = null;

    // constructor is private so that object could not be created directly
    private VolleyNetworkRequestHandler() {

    }

    public static VolleyNetworkRequestHandler getInstance(Context context, UrlResponseListener urlResponseListener) {
        //   if (VolleyNetworkRequestHandler.mctx == null) {
        VolleyNetworkRequestHandler.mctx = context;
        progressDialog = CommonUtils.getInstance().getProgressDialog(mctx);
        //    }
        if (volleyNetworkRequestHandler == null) {
            volleyNetworkRequestHandler = new VolleyNetworkRequestHandler();
        }
        //    if (VolleyNetworkRequestHandler.urlResponseListener == null) {
        VolleyNetworkRequestHandler.urlResponseListener = urlResponseListener;
        //  }
        return volleyNetworkRequestHandler;
    }

    public void stringRequest(final boolean isProgressDialog, final String requestUrl, final ApiURLS.ApiId apiId, int requestMethod, final Map<String, String> postParams, final Map<String, String> headerParams) {
        Logger.info(TAG, "On stringRequest ===url: " + requestUrl + "request Method: " + requestMethod);
        if (isProgressDialog) {
            progressDialog = CommonUtils.getInstance().getProgressDialog(mctx);
            if (progressDialog != null)
                CommonUtils.getInstance().showProgressDialog(progressDialog, null, "Please wait...", false);
        }
        final StringRequest stringRequest = new StringRequest(requestMethod, requestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Logger.info(TAG, "Network response:" + response);
                if (isProgressDialog && progressDialog != null) {
                    CommonUtils.getInstance().cancelProgressDialog(progressDialog);
                }
                try {
                    if (response != null) {
                        if (VolleyNetworkRequestHandler.urlResponseListener != null) {
                            urlResponseListener.onResponseReceived(apiId, response);
                        }
                    } else {
                        if (VolleyNetworkRequestHandler.urlResponseListener != null) {

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (isProgressDialog && progressDialog != null) {
                    CommonUtils.getInstance().cancelProgressDialog(progressDialog);
                }
                String messageBody = null; // message received in the error
                int responseCode = 0;
                if (error != null) {
                    if (error.networkResponse != null) {
                        responseCode = error.networkResponse.statusCode; // to get response code
                        if (error.networkResponse.data != null) {
                            try {
                                messageBody = new String(error.networkResponse.data, "UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            if (VolleyNetworkRequestHandler.urlResponseListener != null) {
                                Logger.info(TAG, "===Object not null on error block===");
                                urlResponseListener.onErrorResponse(apiId, messageBody, responseCode);
                            } else {
                                Logger.info(TAG, "===Object null on error block===");
                            }
                        } else {
                            if (VolleyNetworkRequestHandler.urlResponseListener != null) {
                                Logger.info(TAG, "===Object not null on error block===");
                                urlResponseListener.onErrorResponse(apiId, messageBody, responseCode);
                            } else {
                                Logger.info(TAG, "===Object null on error block===");
                            }
                        }
                    }
                }
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                if (postParams != null) {
                    Logger.info(TAG, "Data for url: " + postParams.toString());
                    params.putAll(postParams);
                }
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                if (headerParams != null) {
                    Logger.info(TAG, "Data for headers: " + headerParams.toString());

                    for (Map.Entry<String, String> entry : headerParams.entrySet()) {
                        //    params.put(entry.getKey(), entry.getValue());
                        //     System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                    }
                    params.putAll(headerParams);
                }
                return params;
            }
        };
        AndroidBaseProjectApplication.getInstance().addToRequestQueue(stringRequest);
    }

    public void jsonRequest() {

    }
}
