package com.homework.group.videoplayer.ui.widgets.slideMenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.homework.group.videoplayer.R;
import com.homework.group.videoplayer.ui.history.HistoryActivty;
import com.homework.group.videoplayer.ui.menu.VideoMenuActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * desc:
 *
 * @author:young date:2016/03/19
 * time: 11:49
 * e-mail：1160415122@qq.com
 */
public class WithToggleSlideMenu {

    private LeftListAdapter adapter;
    private Context context;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerArrowDrawable drawerArrow;
    private boolean drawerArrowColor;

    public WithToggleSlideMenu(final Context context, DrawerLayout mDrawerLayout, ListView mDrawerList){

        this.context = context;
        this.mDrawerList = mDrawerList;
        this.mDrawerLayout = mDrawerLayout;

        final Activity activity = (Activity)context;

        drawerArrow = new DrawerArrowDrawable(context) {
            @Override
            public boolean isLayoutRtl() {
                return false;
            }
        };

        mDrawerToggle = new ActionBarDrawerToggle(activity, mDrawerLayout,
                drawerArrow, R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                activity.invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                activity.invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


        List<String> list = new ArrayList<String>();
        list.add("历史");
        list.add("影单");
        list.add("皮肤");

        adapter = new LeftListAdapter(context);
        adapter.setMdata(list);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        context.startActivity(new Intent(context, HistoryActivty.class));
                        break;
                    case 1:
                        context.startActivity(new Intent(context, VideoMenuActivity.class));
                        break;
                    case 2:
                        if (drawerArrowColor) {
                            drawerArrowColor = false;
                            drawerArrow.setColor(R.color.ldrawer_color);
                        } else {
                            drawerArrowColor = true;
                            drawerArrow.setColor(R.color.drawer_arrow_second_color);
                        }
                        mDrawerToggle.syncState();
                        break;
                }

            }
        });
    }

    public void syncState(){
        mDrawerToggle.syncState();
    }

    public void onConfigurationChanged(Configuration newConfig){
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        }
    }
}
