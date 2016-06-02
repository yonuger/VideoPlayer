package com.homework.group.videoplayer.ui.detail;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.homework.group.videoplayer.R;
import com.homework.group.videoplayer.constant.IntentCodeConstant;
import com.homework.group.videoplayer.sql.DatabaseHelper;
import com.homework.group.videoplayer.sql.dao.MenuDao;
import com.homework.group.videoplayer.sql.dao.VideoDao;
import com.homework.group.videoplayer.sql.dao.impl.MenuDaoImpl;
import com.homework.group.videoplayer.sql.dao.impl.VideoDaoImpl;
import com.homework.group.videoplayer.ui.home.VideoInfo;
import com.homework.group.videoplayer.ui.menu.VideoMenu;
import com.homework.group.videoplayer.ui.menu.VideoMenuInfo;
import com.homework.group.videoplayer.ui.play.PlayActivity;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.vov.vitamio.utils.Log;

public class VideoDetailActivity extends AppCompatActivity implements OnMenuItemClickListener, OnMenuItemLongClickListener {

    private FragmentManager fragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;

    private VideoInfo videoInfo;
    private VideoDao videoDao;
    private VideoDetailFragment fragment;

    private List<VideoMenuInfo> list;
    private String[] mDataName;
    private long[] mDataId;
    private MenuDao menuMao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        videoInfo = getIntent().getParcelableExtra("videoInfo");
        initToolbar();
        initData();
        fragmentManager = getSupportFragmentManager();
        initMenuFragment();
        fragment = new VideoDetailFragment(videoInfo);
        addFragment(fragment, true, R.id.container);
    }

    private void initData() {
        videoDao = new VideoDaoImpl(this);
        VideoInfo result = videoDao.requestVideoDetailById(videoInfo.getId());
        if( result != null ){
            videoInfo = result;
        }

        menuMao = new MenuDaoImpl(getBaseContext());
        list = menuMao.scanVideoMenuInfo();
        mDataName = new String[list.size()];
        mDataId = new long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            mDataName[i] = list.get(i).getTitle();
            mDataId[i] = list.get(i).getId();
        }
    }

    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);
    }

    private List<MenuObject> getMenuObjects() {
        // You can use any [resource, bitmap, drawable, color] as image:
        // item.setResource(...)
        // item.setBitmap(...)
        // item.setDrawable(...)
        // item.setColor(...)
        // You can set image ScaleType:
        // item.setScaleType(ScaleType.FIT_XY)
        // You can use any [resource, drawable, color] as background:
        // item.setBgResource(...)
        // item.setBgDrawable(...)
        // item.setBgColor(...)
        // You can use any [color] as text color:
        // item.setTextColor(...)
        // You can set any [color] as divider color:
        // item.setDividerColor(...)

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.mipmap.icn_close);

        MenuObject edit = new MenuObject("编辑");
        edit.setResource(R.mipmap.icn_1);

        MenuObject like = new MenuObject("加入影单");
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.mipmap.icn_2);
        like.setBitmap(b);

        MenuObject addFav = new MenuObject("喜欢");
        addFav.setResource(R.mipmap.icn_4);

        MenuObject block = new MenuObject("播放");
        block.setResource(R.mipmap.icn_5);

        menuObjects.add(close);
        menuObjects.add(edit);
        menuObjects.add(like);
        menuObjects.add(addFav);
        menuObjects.add(block);
        return menuObjects;
    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setNavigationIcon(R.mipmap.btn_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    protected void addFragment(Fragment fragment, boolean addToBackStack, int containerId) {
        invalidateOptionsMenu();
        String backStackName = fragment.getClass().getName();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStackName, 0);
        if (!fragmentPopped) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(containerId, fragment, backStackName)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            if (addToBackStack)
                transaction.addToBackStack(backStackName);
            transaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
        } else{
            finish();
        }
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        switch ( position ){
            case 1:
                Intent intent = new Intent(this, EditActivity.class);
                intent.putExtra("videoInfo", videoInfo);
                startActivity(intent);
                break;
            case 2:
                addToMenu();
            case 3:
                Toast.makeText(this, "亲,已加入喜欢~~", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                intent = new Intent(this, PlayActivity.class);
                intent.putExtra("videoInfo", videoInfo);
                startActivity(intent);
        }
    }

    /**
     * 加入影单
     */
    private void addToMenu() {
        AlertDialog.Builder builder = new AlertDialog.Builder(VideoDetailActivity.this);
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("选择影单");
        //    设置一个下拉的列表选择项
        builder.setItems(mDataName, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                menuMao.addVideoToMenu(videoInfo.getId(), mDataId[which]);
                onResume();
            }
        });
        builder.show();
    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {
        switch ( position ){
            case 3:
                break;
            case 4:
                Intent intent = new Intent(this, PlayActivity.class);
                intent.putExtra("videoInfo", videoInfo);
                startActivity(intent);
        }
    }

    @Override
    protected void onResume(){
        initData();
        fragment.setVideoInfo(videoInfo);
        super.onResume();
    }
}

