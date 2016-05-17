package com.homework.group.videoplayer.ui.menu;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/10  09:36
 */
@SuppressLint("ParcelCreator")
public class VideoMenuInfo implements Parcelable {
    private String title;
    private Bitmap bitmap;
    private String number;
    private long id;

    public VideoMenuInfo(){
        title = "";
        number = "";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getNumber(){
        return number;
    }

    public void setNumber(String number){
        this.number = number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(title);
        out.writeString(number);
        out.writeValue(bitmap);
    }

    public static final Parcelable.Creator<VideoMenuInfo> CREATOR = new Creator<VideoMenuInfo>()
    {
        @Override
        public VideoMenuInfo[] newArray(int size)
        {
            return new VideoMenuInfo[size];
        }

        @Override
        public VideoMenuInfo createFromParcel(Parcel in)
        {
            return new VideoMenuInfo(in);
        }
    };

    public VideoMenuInfo(Parcel in)
    {
        title = in.readString();
        number = in.readString();
        bitmap = (Bitmap) in.readValue(Bitmap.class.getClassLoader());
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}

