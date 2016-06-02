package com.homework.group.videoplayer.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.homework.group.videoplayer.R;
import com.homework.group.videoplayer.ui.detail.VideoDetailActivity;
import com.homework.group.videoplayer.utils.FindUtils;
import com.homework.group.videoplayer.utils.ImageUtils;


/**
 * desc:
 *
 * @author:young date:2016/4/6
 * time: 14:56
 * e-mail：1160415122@qq.com
 */
public class VideoListItemView extends RelativeLayout {

    private Context context;
    private TextView titleTv;
    private ImageView thumbIv;
    private TextView timeTv;
    private TextView descTv;
    private VideoInfo videoInfo;
    private RelativeLayout rl;

    public VideoListItemView(Context context) {
        super(context);
        this.context = context;
    }

    public VideoListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public VideoListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initComponent();
    }

    private void initComponent() {
        titleTv = (TextView) findViewById(R.id.tv_vedio_list_item_title);
        thumbIv = (ImageView) findViewById(R.id.iv_vedio_list_item_show);
        timeTv = (TextView) findViewById(R.id.tv_vedio_list_item_time);
        descTv = (TextView) findViewById(R.id.tv_vedio_list_item_desc);
        rl = (RelativeLayout) findViewById(R.id.rl);

//        rl.setOnClickListener(this);
    }

    public void resetView(VideoInfo videoInfo) {
        this.videoInfo = videoInfo;
        titleTv.setText(videoInfo.getTitle());
        Bitmap bitmap = ImageUtils.getVideoThumbnail(videoInfo.getPath(),60, 60, MediaStore.Images.Thumbnails.MICRO_KIND);
        if( bitmap != null ) {
            thumbIv.setImageBitmap(bitmap);
        }else{
            thumbIv.setImageResource(R.mipmap.video_default_icon);
        }
        timeTv.setText(videoInfo.getTime());
        descTv.setText(videoInfo.getDisplayName());
    }

}
