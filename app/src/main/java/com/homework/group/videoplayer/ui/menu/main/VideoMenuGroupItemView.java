package com.homework.group.videoplayer.ui.menu.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.homework.group.videoplayer.R;
import com.homework.group.videoplayer.ui.menu.VideoMenu;
import com.homework.group.videoplayer.ui.menu.VideoMenuOperationActivity;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/10  09:09
 */
public class VideoMenuGroupItemView extends RelativeLayout implements View.OnClickListener {

    private TextView title;
    private ImageView image;
    private Activity activity;

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
        image = (ImageView) findViewById(R.id.iv_ideo_menu_group_manager);

        image.setOnClickListener(this);
    }

    public void resetView(VideoMenu videoMenu, Activity activity) {
        title.setText(videoMenu.getTitle());
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_ideo_menu_group_manager:
                Intent intent = new Intent(getContext(), VideoMenuOperationActivity.class);
                intent.putExtra("title", title.getText().toString());
                activity.startActivityForResult(intent, 100);
                break;
        }
    }
}
