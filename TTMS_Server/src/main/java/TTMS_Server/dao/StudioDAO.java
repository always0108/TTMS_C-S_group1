package TTMS_Server.dao;

import TTMS_Server.model.Studio;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudioDAO {

    //根据id获取演出厅信息
    @Select("select * from studio where studio_id = #{id}")
    Studio selectStudioById(Integer id);

    //根据名称获取演出厅信息
    @Select("select * from studio where studio_name = #{name}")
    Studio selectStudioByName(String name);

    //根据关键字获取匹配的演出厅信息
    @Select("select * from studio where studio_name like #{name}")
    List<Studio> getAllStudioByPartName(String name);

    //新增演出厅
    @Insert("insert into studio(studio_name,studio_row_count,studio_col_count,studio_seat_count," +
            "studio_introduction,studio_flag) values (#{studio_name},#{studio_row_count}," +
            "#{studio_col_count},#{studio_seat_count},#{studio_introduction},#{studio_flag})")
    void addStudio(Studio studio);

    //删除演出厅
    @Delete("delete from studio where studio_id = #{id}")
    void deleteStudioById(Integer id);

    //更新演出厅
    @Update("update studio set studio_name = #{studio_name} , studio_row_count = #{studio_row_count}," +
            "studio_col_count = #{studio_col_count} , studio_seat_count = #{studio_seat_count}," +
            "studio_introduction = #{studio_introduction} , studio_flag = #{studio_flag} " +
            "where studio_id = #{studio_id}")
    void updateStudioById(Studio studio);
}
