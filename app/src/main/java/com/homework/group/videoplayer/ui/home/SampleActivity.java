package com.homework.group.videoplayer.ui.home;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.ListView;

import com.homework.group.videoplayer.R;
import com.homework.group.videoplayer.ui.widgets.slideMenu.WithToggleSlideMenu;


public class SampleActivity extends Activity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private WithToggleSlideMenu mWithToggleSlideMenu;
    private SlidingTabsBasicFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setScrimColor(Color.parseColor("#000000"));
        mDrawerList = (ListView) findViewById(R.id.navdrawer);
        mWithToggleSlideMenu = new WithToggleSlideMenu(this, mDrawerLayout, mDrawerList);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            fragment = new SlidingTabsBasicFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mWithToggleSlideMenu.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mWithToggleSlideMenu.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mWithToggleSlideMenu.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onResume(){
        fragment.refresh();
        super.onResume();
    }
}
