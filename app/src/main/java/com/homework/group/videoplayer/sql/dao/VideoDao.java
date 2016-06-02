package com.homework.group.videoplayer.sql.dao;

import com.homework.group.videoplayer.ui.home.VideoInfo;

import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * phone:15625430473
 * date:16/5/25  15:10
 */
public interface VideoDao {

    /**
     * 查看所有的视频
     * @return
     */
    List<VideoInfo> requestAllVideo();

    /**
     *
     * @param id
     * @return
     */
    VideoInfo requestVideoDetailById(long id);

    /**
     * 插入视频或者说更新视频
     * @param mVideoInfo
     */
    void insertVideo(VideoInfo mVideoInfo);

    /**
     *
     *判断是否已有视频
     * @param mVideoInfo
     * @return  true为有, false没有
     */
    boolean hasVideo(VideoInfo mVideoInfo);

    /**
     * 修改视频详情
     * @param mVideoInfo
     */
    void modifyDetail(VideoInfo mVideoInfo);
}
