package com.homework.group.videoplayer.ui.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.homework.group.videoplayer.R;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/10  09:09
 */
public class VideoMenuGroupItemView extends RelativeLayout{

    private TextView title;

    public VideoMenuGroupItemView(Context context) {
        super(context);
    }

    public VideoMenuGroupItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoMenuGroupItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initComponent();
    }

    private void initComponent() {
        title = (TextView) findViewById(R.id.tv_menu_group_show);
    }

    public void resetView(VideoMenu videoMenu) {
        title.setText(videoMenu.getTitle());
    }
}
