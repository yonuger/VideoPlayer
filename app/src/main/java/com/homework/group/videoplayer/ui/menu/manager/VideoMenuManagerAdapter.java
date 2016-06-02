package com.homework.group.videoplayer.ui.menu.manager;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.homework.group.videoplayer.R;
import com.homework.group.videoplayer.ui.menu.VideoMenuInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/6/1  15:01
 */
public class VideoMenuManagerAdapter extends BaseAdapter{

    private final Context context;
    private final List<VideoMenuInfo> mData;
    private final List<Long> mDataId = new ArrayList<Long>();
    private final Activity activity;
    private Object deletedItem;

    public VideoMenuManagerAdapter(Context context, Activity activity, List<VideoMenuInfo> mData){
        this.context = context;
        this.mData = mData;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if( view == null ){
            view = LayoutInflater.from(context).inflate(R.layout.view_video_menu_manager_item, viewGroup, false);
        }
        VideoMenuManagerItemView videoMenuManagerItemView = (VideoMenuManagerItemView)view;
        videoMenuManagerItemView.resetView(this, i, mData.get(i));
        return videoMenuManagerItemView;
    }

    public List<Long> getmDataId(){
        return mDataId;
    }
}

