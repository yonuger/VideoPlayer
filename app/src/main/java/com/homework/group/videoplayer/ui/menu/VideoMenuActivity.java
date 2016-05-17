package com.homework.group.videoplayer.ui.menu;

import com.kymjs.frame.presenter.ActivityPresenter;

public class VideoMenuActivity extends ActivityPresenter<VideoMenuDelegate> {

    @Override
    protected Class<VideoMenuDelegate> getDelegateClass() {
        return VideoMenuDelegate.class;
    }
}
