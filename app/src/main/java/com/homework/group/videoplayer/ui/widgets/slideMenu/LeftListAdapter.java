package com.homework.group.videoplayer.ui.widgets.slideMenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.homework.group.videoplayer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/9  18:26
 */
public class LeftListAdapter extends BaseAdapter {

    private Context context;
    private List<String> mdata;

    public LeftListAdapter(Context context){
        this.context = context;
        mdata = new ArrayList<String>();
    }

    public void setMdata(List<String> mdata){
        if( mdata != null )
            this.mdata = mdata;
    }
    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public Object getItem(int i) {
        return mdata.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if( view == null ){
            view = LayoutInflater.from(context).inflate(R.layout.view_left_list_item, viewGroup, false);
        }
        LeftListItemView listItemView = (LeftListItemView)view;
        listItemView.resetView(mdata.get(i));
        return listItemView;
    }
}
