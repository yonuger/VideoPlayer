package com.homework.group.videoplayer.ui.detail;

import com.kymjs.frame.databind.DataBindFragment;
import com.kymjs.frame.databind.DataBinder;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/9  20:59
 */
public class VideoDetailFragment extends DataBindFragment<VideoDetailDelegate> {

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
    }

    @Override
    protected Class<VideoDetailDelegate> getDelegateClass() {
        return VideoDetailDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return new Demo4DataBinder();
    }
}
