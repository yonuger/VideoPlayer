package com.homework.group.videoplayer.ui.menu.detail;

import com.homework.group.videoplayer.ui.detail.VideoDetailDataBinder;
import com.kymjs.frame.databind.DataBindFragment;
import com.kymjs.frame.databind.DataBinder;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/10  10:15
 */
public class VideoMenuDetailFragment extends DataBindFragment<VideoMenuDetailDelegate> {

    private long menuId;

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
//        viewDelegate.get(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                data.setName(viewDelegate.getText(R.id.editText));
//                data.setAge(viewDelegate.getText(R.id.editText2));
//                notifyModelChanged(data);
//            }
//        });
        viewDelegate.setMenuId(menuId);
    }

    @Override
    protected Class<VideoMenuDetailDelegate> getDelegateClass() {
        return VideoMenuDetailDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return new VideoDetailDataBinder();
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }
}
