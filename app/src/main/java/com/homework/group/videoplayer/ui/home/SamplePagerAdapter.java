package com.homework.group.videoplayer.ui.home;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.homework.group.videoplayer.R;
import com.homework.group.videoplayer.ui.widgets.pullRefresh.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

public class SamplePagerAdapter extends PagerAdapter implements AbsListView.OnScrollListener {

    protected Context context;
    private Activity activity;
    public static final int REFRESH_DELAY = 3000;
    static final String LOG_TAG = "SlidingTabsBasicFragment";

    private List<VideoInfo> videoInfoList;
    private ListView mListView;
    private VideoAdapter mAdapter;

    private ArrayList<String> mData;
    private boolean mHasRequestedMore;

    final String [] TITLES = {"电视剧", "电影", "动漫", "音乐", "其他"};

    public SamplePagerAdapter(Activity activity){
        this.activity = activity;
        this.context = activity.getApplicationContext();
    }

    public void setmData(List<VideoInfo> videoInfoList){
        this.videoInfoList = videoInfoList;
    }
    /**
     * @return the number of pages to display
     */
    @Override
    public int getCount() {
        return 5;
    }

    /**
     * @return true if the value returned from {@link #instantiateItem(ViewGroup, int)} is the
     * same object as the {@link View} added to the {@link ViewPager}.
     */
    @Override
    public boolean isViewFromObject(View view, Object o) {
        return o == view;
    }

    // BEGIN_INCLUDE (pageradapter_getpagetitle)
    /**
     * Return the title of the item at {@code position}. This is important as what this method
     * returns is what is displayed in the {@link }.
     * <p>
     * Here we construct one using the position value, but for real application the title should
     * refer to the item's contents.
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
    // END_INCLUDE (pageradapter_getpagetitle)

    /**
     * Instantiate the {@link View} which should be displayed at {@code position}. Here we
     * inflate a layout from the apps resources and then change the text view to signify the position.
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // Inflate a new layout from our resources
        View view = activity.getLayoutInflater().inflate(R.layout.activity_sgv,
                container, false);
        // Add the newly created View to the ViewPager
        final PullToRefreshView mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, REFRESH_DELAY);
            }
        });

        mListView = (ListView) view.findViewById(R.id.lv_vedio);

        final LayoutInflater layoutInflater = activity.getLayoutInflater();

        ImageView iv_01 = (ImageView)view.findViewById(R.id.iv_local_video_01);
        ImageView iv_02 = (ImageView)view.findViewById(R.id.iv_local_video_02);
        TextView title_01 = (TextView) view.findViewById(R.id.tv_local_video_title_01);
        TextView title_02 = (TextView) view.findViewById(R.id.tv_local_video_title_02);
        TextView desc_01 = (TextView) view.findViewById(R.id.tv_local_video_desc_01);
        TextView desc_02 = (TextView) view.findViewById(R.id.tv_local_video_desc_02);

//        if (mAdapter == null) {
//            mAdapter =  new VideoAdapter(activity, videoInfoList);
//        }
//
//        if (mData == null) {
//        }
//
//        mListView.setAdapter(mAdapter);
//        mListView.setOnScrollListener(this);

        container.addView(view);


        // Return the View
        return view;
    }

    /**
     * Destroy the item from the {@link ViewPager}. In our case this is simply removing the
     * {@link View}.
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        Log.i(LOG_TAG, "destroyItem() [position: " + position + "]");
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        Log.d("", "onScrollStateChanged:" + scrollState);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        Log.d("", "onScroll firstVisibleItem:" + firstVisibleItem +
                " visibleItemCount:" + visibleItemCount +
                " totalItemCount:" + totalItemCount);
        // our handling
        if (!mHasRequestedMore) {
            int lastInScreen = firstVisibleItem + visibleItemCount;
            if (lastInScreen >= totalItemCount) {
                Log.d("", "onScroll lastInScreen - so load more");
                mHasRequestedMore = true;
                onLoadMoreItems();
            }
        }
    }

    private void onLoadMoreItems() {
//        final ArrayList<String> sampleData = SampleData.generateSampleData();
//        for (String data : sampleData) {
//            mAdapter.add(data);
//        }
//        // stash all the data in our backing store
//        mData.addAll(sampleData);
        // notify the adapter that we can update now
        mAdapter.notifyDataSetChanged();
        mHasRequestedMore = false;
    }

}