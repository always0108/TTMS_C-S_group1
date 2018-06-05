package TTMS_Server.dao;

import TTMS_Server.model.Schedule;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ScheduleDAO {

    //根据id获取
    @Select("select * from schedule where sched_id = #{id}")
    Schedule selectScheduleById(Integer id);

    //新增
    @Insert("insert into schedule(studio_id,play_id,sched_time,sched_ticket_price) values (" +
            "#{studio_id},#{play_id},#{sched_time},#{sched_ticket_price})")
    void addSchedule(Schedule schedule);

    //删除
    @Delete("delete from schedule where sched_id = #{id}")
    void deleteScheduleById(Integer id);

    //更新
    @Update("update schedule set studio_id = #{studio_id} , play_id = #{play_id} ," +
            " sched_time = #{sched_time} , sched_ticket_price = #{sched_ticket_price} " +
            "where sched_id = #{sched_id}")
    void updateScheduleById(Schedule schedule);


}
