package com.homework.group.videoplayer.ui.menu.main;

import android.content.Intent;

import com.kymjs.frame.presenter.ActivityPresenter;

public class VideoMenuActivity extends ActivityPresenter<VideoMenuDelegate> {

    @Override
    protected Class<VideoMenuDelegate> getDelegateClass() {
        return VideoMenuDelegate.class;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == 100) {
            int operation = data.getIntExtra("operation", 1);
            if( operation == 1 ){
                viewDelegate.createNewMenu();
            }else{
                viewDelegate.manageMenu();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume(){
        super.onResume();
        viewDelegate.refresh();
    }
}
