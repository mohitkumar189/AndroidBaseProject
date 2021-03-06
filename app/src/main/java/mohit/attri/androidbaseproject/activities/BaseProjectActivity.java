package mohit.attri.androidbaseproject.activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import mohit.attri.androidbaseproject.R;
import mohit.attri.androidbaseproject.interfaces.AppConstants;
import mohit.attri.androidbaseproject.interfaces.ItemClickListener;
import mohit.attri.androidbaseproject.network.UrlResponseListener;
import mohit.attri.androidbaseproject.utils.ColoredSnackBar;
import mohit.attri.androidbaseproject.utils.DimensionUtils;

public abstract class BaseProjectActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener, UrlResponseListener, ItemClickListener {

    public Activity currentActivity;
    public Bundle bundle;
    public Context context;
    public Toolbar toolbar;
    public FloatingActionButton fab;
    public DrawerLayout drawer;
    public NavigationView navigationView;
    public TabLayout tabLayout;
    public CoordinatorLayout coordinatorLayout;
    public ProgressDialog pdialog;
    public Dialog customDialog;

    public static void log(String key, String value, int LogType) {
        if (LogType == Log.ASSERT) {
            Log.wtf(key, value);
        } else if (LogType == Log.DEBUG) {
            Log.d(key, value);
        } else if (LogType == Log.ERROR) {
            Log.e(key, value);
        } else if (LogType == Log.INFO) {
            Log.i(key, value);
        } else if (LogType == Log.VERBOSE) {
            Log.v(key, value);
        } else if (LogType == Log.WARN) {
            Log.w(key, value);
        }

    }

    protected abstract void initViews();

    protected abstract void initContext();

    protected abstract void initListners();

    protected abstract boolean isActionBar();

    protected abstract boolean isHomeButton();

    protected abstract boolean isNavigationView();

    protected abstract boolean isTabs();

    protected abstract boolean isFab();

    protected abstract boolean isDrawerListener();

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View viewHolder = getLayoutInflater().inflate(R.layout.activity_base_project, null);

        FrameLayout activityLayout = (FrameLayout) viewHolder.findViewById(R.id.activity_container);
        getLayoutInflater().inflate(layoutResID, activityLayout, true);

        super.setContentView(viewHolder);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.baseCoordinatorLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

// Setting Action Bar///
        if (isActionBar()) {
            setSupportActionBar(toolbar);
        } else {
            toolbar.setVisibility(View.GONE);
        }
// Setting HomeButton//
        if (isHomeButton()) {
            settingHomeButton();
        }
// Setting Tabs View//
        if (!isTabs()) {
            tabLayout.setVisibility(View.GONE);
        }
// Setting Navigation View//
        if (isNavigationView()) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            navigationView.setNavigationItemSelectedListener(this);
        } else {
            navigationView.setVisibility(View.GONE);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }

        if (isDrawerListener()) {
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
        }
