package com.homework.group.videoplayer.ui.history;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.homework.group.videoplayer.R;
import com.homework.group.videoplayer.sql.DatabaseHelper;
import com.homework.group.videoplayer.ui.home.VideoAdapter;
import com.homework.group.videoplayer.ui.home.VideoInfo;
import com.homework.group.videoplayer.utils.FindUtils;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivty extends Activity {

    private DatabaseHelper dbHelper;
    private List<VideoInfo> mVideoList;
    private SwipeMenuListView listView;
    private VideoAdapter adapter;

    private FindUtils findUtils;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_history_activty);

        dbHelper = new DatabaseHelper(this);
        findUtils = new FindUtils(this);
        Log.e("查看语句","我是观看历史");
        initData();
        listView = (SwipeMenuListView)findViewById(R.id.swipelv_history);
        listView.setAdapter(adapter);

        initSwipe();
    }

    private void initSwipe() {
        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(HistoryActivty.this);
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_drawer);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        listView.setMenuCreator(creator);

        // step 2. listener item click event
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
//                ApplicationInfo item = mAppList.get(position);
//                switch (index) {
//                    case 0:
//                        // open
//                        open(item);
//                        break;
//                    case 1:
//                        // delete
////					delete(item);
//                        mAppList.remove(position);
//                        mAdapter.notifyDataSetChanged();
//                        break;
//                }
            }
        });

        // set SwipeListener
        listView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        // other setting
//		listView.setCloseInterpolator(new BounceInterpolator());

        // test item long click
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                Toast.makeText(HistoryActivty.this, position + " long click", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mVideoList = new ArrayList<VideoInfo>();
        mVideoList = dbHelper.scanRecode();
        for (int i = 0; i < mVideoList.size(); i++) {
            mVideoList.get(i).setBitmap(findUtils.getBitmap(mVideoList.get(i).getTitle()));
        }
        adapter = new VideoAdapter(this, mVideoList);
        if( mVideoList.size() != 0 )
            Log.i("查看语句", mVideoList.get(0).getProcess()+"");
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                this.getResources().getDisplayMetrics());
    }
}
