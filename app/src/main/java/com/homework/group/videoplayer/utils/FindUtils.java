package com.homework.group.videoplayer.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.util.Log;

import com.homework.group.videoplayer.ui.home.VideoInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * desc:
 *
 * @author:young date:2016/4/12
 * time: 21:03
 * e-mail：1160415122@qq.com
 */
public class FindUtils {

    private List<VideoInfo> mVideoInfoList = new ArrayList<VideoInfo>();
    private Activity mActivity;
    // MediaStore.Video.Thumbnails.DATA:视频缩略图的文件路径
    String[] thumbColumns = { MediaStore.Video.Thumbnails.DATA,
            MediaStore.Video.Thumbnails.VIDEO_ID };

    // MediaStore.Video.Media.DATA：视频文件路径；
    // MediaStore.Video.Media.DISPLAY_NAME : 视频文件名，如 testVideo.mp4
    // MediaStore.Video.Media.TITLE: 视频标题 : testVideo
    String[] mediaColumns = { MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DATA, MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.MIME_TYPE,
            MediaStore.Video.Media.DISPLAY_NAME };

    public FindUtils(Activity mActivity){
        this.mActivity = mActivity;
    }

    public void init(){
        Cursor cursor = mActivity.managedQuery(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                mediaColumns, null, null, null);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        ContentResolver contentResolver = mActivity.getContentResolver();

        if (cursor.moveToFirst()) {
            do {
                VideoInfo info = new VideoInfo();
                int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Video.Media._ID));
                Cursor thumbCursor = mActivity.managedQuery(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI,
                        thumbColumns, MediaStore.Video.Thumbnails.VIDEO_ID + "=" + id, null, null);
                if (thumbCursor.moveToFirst()) {
                    info.setImagePath(thumbCursor.getString(thumbCursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA)));
                }
                info.setPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));
                info.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE)));
                info.setTime("");
                info.setDisplayName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)));
                info.setBitmap(MediaStore.Video.Thumbnails.getThumbnail(contentResolver, id, MediaStore.Images.Thumbnails.MICRO_KIND, options));
//                info.setMimeType(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE)));
                Log.e("123456", cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));
                mVideoInfoList.add(info);
            } while (cursor.moveToNext());
        }
    }

    public List<VideoInfo> getmVideoInfoList() {
        return mVideoInfoList;
    }

    public Bitmap getBitmap(String title){
        Cursor cursor = mActivity.managedQuery(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                mediaColumns, null, null, null);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        ContentResolver contentResolver = mActivity.getContentResolver();

        if (cursor.moveToFirst()) {
            do {
                VideoInfo info = new VideoInfo();
                int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Video.Media._ID));
                Cursor thumbCursor = mActivity.managedQuery(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI,
                        thumbColumns, MediaStore.Video.Thumbnails.VIDEO_ID + "=" + id, null, null);
                if (thumbCursor.moveToFirst()) {
                    info.setImagePath(thumbCursor.getString(thumbCursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA)));
                }
                if( cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)).equals(title) ){
                    return MediaStore.Video.Thumbnails.getThumbnail(contentResolver, id, MediaStore.Images.Thumbnails.MICRO_KIND, options);
                }
            } while (cursor.moveToNext());
        }
        return null;
    }

}
