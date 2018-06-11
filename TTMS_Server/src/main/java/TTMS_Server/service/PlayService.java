package TTMS_Server.service;

import TTMS_Server.model.Play;

import java.util.List;

public interface PlayService {

    //根据id获取剧目信息
    Play selectPlayById(Integer id);

    //根据名称获取剧目信息
    Play selectPlayByName(String name);

    //根据关键字获取匹配的剧目信息
    List<Play> getAllPlayByPartName(String name);

    //根据日期列出当天上映的演出计划
    List<Play> selectPlayByDate(String date);

    //新增剧目
    boolean addPlay(Play play);

    //删除剧目
    boolean deletePlayById(Integer id);

    //更新剧目
    boolean updatePlayById(Play play);
}