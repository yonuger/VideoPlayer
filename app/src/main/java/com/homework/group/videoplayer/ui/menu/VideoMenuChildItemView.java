package com.homework.group.videoplayer.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.homework.group.videoplayer.R;



/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/10  09:20
 */
public class VideoMenuChildItemView extends RelativeLayout implements View.OnClickListener {

    private TextView title, number;
    private VideoMenuInfo videoMenuInfo;

    public VideoMenuChildItemView(Context context) {
        super(context);
    }

    public VideoMenuChildItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoMenuChildItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onFinishInflate(){
        super.onFinishInflate();
        initComponent();
    }

    private void initComponent() {
        title = (TextView) findViewById(R.id.tv_menu_child_title);
        number = (TextView) findViewById(R.id.tv_menu_child_number);
        this.setOnClickListener(this);
    }

    public void resetView(VideoMenuInfo videoMenuInfo) {
        title.setText(videoMenuInfo.getTitle());
        number.setText(videoMenuInfo.getNumber()+"条");
        this.videoMenuInfo = videoMenuInfo;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), VideoMenuDetailActivity.class);
        intent.putExtra("video_menu_id", videoMenuInfo.getId());
        getContext().startActivity(intent);
    }
}
