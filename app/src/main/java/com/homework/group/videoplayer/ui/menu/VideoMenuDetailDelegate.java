package com.homework.group.videoplayer.ui.menu;

import android.widget.LinearLayout;

import com.homework.group.videoplayer.R;
import com.homework.group.videoplayer.sql.DatabaseHelper;
import com.homework.group.videoplayer.ui.home.VideoInfo;
import com.kymjs.frame.view.AppDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/10  09:57
 */
public class VideoMenuDetailDelegate extends AppDelegate{

    private LinearLayout contentLl;
    private DatabaseHelper dbHelper;

    private List<VideoInfo> videoInfoList;
    private long menuId;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_video_menu_detail;
    }

    @Override
    public void initWidget(){
        contentLl = get(R.id.ll_menu_detail_content);

        videoInfoList = new ArrayList<VideoInfo>();
        dbHelper = new DatabaseHelper(getActivity());

    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;

        dbHelper = new DatabaseHelper(getActivity());
        videoInfoList = dbHelper.requestMenuVideo(menuId);
        for (int i = 0; i < videoInfoList.size(); i++) {
            VideoMenuDetailItemView view = new VideoMenuDetailItemView(getActivity(), contentLl);
            view.resetView(videoInfoList.get(i), i);
        }
    }
}
