package mohit.attri.androidbaseproject.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

import mohit.attri.androidbaseproject.R;
import mohit.attri.androidbaseproject.network.ApiURLS;
import mohit.attri.androidbaseproject.network.VolleyNetworkRequestHandler;
import mohit.attri.androidbaseproject.utils.Logger;

public class TestActivity extends BaseProjectActivity {

    Button button;
    VolleyNetworkRequestHandler volleyNetworkRequestHandle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    @Override
    protected void initViews() {

        button=(Button) findViewById(R.id.button);
    }

    @Override
    protected void initContext() {
        context = this;
        currentActivity = this;
    }

    @Override
    protected void initListners() {
        button.setOnClickListener(this);
    }

    @Override
    protected boolean isActionBar() {
        return true;
    }

    @Override
    protected boolean isHomeButton() {
        return true;
    }

    @Override
    protected boolean isNavigationView() {
        return true;
    }

    @Override
    protected boolean isTabs() {
        return true;
    }

    @Override
    protected boolean isFab() {
        return false;
    }

    @Override
    protected boolean isDrawerListener() {
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onClick(View view) {
        if(volleyNetworkRequestHandle ==null){
            volleyNetworkRequestHandle=VolleyNetworkRequestHandler.getInstance(context,this);

        }
        String url="https://www.google.co.in/";
        for(int i=0;i<5;i++){
            volleyNetworkRequestHandle.stringRequest(true,url, null,ApiURLS.REQUEST_GET,null,null);

        }
    }

    @Override
    public void onItemClickCallback(int position, int flag) {

    }

    @Override
    public void onResponseReceived(ApiURLS.ApiId apiId, JSONObject jsonObjectResponse) {

    }

    @Override
    public void onResponseReceived(ApiURLS.ApiId apiId, String stringResponse) {

        Logger.error("response:",stringResponse);
    }

    @Override
    public void onErrorResponse(ApiURLS.ApiId apiId, String errorData, int responseCode) {

    }
}
