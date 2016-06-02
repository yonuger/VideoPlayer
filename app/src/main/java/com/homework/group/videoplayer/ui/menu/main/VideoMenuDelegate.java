package com.homework.group.videoplayer.ui.menu.main;

import android.content.Intent;
import android.widget.ExpandableListView;

import com.homework.group.videoplayer.R;
import com.homework.group.videoplayer.sql.dao.MenuDao;
import com.homework.group.videoplayer.sql.dao.impl.MenuDaoImpl;
import com.homework.group.videoplayer.ui.menu.VideoMenu;
import com.homework.group.videoplayer.ui.menu.VideoMenuInfo;
import com.homework.group.videoplayer.ui.menu.manager.VideoMenuManagerActivity;
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
    private MenuDao menuDao;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_video_menu;
    }

    @Override
    public void initWidget(){
        initData();
        expendableListView = get(R.id.elv_video_menu);
        expendableListView.setAdapter(adapter);
        for(int i = 0; i < adapter.getGroupCount(); i++){
            expendableListView.expandGroup(i);
        }
    }

    private void initData() {

        menuDao = new MenuDaoImpl(getActivity());

        if( menuList == null ){
            menuList = new ArrayList<VideoMenu>();
        }
        menuList.clear();
        if( menuInfoList == null ){
            menuInfoList = new ArrayList<VideoMenuInfo>();
        }
        menuInfoList.clear();
        menuInfoList = menuDao.scanVideoMenuInfo();

        VideoMenu videoMenu = new VideoMenu();
        videoMenu.setTitle("创建的影单");
        videoMenu.setmVideoInfoList(menuInfoList);
        menuList.add(videoMenu);
        adapter = new VideoMenuAdapter(getActivity(), getActivity(), menuList);
    }

    public void  createNewMenu(){
        NewMenuDialog dialog = new NewMenuDialog(getActivity(), this);
        dialog.show();
    }

    public void refresh(){
        initWidget();
    }

    public void manageMenu() {
        getActivity().startActivity(new Intent(getActivity(), VideoMenuManagerActivity.class));
    }
}
