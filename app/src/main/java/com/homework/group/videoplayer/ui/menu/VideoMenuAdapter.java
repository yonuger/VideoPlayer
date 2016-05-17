package com.homework.group.videoplayer.ui.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.homework.group.videoplayer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/10  08:52
 */
public class VideoMenuAdapter extends BaseExpandableListAdapter {

    private List<VideoMenu> mData = new ArrayList<VideoMenu>();
    private Context context;

    public VideoMenuAdapter(Context context, List<VideoMenu> mData){
        this.context = context;
        this.mData = mData;
    }

    @Override
    public int getGroupCount() {
        return mData.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mData.get(i).getmVideoInfoList().size();
    }

    @Override
    public Object getGroup(int i) {
        return mData.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return mData.get(i).getmVideoInfoList().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if( view == null ){
            view = LayoutInflater.from(context).inflate(R.layout.view_video_menu_group_item,viewGroup, false);
        }
        VideoMenuGroupItemView videoMenuGroupItemView = (VideoMenuGroupItemView)view;
        videoMenuGroupItemView.resetView(mData.get(i));
        return videoMenuGroupItemView;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if( view == null ){
            view = LayoutInflater.from(context).inflate(R.layout.view_video_menu_child_item,viewGroup, false);
        }
        VideoMenuChildItemView videoMenuChildItemView = (VideoMenuChildItemView)view;
        videoMenuChildItemView.resetView(mData.get(i).getmVideoInfoList().get(i1));
        return videoMenuChildItemView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
