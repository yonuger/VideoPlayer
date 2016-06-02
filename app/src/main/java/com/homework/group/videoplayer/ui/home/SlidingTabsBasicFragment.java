/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.homework.group.videoplayer.ui.home;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.homework.group.videoplayer.R;
import com.homework.group.videoplayer.sql.DatabaseHelper;
import com.homework.group.videoplayer.sql.dao.VideoDao;
import com.homework.group.videoplayer.sql.dao.impl.VideoDaoImpl;
import com.homework.group.videoplayer.ui.detail.VideoDetailActivity;
import com.homework.group.videoplayer.utils.FindUtils;
import com.homework.group.videoplayer.ui.widgets.pullRefresh.PullToRefreshView;

import java.util.List;



public class SlidingTabsBasicFragment extends android.app.Fragment implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener {

    public static final int REFRESH_DELAY = 3000;

    private List<VideoInfo> videoInfoList;
    private ListView listView;
    private VideoAdapter mAdapter;

    private VideoDao mVideoDao;

    private boolean mHasRequestedMore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialer_sliding, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        mVideoDao = new VideoDaoImpl(getActivity());

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

        listView = (ListView) view.findViewById(R.id.lv_vedio);

        getVideoFile();
        mAdapter =  new VideoAdapter(getActivity(), videoInfoList);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(this);


    }

    public void refresh() {
        videoInfoList.clear();
        videoInfoList = mVideoDao.requestAllVideo();
        mAdapter =  new VideoAdapter(getActivity(), videoInfoList);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(this);
    }

    /**
     * 获取视频文件
     */
    private void getVideoFile() {
        videoInfoList = mVideoDao.requestAllVideo();
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(new Intent(getActivity(), VideoDetailActivity.class));
        intent.putExtra("videoInfo", videoInfoList.get(i));
        getActivity().startActivity(intent);
    }
}