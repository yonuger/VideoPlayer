package com.homework.group.videoplayer.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * desc:
 *
 * @author:young date:2016/4/6
 * time: 14:34
 * e-mail：1160415122@qq.com
 */
@SuppressLint("ParcelCreator")
public class VideoInfo implements Parcelable {
    private String title;
    private String imagePath;
    private Bitmap bitmap;
    private String time;
    private String displayName;
    private String path;
    private long process;
    private long id;

    public VideoInfo(){
        path = "";
        title = "";
        imagePath = "";
        process = 0;
        displayName = "";
        time = "";
        id = 0;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getProcess() {
        return process;
    }

    public void setProcess(long process) {
        this.process = process;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(title);
        out.writeString(imagePath);
        out.writeValue(bitmap);
        out.writeString(time);
        out.writeString(displayName);
        out.writeString(path);
        out.writeLong(process);
        out.writeLong(id);
    }

    public static final Parcelable.Creator<VideoInfo> CREATOR = new Creator<VideoInfo>()
    {
        @Override
        public VideoInfo[] newArray(int size)
        {
            return new VideoInfo[size];
        }

        @Override
        public VideoInfo createFromParcel(Parcel in)
        {
            return new VideoInfo(in);
        }
    };

    public VideoInfo(Parcel in)
    {
        title = in.readString();
        imagePath = in.readString();
        bitmap = (Bitmap) in.readValue(Bitmap.class.getClassLoader());
        time = in.readString();
        displayName = in.readString();
        path = in.readString();
        process = in.readLong();
        id = in.readLong();
    }
}
