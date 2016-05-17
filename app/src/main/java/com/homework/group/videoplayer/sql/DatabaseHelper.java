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

    private static final int version = 3; //数据库版本

    public DatabaseHelper(Context context) {

        //第三个参数CursorFactory指定在执行查询时获得一个游标实例的工厂类,设置为null,代表使用系统默认的工厂类

        super(context, name, null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS play_recode (video_id integer primary key autoincrement, video_name varchar(50), " +
                    "video_desc varchar(80), video_path varchar(80), video_process varchar(50))");

        db.execSQL("CREATE TABLE IF NOT EXISTS video_menu (video_menu_id integer primary key autoincrement, video_menu_name varchar(50))");
        db.execSQL("INSERT INTO video_menu ( video_menu_name ) VALUES ( '我喜欢的视频' )");

        db.execSQL("CREATE TABLE IF NOT EXISTS video_menu_recode (video_id integer, video_menu_id integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//               db.execSQL("ALTER TABLE person ADD phone VARCHAR(12)"); //往表中增加一列
        System.out.println("--------onUpdate Called--------"
                + oldVersion + "--->" + newVersion);

    }


    /**
     * 添加播放记录
     * @param mVideoInfo
     * @return
     */
    public void addRecode(VideoInfo mVideoInfo){
        String sql = "";
        if( !hasRecode(mVideoInfo) ) {
            sql = "insert into play_recode(video_name, video_desc, video_path, video_process) Values('" + mVideoInfo.getTitle()+ "', '" + mVideoInfo.getDisplayName()
                    + "', '" + mVideoInfo.getPath() + "', '" + mVideoInfo.getProcess() + "')";
        }else{
            sql = "update play_recode set video_process = '"+ mVideoInfo.getProcess() + "' where video_name = '"+ mVideoInfo.getTitle() + "'";
        }
        Log.i("查看语句addRecode", sql);
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        db.close();
        Log.i("查看语句", "添加成功");
    }

    /**
     *
     *判断是否已有记录
     * @param mVideoInfo
     * @return  true为有, false没有
     */
    private boolean hasRecode(VideoInfo mVideoInfo) {
        String sql = "select * from play_recode where video_name = '" + mVideoInfo.getTitle() + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        Log.i("查看语句hasRecode", sql);
        Log.i("查看语句hasRecode", cursor.getCount()+"");
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
    public List<VideoInfo> scanRecode(){
        List<VideoInfo> mList = new ArrayList<VideoInfo>();
        String sql = "select * from play_recode";
        Log.i("查看语句", sql);
        SQLiteDatabase db = this.getReadableDatabase();
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
     * 获取所有影单
     * @return
     */
    public List<VideoMenuInfo> scanVideoMenuInfo(){
        List<VideoMenuInfo> mList = new ArrayList<VideoMenuInfo>();
        String sql = "select * from video_menu";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            VideoMenuInfo videoMenuInfo = new VideoMenuInfo();
            videoMenuInfo.setId(cursor.getLong(cursor.getColumnIndex("video_menu_id")));
            videoMenuInfo.setTitle(cursor.getString(cursor.getColumnIndex("video_menu_name")));
            mList.add(videoMenuInfo);
        }
        cursor.close();
        db.close();


        sql = "select * from video_menu_recode where video_menu_id = '" + mList.get(0).getId() + "'";
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        map.put(mList.get(0).getId(), 0);
        for (int i = 1; i < mList.size(); i++) {
            sql += " or video_menu_id = '"+ mList.get(i).getId() + "'";
            map.put(mList.get(i).getId(), 0);
        }

        db = this.getReadableDatabase();
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

    /**
     * 根据影单id获取视频列表内容
     * @param videoMenuId
     * @return
     */
    public List<VideoInfo>requestMenuVideo(long videoMenuId){
        List<VideoInfo> videoInfoList = new ArrayList<VideoInfo>();
        String sql = "select * from play_recode, video_menu_recode where video_menu_id = '"+ videoMenuId + "'";
        SQLiteDatabase db = this.getReadableDatabase();
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
        addRecode(mVideoInfo);
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
