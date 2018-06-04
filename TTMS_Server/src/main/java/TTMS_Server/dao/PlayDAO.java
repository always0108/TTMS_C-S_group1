package TTMS_Server.dao;


import TTMS_Server.model.Play;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PlayDAO {

    //根据id获取剧目信息
    @Select("select * from play where play_id = #{id}")
    Play selectPlayById(Integer id);

    //根据名称获取剧目信息
    @Select("select * from play where play_name = #{name}")
    Play selectPlayByName(String name);

    //根据关键字获取匹配的剧目信息
    @Select("select * from play where play_name like #{name}")
    List<Play> getAllPlayByPartName(String name);

    //新增剧目
    @Insert("insert into play(play_type_id,play_lang_id," +
            "play_name,play_introduction,play_image,play_length," +
            "play_ticket_price,play_status) values (#{play_type_id},#{play_lang_id},#{play_name}," +
            "#{play_introduction},#{play_image},#{play_length},#{play_ticket_price},#{play_status})")
    void addPlay(Play play);

    //删除剧目
    @Delete("delete from play where play_id = #{id}")
    void deletePlayById(Integer id);

    //更新剧目
    @Update("update play set play_type_id = #{play_type_id},play_lang_id = #{play_lang_id}," +
            "play_name = #{play_name},play_introduction = #{play_introduction},play_image = #{play_image}, " +
            "play_length = #{play_length},play_ticket_price = #{play_ticket_price},play_status = #{play_status}" +
            "where play_id = #{play_id}")
    void updatePlayById(Play play);
}
