package com.homework.group.videoplayer.sql.dao.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.homework.group.videoplayer.sql.dao.HistoryDao;
import com.homework.group.videoplayer.ui.home.VideoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/25  15:21
 */
public class HistoryDaoImpl extends CommonImpl implements HistoryDao {

    public HistoryDaoImpl(Context context){
        super(context);
    }

    /**
     * 添加播放记录
     * @param mVideoInfo
     * @return
     */
    public void addHistory(VideoInfo mVideoInfo){
        String sql = "";
        if( !hasHistory(mVideoInfo) ) {
            sql = "insert into play_history(video_id, video_process) Values('" + mVideoInfo.getId()
                    + "', '" + mVideoInfo.getProcess() + "')";
        }else{
            sql = "update play_history set video_process = '"+ mVideoInfo.getProcess() + "' where video_id = '"+ mVideoInfo.getId() + "'";
        }
        Log.i("查看语句addRecode", sql);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql);
        db.close();
        Log.i("查看语句", "添加成功");
    }

    /**
     * 判断是否已有播放历史
     * @param mVideoInfo
     * @return
     */
    public boolean hasHistory(VideoInfo mVideoInfo) {
        String sql = "select * from play_history where video_id = '" + mVideoInfo.getId() + "'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        Log.i("查看语句hasHistory", sql);
        Log.i("查看语句hasHistory", cursor.getCount()+"");
        if( cursor.getCount() != 0 ){
            return true;
        }else{
            return false;
        }
    }


    /**
     * 查看历史记录
     * @return
     */
    public List<VideoInfo> requestAllHistory(){
        List<VideoInfo> mList = new ArrayList<VideoInfo>();
        String sql = "select * from play_history, video where play_history.video_id = video.video_id";
        Log.i("查看语句", sql);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            VideoInfo videoInfo = new VideoInfo();
            videoInfo.setId(cursor.getLong(cursor.getColumnIndex("video_id")));
            videoInfo.setTitle(cursor.getString(cursor.getColumnIndex("video_name")));
            videoInfo.setProcess(Long.parseLong(cursor.getString(cursor.getColumnIndex("video_process"))));
            videoInfo.setPath(cursor.getString(cursor.getColumnIndex("video_path")));
            videoInfo.setDisplayName(cursor.getString(cursor.getColumnIndex("video_desc")));
            Log.i("查看语句", videoInfo.getTitle());
            Log.i("查看语句", videoInfo.getPath());
            mList.add(videoInfo);
        }
        cursor.close();
        db.close();
        return mList;
    }

    /**
     * 删除历史记录
     * @param mVideoInfo
     */
    public boolean deleteHistory(VideoInfo mVideoInfo) {
        String sql = "delete from play_history where video_id = '"+ mVideoInfo.getId()+"'";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if( cursor == null || cursor.getCount() == 0 ){
            return false;
        }else {
            while (cursor.moveToNext()) {
            }
            return true;
        }
    }
}
