package TTMS_Server.service;

import TTMS_Server.model.Schedule;

import java.util.List;

public interface ScheduleService {

    //根据id获取信息
    Schedule selectScheduleById(Integer id);

    //根据剧目id获取相应演出计划
    List<Schedule> selectScheduleByPlayId(Integer id);

    //根据剧目Id和日期获取相应演出计划
    List<Schedule> selectScheduleByPlayIdDate(Integer id,String date);

    //根据剧目Id获取当天剩余演出计划
    List<Schedule> selectTodayLeastSchedules(Integer id);

    //根据演出厅id获取相应演出计划
    List<Schedule> selectScheduleByStudioId(Integer id);

    //增加
    boolean addSchedule(Schedule schedule);

    //删除
    boolean deleteScheduleById(Integer id);

    //更新
    boolean updateScheduleById(Schedule schedule);
}
