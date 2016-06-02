package com.homework.group.videoplayer.ui.history;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.homework.group.videoplayer.sql.dao.HistoryDao;
import com.homework.group.videoplayer.sql.dao.impl.HistoryDaoImpl;
import com.homework.group.videoplayer.ui.detail.VideoDetailActivity;
import com.homework.group.videoplayer.ui.home.VideoAdapter;
import com.homework.group.videoplayer.sql.DatabaseHelper;
import com.homework.group.videoplayer.ui.home.VideoInfo;
import com.homework.group.videoplayer.utils.FindUtils;
import com.kymjs.frame.view.AppDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/9  15:12
 */
public class HistoryDelegate extends AppDelegate implements AdapterView.OnItemClickListener {

    private List<VideoInfo> mVideoList;
    private SwipeMenuListView listView;
    private VideoAdapter adapter;

    private FindUtils findUtils;
    private HistoryDao historyDao;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_history_activty;
    }

    @Override
    public void initWidget() {
        super.initWidget();

        historyDao = new HistoryDaoImpl(getActivity());
        findUtils = new FindUtils(getActivity());

        initData();
        listView = (SwipeMenuListView)get(R.id.swipelv_history);
        listView.setAdapter(adapter);

        initSwipe();
    }

    private void initSwipe() {
        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.mipmap.delete_icon);
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
                switch (index) {
                    case 0:
                        historyDao.deleteHistory(mVideoList.get(position));
                        mVideoList.remove(position);
                        adapter.notifyDataSetChanged();
                        break;
                }
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

        listView.setOnItemClickListener(this);
        // other setting
//		listView.setCloseInterpolator(new BounceInterpolator());
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mVideoList = new ArrayList<VideoInfo>();
        mVideoList = historyDao.requestAllHistory();
        for (int i = 0; i < mVideoList.size(); i++) {
            mVideoList.get(i).setBitmap(findUtils.getBitmap(mVideoList.get(i).getTitle()));
        }
        adapter = new VideoAdapter(getActivity(), mVideoList);
        if( mVideoList.size() != 0 )
            Log.i("查看语句", mVideoList.get(0).getProcess()+"");
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getActivity().getResources().getDisplayMetrics());
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent intent = new Intent(new Intent(getActivity(), VideoDetailActivity.class));
        intent.putExtra("videoInfo", mVideoList.get(i));
        getActivity().startActivity(intent);

    }
}
