package com.homework.group.videoplayer.sql.dao;

import com.homework.group.videoplayer.ui.home.VideoInfo;

import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/25  15:20
 */
public interface HistoryDao {

    /**
     * 添加播放记录
     * @param mVideoInfo
     * @return
     */
    public void addHistory(VideoInfo mVideoInfo);

    /**
     * 判断是否已有播放历史
     * @param mVideoInfo
     * @return
     */
    public boolean hasHistory(VideoInfo mVideoInfo);

    /**
     * 查看历史记录
     * @return
     */
    public List<VideoInfo> requestAllHistory();

    /**
     * 删除历史记录
     * @param mVideoInfo
     */
    public boolean deleteHistory(VideoInfo mVideoInfo);
}
