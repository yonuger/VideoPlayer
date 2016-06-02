package com.homework.group.videoplayer.sql.dao.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.homework.group.videoplayer.sql.dao.VideoDao;
import com.homework.group.videoplayer.ui.home.VideoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/25  15:12
 */
public class VideoDaoImpl extends CommonImpl implements VideoDao{

    public VideoDaoImpl(Context context){
        super(context);
    }

    /**
     * 查看所有的视频
     * @return
     */
    public List<VideoInfo> requestAllVideo(){
        List<VideoInfo> mList = new ArrayList<VideoInfo>();
        String sql = "select * from video";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            VideoInfo videoInfo = new VideoInfo();
            videoInfo.setId(cursor.getLong(cursor.getColumnIndex("video_id")));
            videoInfo.setTitle(cursor.getString(cursor.getColumnIndex("video_name")));
            videoInfo.setPath(cursor.getString(cursor.getColumnIndex("video_path")));
            videoInfo.setDisplayName(cursor.getString(cursor.getColumnIndex("video_desc")));
            Log.i("查看语句requestAllVideo", videoInfo.getDisplayName());
            mList.add(videoInfo);
        }
        cursor.close();
        db.close();
        return mList;
    }

    @Override
    public VideoInfo requestVideoDetailById(long id) {
        String sql = "select * from video where video_id = '" + id + "'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        Log.i("查看语句hasRecode", sql);
        Log.i("查看语句hasRecode", cursor.getCount()+"");
        while (cursor.moveToNext()) {
            VideoInfo videoInfo = new VideoInfo();
            videoInfo.setId(cursor.getLong(cursor.getColumnIndex("video_id")));
            videoInfo.setTitle(cursor.getString(cursor.getColumnIndex("video_name")));
            videoInfo.setPath(cursor.getString(cursor.getColumnIndex("video_path")));
            videoInfo.setDisplayName(cursor.getString(cursor.getColumnIndex("video_desc")));
            videoInfo.setImagePath(cursor.getString(cursor.getColumnIndex("video_bitmap")));
            Log.i("查看语句", videoInfo.getTitle());
            Log.i("查看语句", videoInfo.getPath());
            return videoInfo;
        }
        return null;
    }

    /**
     * 插入视频或者说更新视频
     * @param mVideoInfo
     */
    public void insertVideo(VideoInfo mVideoInfo) {
        String sql = "";
        if( !hasVideo(mVideoInfo) ) {
            sql = "insert into video(video_name, video_desc, video_path) Values('" + mVideoInfo.getTitle()+ "', '" + mVideoInfo.getDisplayName()
                    + "', '" + mVideoInfo.getPath() + "')";
        }else{
            sql = "update video set video_desc = '"+ mVideoInfo.getDisplayName() + "', video_path = '"+ mVideoInfo.getPath()
                    +"' where video_name = '"+ mVideoInfo.getTitle() + "'";
        }
        Log.i("查看语句insertVideo", sql);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql);
        db.close();
        Log.i("查看语句", "添加成功");
    }

    /**
     *
     *判断是否已有视频
     * @param mVideoInfo
     * @return  true为有, false没有
     */
    public boolean hasVideo(VideoInfo mVideoInfo) {
        String sql = "select * from video where video_name = '" + mVideoInfo.getTitle() + "'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        Log.i("查看语句hasRecode", sql);
        Log.i("查看语句hasRecode", cursor.getCount()+"");
        if( cursor.getCount() != 0 ){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void modifyDetail(VideoInfo mVideoInfo) {
        String sql = "";

        sql = "update video set video_desc = '"+ mVideoInfo.getDisplayName() + "', video_name = '" +
                mVideoInfo.getTitle() + "', video_bitmap = '"+ mVideoInfo.getImagePath()
                    +"' where video_id = '"+ mVideoInfo.getId() + "'";

        Log.i("查看语句modifyDetail", sql);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }
}
