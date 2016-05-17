package com.homework.group.videoplayer.ui.menu;

import android.widget.ExpandableListView;

import com.homework.group.videoplayer.R;
import com.homework.group.videoplayer.sql.DatabaseHelper;
import com.homework.group.videoplayer.utils.FindUtils;
import com.kymjs.frame.view.AppDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/10  08:44
 */
public class VideoMenuDelegate extends AppDelegate{

    private VideoMenuAdapter adapter;
    private List<VideoMenu> menuList;
    private List<VideoMenuInfo> menuInfoList;
    private ExpandableListView expendableListView;
    private DatabaseHelper dbHelper;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_video_menu;
    }

    @Override
    public void initWidget(){
        initData();
        expendableListView = get(R.id.elv_video_menu);
        expendableListView.setAdapter(adapter);
    }

    private void initData() {

        dbHelper = new DatabaseHelper(getActivity());

        FindUtils findUtils = new FindUtils(getActivity());
        if( menuList == null ){
            menuList = new ArrayList<VideoMenu>();
        }
        if( menuInfoList == null ){
            menuInfoList = new ArrayList<VideoMenuInfo>();
        }
        menuInfoList = dbHelper.scanVideoMenuInfo();

        VideoMenu videoMenu = new VideoMenu();
        videoMenu.setTitle("创建的影单");
        videoMenu.setmVideoInfoList(menuInfoList);
        menuList.add(videoMenu);
        adapter = new VideoMenuAdapter(getActivity(), menuList);

    }
}
