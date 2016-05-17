package com.homework.group.videoplayer.utils;

import android.content.Context;

import com.homework.group.videoplayer.sql.DatabaseHelper;

/**
 * Created by young on 16/5/5.
 */
public class DBUtils {
    private DatabaseHelper dbHelper;

    public DBUtils(Context context){
        dbHelper = new DatabaseHelper(context);
    }


}
