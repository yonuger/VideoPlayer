package com.homework.group.videoplayer.ui.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/10  08:55
 */
public class VideoMenu {

    private long id;
    private String title;
    private List<VideoMenuInfo> mVideoMenuInfoList = new ArrayList<VideoMenuInfo>();

    public List<VideoMenuInfo> getmVideoInfoList(){
        return mVideoMenuInfoList;
    }

    public void setmVideoInfoList(List<VideoMenuInfo> mVideoInfoList){
        this.mVideoMenuInfoList = mVideoInfoList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
