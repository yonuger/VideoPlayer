package com.homework.group.videoplayer.ui.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.homework.group.videoplayer.R;
import com.homework.group.videoplayer.sql.dao.VideoDao;
import com.homework.group.videoplayer.sql.dao.impl.VideoDaoImpl;
import com.homework.group.videoplayer.ui.home.SampleActivity;
import com.homework.group.videoplayer.ui.home.VideoInfo;
import com.homework.group.videoplayer.utils.FindUtils;

import java.util.List;

public class SplashActivity extends Activity {

    private List<VideoInfo> videoInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        FindUtils findUtils = new FindUtils(this);
        findUtils.init();
        VideoDao mVideoDao = new VideoDaoImpl(this);
        videoInfoList = findUtils.getmVideoInfoList();
        for (int i = 0; i < videoInfoList.size(); i++) {
            mVideoDao.insertVideo(videoInfoList.get(i));
        }

        new Thread()
        {public void run()
        {try {sleep(2000);     //等待三秒,自动进入软件主窗口
            Intent intent = new Intent();
            intent.setClass(SplashActivity.this, SampleActivity.class);
            startActivity(intent);        }
        catch (Exception e) {
            e.printStackTrace();
        }
            //progressDialog.dismiss();
        }
        }.start();
    }
}
