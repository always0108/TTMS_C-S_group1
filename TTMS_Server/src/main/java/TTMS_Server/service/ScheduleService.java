package TTMS_Server.service;

import TTMS_Server.model.Schedule;

import java.util.List;

public interface ScheduleService {

    //根据id获取信息
    Schedule selectScheduleById(Integer id);

    //根据剧目id获取演出计划
    List<Schedule> selectScheduleByPlayId(Integer id);

    //根据演出厅id获取
    List<Schedule> selectScheduleByStudioId(Integer id);

    //增加
    boolean addSchedule(Schedule schedule);

    //删除
    boolean deleteScheduleById(Integer id);

    //更新
    boolean updateScheduleById(Schedule schedule);
}
