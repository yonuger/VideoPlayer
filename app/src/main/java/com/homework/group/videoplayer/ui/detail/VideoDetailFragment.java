package com.homework.group.videoplayer.ui.detail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.homework.group.videoplayer.R;
import com.homework.group.videoplayer.ui.home.VideoInfo;

import io.vov.vitamio.utils.Log;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/25  14:58
 */
public class VideoDetailFragment extends Fragment {

    private VideoInfo mVideoInfo;

    private TextView title;
    private TextView desc;
    private ImageView head;
    private VideoInfo videoInfo;

    public VideoDetailFragment(VideoInfo videoInfo) {
        this.mVideoInfo = videoInfo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        title = (TextView) view.findViewById(R.id.iv_video_detail_title);
        desc = (TextView) view.findViewById(R.id.iv_video_detail_desc);
        head = (ImageView) view.findViewById(R.id.iv_video_detail_head);

        title.setText(mVideoInfo.getTitle());
        if( mVideoInfo.getDisplayName().isEmpty() ){
            mVideoInfo.setDisplayName("暂时没有什么可描述");
        }else{
            mVideoInfo.setDisplayName(mVideoInfo.getDisplayName());
        }
        desc.setText(mVideoInfo.getDisplayName());
        Bitmap bitmap = BitmapFactory.decodeFile(mVideoInfo.getImagePath());
        if( bitmap == null ){
            head.setBackgroundResource(R.mipmap.detail);
        }else{
            Drawable drawable = new BitmapDrawable(getContext().getResources(), bitmap);
            head.setBackground(drawable);
        }
    }

    public void setVideoInfo(VideoInfo mVideoInfo) {
        this.videoInfo = mVideoInfo;
        title.setText(videoInfo.getTitle());
        if( videoInfo.getDisplayName().isEmpty() ){
            videoInfo.setDisplayName("暂时没有什么可描述");
        }else{
            videoInfo.setDisplayName(videoInfo.getDisplayName());
        }
        desc.setText(videoInfo.getDisplayName());
        Bitmap bitmap = BitmapFactory.decodeFile(mVideoInfo.getImagePath());
        if( bitmap == null ){
            head.setBackgroundResource(R.mipmap.detail);
        }else{
            Drawable drawable = new BitmapDrawable(getContext().getResources(), bitmap);
            head.setBackground(drawable);
        }
    }
}
