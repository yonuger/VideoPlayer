package com.homework.group.videoplayer.ui.menu.manager;

import com.kymjs.frame.presenter.ActivityPresenter;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/6/1  14:50
 */
public class VideoMenuManagerActivity extends ActivityPresenter<VideoMenuManagerDelegate> {

    @Override
    protected Class<VideoMenuManagerDelegate> getDelegateClass() {
        return VideoMenuManagerDelegate.class;
    }
}
