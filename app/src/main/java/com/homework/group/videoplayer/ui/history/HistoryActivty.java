package com.homework.group.videoplayer.ui.history;

import com.kymjs.frame.presenter.ActivityPresenter;


public class HistoryActivty extends ActivityPresenter<HistoryDelegate> {

    @Override
    protected Class<HistoryDelegate> getDelegateClass() {
        return HistoryDelegate.class;
    }
}
