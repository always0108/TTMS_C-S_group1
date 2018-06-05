package TTMS_Server.service;

import TTMS_Server.model.Schedule;

public interface ScheduleService {

    //根据id获取信息
    Schedule selectScheduleById(Integer id);

    //增加
    boolean addSchedule(Schedule schedule);

    //删除
    boolean deleteScheduleById(Integer id);

    //更新
    boolean updateScheduleById(Schedule schedule);
}
