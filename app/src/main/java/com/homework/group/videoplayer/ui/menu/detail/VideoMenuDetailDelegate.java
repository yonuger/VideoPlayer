package com.homework.group.videoplayer.ui.menu.detail;

import android.widget.LinearLayout;

import com.homework.group.videoplayer.R;
import com.homework.group.videoplayer.sql.dao.MenuDao;
import com.homework.group.videoplayer.sql.dao.impl.MenuDaoImpl;
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

    private List<VideoInfo> videoInfoList;
    private long menuId;
    private MenuDao menuDao;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_video_menu_detail;
    }

    @Override
    public void initWidget(){
        contentLl = get(R.id.ll_menu_detail_content);

        videoInfoList = new ArrayList<VideoInfo>();
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;

        menuDao = new MenuDaoImpl(getActivity());
        videoInfoList = menuDao.requestMenuVideoById(menuId);
        for (int i = 0; i < videoInfoList.size(); i++) {
            VideoMenuDetailItemView view = new VideoMenuDetailItemView(getActivity(), contentLl);
            view.resetView(videoInfoList.get(i), i);
        }
    }
}
