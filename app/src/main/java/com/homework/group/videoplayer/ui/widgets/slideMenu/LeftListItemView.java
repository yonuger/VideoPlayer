package com.homework.group.videoplayer.ui.widgets.slideMenu;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.homework.group.videoplayer.R;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/9  18:37
 */
public class LeftListItemView extends RelativeLayout{

    private TextView title;

    public LeftListItemView(Context context) {
        super(context);
    }

    public LeftListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LeftListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        title = (TextView) findViewById(R.id.tv_left_list_item);
    }


    public void resetView(String s) {
        title.setText(s);
    }
}
