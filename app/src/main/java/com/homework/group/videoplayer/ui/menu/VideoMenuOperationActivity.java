package com.homework.group.videoplayer.ui.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.homework.group.videoplayer.R;
import com.homework.group.videoplayer.ui.menu.detail.VideoMenuDetailActivity;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/30  23:49
 */
public class VideoMenuOperationActivity extends Activity implements View.OnClickListener {

    private LinearLayout createLl, managerLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_menu_operation);

        createLl = (LinearLayout) findViewById(R.id.ll_menu_operation_create);
        managerLl = (LinearLayout) findViewById(R.id.ll_menu_operation_manager);

        createLl.setOnClickListener(this);
        managerLl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, VideoMenuDetailActivity.class);
        switch (view.getId()){
            case R.id.ll_menu_operation_create:
                intent.putExtra("operation", 1);
                setResult(100, intent);
                finish();
                break;
            case R.id.ll_menu_operation_manager:
                intent.putExtra("operation", 2);
                setResult(100, intent);
                finish();
                break;
        }
    }
}
