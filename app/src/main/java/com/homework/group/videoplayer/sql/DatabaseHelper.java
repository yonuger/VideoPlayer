package com.homework.group.videoplayer.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.homework.group.videoplayer.ui.home.VideoInfo;
import com.homework.group.videoplayer.ui.menu.VideoMenuInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DatabaseHelper extends SQLiteOpenHelper {

    //类没有实例化,是不能用作父类构造器的参数,必须声明为静态
    private static final String name = "video_player"; //数据库名称

    private static final int version = 2; //数据库版本

    public DatabaseHelper(Context context) {

        //第三个参数CursorFactory指定在执行查询时获得一个游标实例的工厂类,设置为null,代表使用系统默认的工厂类

        super(context, name, null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS video (video_id integer primary key autoincrement, video_name varchar(50), " +
                "video_desc varchar(200), video_path varchar(80), video_bitmap varchar(80))");

        db.execSQL("CREATE TABLE IF NOT EXISTS video_menu (video_menu_id integer primary key autoincrement, video_menu_name varchar(50))");
        db.execSQL("INSERT INTO video_menu ( video_menu_name ) VALUES ( '我喜欢的视频' )");

        db.execSQL("CREATE TABLE IF NOT EXISTS addto_menu (video_id integer, video_menu_id integer)");

        db.execSQL("CREATE TABLE IF NOT EXISTS play_history (history_id integer primary key autoincrement, video_id integer, video_process integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//               db.execSQL("ALTER TABLE person ADD phone VARCHAR(12)"); //往表中增加一列
        System.out.println("--------onUpdate Called--------"
                + oldVersion + "--->" + newVersion);

    }


    public void addFavoriteVideo(VideoInfo mVideoInfo){
        String sql = "";
        SQLiteDatabase db = this.getReadableDatabase();
        sql = "select * from video_menu where video_menu_name = '我喜欢的视频'";
        Cursor cursor = db.rawQuery(sql, null);
        long menuId = 0;
        if (cursor.moveToNext()){
            menuId = cursor.getLong(cursor.getColumnIndex("video_menu_id"));
        }
        db.close();
        /*
         *   如果记录中没有就把它加进去,有记录的才能进行添加喜爱
         */
//        addRecode(mVideoInfo);
        mVideoInfo.setId(getVideoIdByName(mVideoInfo.getTitle()));
        db = getWritableDatabase();
        if( !hasFavoriteVideo(mVideoInfo, menuId) ) {
            sql = "insert into video_menu_recode(video_id, video_menu_id) Values('" + mVideoInfo.getId()+ "', '" +
                    menuId + "')";
        }else{
            db.close();
            return;
        }
        Log.i("查看语句", sql);
        db.execSQL(sql);
        db.close();
        Log.i("查看语句", "添加成功");
    }

    /**
     * 根据视频的name获取id
     * @param title
     * @return
     */
    private long  getVideoIdByName(String title) {
        String sql = "select * from play_recode where video_name = '"+ title +"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        Log.i("查看语句hasFavoriteVideo", sql);
        if( cursor.moveToNext() ){
            return cursor.getLong(cursor.getColumnIndex("video_id"));
        }
        return -1;
    }

    private boolean hasFavoriteVideo(VideoInfo mVideoInfo, long menuId) {
        String sql = "select * from video_menu_recode where video_id = '"+ mVideoInfo.getId() +"' or video_menu_id = '"+ menuId +"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        Log.i("查看语句hasFavoriteVideo", sql);
        if( cursor == null || cursor.getCount() == 0 ){
            return false;
        }else {
            while (cursor.moveToNext()) {
            }
            return true;
        }
    }


}
