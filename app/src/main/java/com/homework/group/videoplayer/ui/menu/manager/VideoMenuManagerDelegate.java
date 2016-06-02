package com.homework.group.videoplayer.ui.menu.manager;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.homework.group.videoplayer.R;
import com.homework.group.videoplayer.sql.dao.MenuDao;
import com.homework.group.videoplayer.sql.dao.impl.MenuDaoImpl;
import com.homework.group.videoplayer.ui.menu.VideoMenuInfo;
import com.kymjs.frame.view.AppDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/6/1  14:51
 */
public class VideoMenuManagerDelegate extends AppDelegate implements View.OnClickListener {

    private List<VideoMenuInfo> menuInfoList;
    private MenuDao menuDao;
    private VideoMenuManagerAdapter adapter;

    private ListView listView;
    private LinearLayout deleteLl;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_video_menu_manager;
    }

    @Override
    public void initWidget(){
        initData();
        listView = get(R.id.lv_menu_manager);
        listView.setAdapter(adapter);
        deleteLl = get(R.id.ll_menu_manager);
        deleteLl.setOnClickListener(this);
    }

    private void initData() {

        menuDao = new MenuDaoImpl(getActivity());

        if( menuInfoList == null ){
            menuInfoList = new ArrayList<VideoMenuInfo>();
        }
        menuInfoList.clear();
        menuInfoList = menuDao.scanVideoMenuInfo();

        adapter = new VideoMenuManagerAdapter(getActivity(), getActivity(), menuInfoList);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_menu_manager:
                delete();
        }
    }

    /**
     * 删除影单
     */
    private void delete() {
        menuDao.deleteMenu(adapter.getmDataId());
        initWidget();
    }
}
