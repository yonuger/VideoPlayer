package com.homework.group.videoplayer.ui.detail;

import android.widget.EditText;
import android.widget.TextView;

import com.homework.group.videoplayer.R;
import com.kymjs.frame.view.AppDelegate;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/9  19:45
 */
public class VideoDetailDelegate extends AppDelegate{


    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_video_detail;
    }

    @Override
    public void initWidget() {

    }

    public String getText(int id) {
        EditText et = get(id);
        return et.getText().toString();
    }

    public void setResult(String name, String age) {
        TextView textView = get(R.id.text);
        textView.setText(String.format("姓名+ %s + 年龄 + %s", name, age));
    }
}
