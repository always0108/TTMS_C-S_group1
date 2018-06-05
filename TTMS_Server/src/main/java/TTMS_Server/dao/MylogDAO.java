package TTMS_Server.dao;

import TTMS_Server.model.Mylog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MylogDAO {
    //根据id获取
    @Select("select * from mylog where log_id = #{id}")
    Mylog selectMylogById(Integer id);

    //新增
    @Insert("insert into mylog (log_time,log_content) values (#{log_time},#{log_content})")
    void addMylog(Mylog mylog);

    //删除
    @Delete("delete from mylog where log_id = #{id}")
    void deleteMylogById(Integer id);

}
