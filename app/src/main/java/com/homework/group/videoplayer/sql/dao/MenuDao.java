package com.homework.group.videoplayer.sql.dao;

import com.homework.group.videoplayer.ui.home.VideoInfo;
import com.homework.group.videoplayer.ui.menu.VideoMenuInfo;

import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/30  16:38
 */
public interface MenuDao {

    /**
     * 获取所有影单
     * @return
     */
    List<VideoMenuInfo> scanVideoMenuInfo();

    /**
     * 根据影单id获取视频列表内容
     * @param videoMenuId
     * @return
     */
    public List<VideoInfo>requestMenuVideoById(long videoMenuId);

    /**
     * 创建影单
     * @param s
     */
    void createMenu(String s);

    /**
     * 删除影单
     * @param longs
     */
    void deleteMenu(List<Long> longs);

    /**
     *  增加视频到影单
     * @param videoInfoId
     * @param menuId
     */
    void addVideoToMenu(long videoInfoId, long menuId);
}
