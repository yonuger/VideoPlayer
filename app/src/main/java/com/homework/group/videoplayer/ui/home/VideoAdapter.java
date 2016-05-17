package com.homework.group.videoplayer.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.homework.group.videoplayer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:
 *
 * @author:young date:2016/4/6
 * time: 14:33
 * e-mail：1160415122@qq.com
 */
public class VideoAdapter extends BaseAdapter {

    private Context context;

    private List<VideoInfo> videoInfoList = new ArrayList<VideoInfo>();

    public VideoAdapter(Context context, List<VideoInfo> list) {
        this.context = context;
        this.videoInfoList = list;
    }

    @Override
    public int getCount() {
        return videoInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return videoInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if( convertView == null ){
            convertView = LayoutInflater.from(context).inflate(R.layout.view_vedio_list_item, parent, false);
        }
        VideoListItemView view = (VideoListItemView)convertView;
        view.resetView(videoInfoList.get(position));

        return view;
    }
}