// Setting Floating Action View//
        if (isFab()) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Snackbar snackbar = Snackbar.make(coordinatorLayout, "Replace with your own action", Snackbar.LENGTH_LONG);
                    ColoredSnackBar.info(snackbar).setAction("Action", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
                }
            });
        } else {
            fab.setVisibility(View.GONE);
        }
        initContext();
        initViews();
        initListners();

    }

    protected void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void settingHomeButton() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //    getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_btn);
    }

    public void setToolbarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public void removeHomeButton() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    public void toast(String text, boolean isLengthLong) {
        int time;
        if (isLengthLong) {
            time = Toast.LENGTH_LONG;
        } else {
            time = Toast.LENGTH_SHORT;
        }
        Toast.makeText(currentActivity, text, time).show();
    }

    public void toastTesting(String text, boolean isLengthLong) {
        int time;
        if (isLengthLong) {
            time = Toast.LENGTH_LONG;
        } else {
            time = Toast.LENGTH_SHORT;
        }
        Toast.makeText(currentActivity, text, time).show();
    }

    public void logTesting(String key, String value, int LogType) {
        if (LogType == Log.ASSERT) {
            Log.wtf(key, value);
        } else if (LogType == Log.DEBUG) {
            Log.d(key, value);
        } else if (LogType == Log.ERROR) {
            Log.e(key, value);
        } else if (LogType == Log.INFO) {
            Log.i(key, value);
        } else if (LogType == Log.VERBOSE) {
            Log.v(key, value);
        } else if (LogType == Log.WARN) {
            Log.w(key, value);
        }

    }


    public void switchFragment(Fragment fragment, boolean addToBackStack,
                               boolean add, String tag) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (!add) {

            ft.replace(R.id.activity_container, fragment, tag);
        } else {
            ft.add(R.id.activity_container, fragment, tag);
        }
        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.commit();
    }

    public void switchFragment(int containerId, Fragment fragment, boolean addToBackStack,
                               boolean add, String tag) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (!add) {

            ft.replace(containerId, fragment, tag);
        } else {
            ft.add(containerId, fragment, tag);
        }
        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.commit();
    }

    public void popFragment(String tag) {
        if (getSupportFragmentManager().getBackStackEntryCount() >= 0) {
            getSupportFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

    }

    public void startActivity(Activity activity, Class newclass) {
        Intent intent = new Intent(activity, newclass);
        startActivity(intent);
    }

    public void startActivity(Activity activity, Class newclass, Bundle bundle, boolean isResult, int requestCode, boolean animationRequired, int animationType) {
        Intent intent = new Intent(activity, newclass);
        if (bundle != null)

            intent.putExtras(bundle);
        if (!isResult && !animationRequired)
            startActivity(intent);
        else if (!isResult && animationRequired) {
            startActivity(intent);
            if (animationType == AppConstants.ANIMATION_SLIDE_LEFT) {
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
            } else if (animationType == AppConstants.ANIMATION_SLIDE_UP) {
                overridePendingTransition(R.anim.anim_slide_up, R.anim.anim_stopping_exiting_activity);
            }

        } else if (isResult && animationRequired) {
            startActivityForResult(intent, requestCode);
            if (animationType == AppConstants.ANIMATION_SLIDE_LEFT) {
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
            } else if (animationType == AppConstants.ANIMATION_SLIDE_UP) {
                overridePendingTransition(R.anim.anim_slide_up, R.anim.anim_stopping_exiting_activity);
            }
        } else
            startActivityForResult(intent, requestCode);
    }

    public void progressDialog(Context context, String title, String message, boolean cancelable, boolean isTitle) {
        if (pdialog == null) {
            pdialog = new ProgressDialog(context);
        }

        if (isTitle) {
            pdialog.setTitle(title);
        }

        pdialog.setMessage(message);

        if (!cancelable) {
            pdialog.setCancelable(false);
        }

        if (!pdialog.isShowing()) {
            pdialog.show();
        }

    }

    public void cancelProgressDialog() {
        pdialog.cancel();
    }

    protected Dialog creatingDialog(Context context, boolean isCancelableBack, boolean isCancelableoutside, View view, int height, int width) {
        customDialog = new Dialog(context, R.style.dialogTheme);
        //  dialog.setCancelable(isCancelableBack);
        if (view.getParent() != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            viewGroup.removeView(view);
        }
        customDialog.setCanceledOnTouchOutside(isCancelableoutside);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


        customDialog.setContentView(view);
        WindowManager.LayoutParams wmlp = customDialog.getWindow().getAttributes();
        customDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        wmlp.gravity = Gravity.CENTER;

        customDialog.show();

        customDialog.getWindow().setLayout(DimensionUtils.toPixels(context, width), DimensionUtils.toPixels(context, height));
        customDialog.getWindow().setAttributes(wmlp);
        return customDialog;

    }

    protected Dialog createCustomDialog(Context context, boolean isCancelable, View view) {
        if (view.getParent() != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            viewGroup.removeView(view);
        }
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setContentView(view);
        WindowManager.LayoutParams wmlp = customDialog.getWindow().getAttributes();
        customDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        wmlp.gravity = Gravity.CENTER;
        customDialog.show();
        return customDialog;
    }

    protected void cancelCustomDialog() {
        if (customDialog != null) {
            customDialog.cancel();
        }
    }

    protected void cancelCustomDialog(Dialog cd) {
        if (cd != null) {
            cd.cancel();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {

            finish();
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void askForPermission(int permissionRequestCode, String... permissions) {

    }

    public void hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // hiding status bar
    }
}