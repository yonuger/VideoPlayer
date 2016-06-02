package com.homework.group.videoplayer.ui.menu.detail;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.homework.group.videoplayer.R;
import com.homework.group.videoplayer.ui.home.VideoInfo;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/10  10:26
 */
public class VideoMenuDetailItemView extends LinearLayout{

    private LinearLayout groupLl;
    private Context context;
    private View view;

    private TextView title, number, desc;
    private ImageView imageView;
    private Bitmap bitmap;

    public VideoMenuDetailItemView(Context context) {
        super(context);
    }

    public VideoMenuDetailItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoMenuDetailItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public VideoMenuDetailItemView(Context context, LinearLayout groupLl) {
        super(context);
        this.context=context;
        this.groupLl = groupLl;
        initViews(context);
    }

    private void initViews(Context context) {
        view=LayoutInflater.from(context).inflate(R.layout.view_menu_detail_item,null,false);
        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);

        number = (TextView) view.findViewById(R.id.tv_menu_detail_item_number);
        title = (TextView) view.findViewById(R.id.tv_menu_detail_item_title);
        desc = (TextView) view.findViewById(R.id.tv_menu_detail_item_desc);
        imageView = (ImageView) view.findViewById(R.id.iv_menu_detail_item_show);

        addView(view);
        groupLl.addView(this);

    }

    public void resetView(VideoInfo videoInfo, int i) {
        number.setText(Integer.toString(i+1));
        title.setText(videoInfo.getTitle());
        desc.setText(videoInfo.getDisplayName());
        imageView.setImageBitmap(videoInfo.getBitmap());
    }
}
