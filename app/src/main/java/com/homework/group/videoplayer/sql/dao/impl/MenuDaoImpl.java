package com.homework.group.videoplayer.sql.dao.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.homework.group.videoplayer.sql.dao.MenuDao;
import com.homework.group.videoplayer.ui.home.VideoInfo;
import com.homework.group.videoplayer.ui.menu.VideoMenuInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/30  16:39
 */
public class MenuDaoImpl extends CommonImpl implements MenuDao{

    public MenuDaoImpl(Context context) {
        super(context);
    }

    @Override
    public List<VideoMenuInfo> scanVideoMenuInfo() {
        List<VideoMenuInfo> mList = new ArrayList<VideoMenuInfo>();
        String sql = "select * from video_menu";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            VideoMenuInfo videoMenuInfo = new VideoMenuInfo();
            videoMenuInfo.setId(cursor.getLong(cursor.getColumnIndex("video_menu_id")));
            videoMenuInfo.setTitle(cursor.getString(cursor.getColumnIndex("video_menu_name")));
            mList.add(videoMenuInfo);
        }
        cursor.close();
        db.close();


        sql = "select * from addto_menu where video_menu_id = '" + mList.get(0).getId() + "'";
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        map.put(mList.get(0).getId(), 0);
        for (int i = 1; i < mList.size(); i++) {
            sql += " or video_menu_id = '"+ mList.get(i).getId() + "'";
            map.put(mList.get(i).getId(), 0);
        }

        db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int count = map.get(cursor.getLong(cursor.getColumnIndex("video_menu_id")));
            count++;
            map.put(cursor.getLong(cursor.getColumnIndex("video_menu_id")), count);
        }
        cursor.close();
        db.close();

        for (int i = 0; i < mList.size(); i++) {
            long id = mList.get(i).getId();
            mList.get(i).setNumber(Integer.toString(map.get(id)));
        }


        return mList;
    }

    @Override
    public List<VideoInfo> requestMenuVideoById(long videoMenuId) {
        List<VideoInfo> videoInfoList = new ArrayList<VideoInfo>();
        String sql = "select * from video, addto_menu where video_menu_id = '"+ videoMenuId + "' and video.video_id = addto_menu.video_id";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            VideoInfo videoInfo = new VideoInfo();
            videoInfo.setTitle(cursor.getString(cursor.getColumnIndex("video_name")));
            videoInfo.setDisplayName(cursor.getString(cursor.getColumnIndex("video_desc")));
            videoInfo.setPath(cursor.getString(cursor.getColumnIndex("video_path")));
            videoInfoList.add(videoInfo);
        }
        cursor.close();
        db.close();

        return videoInfoList;
    }

    /**
     * 插入新工单
     * @param s
     */
    @Override
    public void createMenu(String s) {
        String sql = "";
        if( !hasMenu(s) ) {
            sql = "insert into video_menu(video_menu_name) Values('" + s + "')";
        }
        Log.i("查看语句insertVideo", sql);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql);
        db.close();
        Log.i("查看语句", "添加成功");
    }

    @Override
    public void deleteMenu(List<Long> longs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //开启事务
        db.beginTransaction();
        try
        {
            for (int i = 0; i < longs.size(); i++) {
                Log.e("12312312312", "delete from video_menu where video_menu_id= '"+longs.get(i) + "'");
                db.execSQL("delete from video_menu where video_menu_id= '"+longs.get(i) + "'");
            }
            //设置事务标志为成功，当结束事务时就会提交事务
            db.setTransactionSuccessful();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally
        {
            //结束事务
            db.endTransaction();
        }
    }

    @Override
    public void addVideoToMenu(long videoInfoId, long menuId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "insert into addto_menu( video_id, video_menu_id ) VALUES ('"+
                videoInfoId + "', '"+ menuId + "')";
        db.execSQL(sql);
        db.close();
    }

    /**
     *
     *判断是否已有视频
     * @param s
     * @return  true为有, false没有
     */
    private boolean hasMenu(String s) {
        String sql = "select * from video_menu where video_menu_name = '" + s + "'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if( cursor.getCount() != 0 ){
            return true;
        }else{
            return false;
        }
    }
}
