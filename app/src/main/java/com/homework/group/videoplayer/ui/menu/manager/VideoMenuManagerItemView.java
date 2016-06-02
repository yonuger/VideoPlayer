package com.homework.group.videoplayer.ui.menu.manager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.homework.group.videoplayer.R;
import com.homework.group.videoplayer.ui.menu.VideoMenuInfo;

import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/6/1  15:10
 */
public class VideoMenuManagerItemView extends LinearLayout implements View.OnClickListener {
    private int position;

    private TextView nameTv, descTv;
    private CheckBox checkBox;
    private VideoMenuManagerAdapter adapter;
    private VideoMenuInfo videoMenuInfo;

    public VideoMenuManagerItemView(Context context) {
        super(context);
    }

    public VideoMenuManagerItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoMenuManagerItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onFinishInflate(){
        super.onFinishInflate();
        initComponent();
    }

    private void initComponent() {
        checkBox = (CheckBox) findViewById(R.id.cb_menu_manager);

        nameTv = (TextView) findViewById(R.id.tv_menu_manager_item_name);
        descTv = (TextView) findViewById(R.id.tv_menu_manager_item_desc);

        checkBox.setOnClickListener(this);
    }

    public void resetView(VideoMenuManagerAdapter adapter, int i, VideoMenuInfo videoMenuInfo) {
        this.adapter = adapter;
        this.position = i;
        this.videoMenuInfo = videoMenuInfo;

        nameTv.setText(videoMenuInfo.getTitle());
        descTv.setText(videoMenuInfo.getNumber()+"条");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cb_menu_manager:
                if( checkBox.isChecked() ){
                    adapter.getmDataId().add(videoMenuInfo.getId());
                }else{
                    try {
                        adapter.getmDataId().remove(videoMenuInfo.getId());
                    }catch (Exception e){

                    }
                }
        }
    }
}
