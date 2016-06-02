package com.homework.group.videoplayer.ui.detail;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.homework.group.videoplayer.R;
import com.homework.group.videoplayer.constant.IntentCodeConstant;
import com.homework.group.videoplayer.sql.dao.VideoDao;
import com.homework.group.videoplayer.sql.dao.impl.VideoDaoImpl;
import com.homework.group.videoplayer.ui.home.VideoInfo;
import com.homework.group.videoplayer.utils.ImageUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/18  16:00
 */
public class EditActivity extends Activity implements View.OnClickListener {

    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果


    private VideoInfo videoInfo;
    private EditText descEt;
    private TextView nameTv;
    private ImageView imageView;
    private Button sendBtn;

    private String path = "";

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_edit);

        nameTv = (TextView) findViewById(R.id.tv_detail_edit_name);
        descEt = (EditText) findViewById(R.id.et_detail_edit_desc);
        imageView = (ImageView) findViewById(R.id.iv_detail_edit);
        sendBtn = (Button) findViewById(R.id.btn_detail_edit);

        videoInfo = (VideoInfo) getIntent().getParcelableExtra("videoInfo");

        imageView.setOnClickListener(this);
        sendBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_detail_edit:
                selectImage();
                break;
            case R.id.btn_detail_edit:
                send();
                break;
        }
    }

    private void send() {
        videoInfo.setDisplayName(descEt.getText().toString());
        videoInfo.setImagePath(path);
        Log.e("send", path);
        VideoDao videoDao = new VideoDaoImpl(this);
        videoDao.modifyDetail(videoInfo);
        finish();
    }

    /**
     * 选择图片
     */
    private void selectImage() {
        Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
        intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
        intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case PHOTO_REQUEST_GALLERY:
                if (data != null)
                    startPhotoZoom(data.getData(), 150);
                break;
            case PHOTO_REQUEST_CUT:
                if (data != null)
                    setPicToView(data);
                break;
        }
    }


    // 使用系统当前日期加以调整作为照片的名称
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

    /**
     *  进行裁剪
     * @param uri
     * @param size
     */
    private void startPhotoZoom(Uri uri, int size) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }


    //将进行剪裁后的图片显示到UI界面上
    private void setPicToView(Intent picdata) {
        Bundle bundle = picdata.getExtras();
        if (bundle != null) {
            Bitmap photo = bundle.getParcelable("data");
            Drawable drawable = new BitmapDrawable(photo);
            imageView.setBackgroundDrawable(drawable);

            try {
                FileOutputStream fos = new FileOutputStream(getPhotoFileName());
                // Bitmap.CompressFormat.PNG
                photo.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
