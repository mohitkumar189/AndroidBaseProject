package mohit.attri.androidbaseproject.network;

import org.json.JSONObject;

/**
 * Created by mohitattri on 5/25/17.
 */

public interface UrlResponseListener {

    void onResponseReceived(ApiURLS.ApiId apiId, JSONObject jsonObjectResponse);

    void onResponseReceived(ApiURLS.ApiId apiId, String stringResponse);

    void onErrorResponse(ApiURLS.ApiId apiId, String errorData, int responseCode);
}

