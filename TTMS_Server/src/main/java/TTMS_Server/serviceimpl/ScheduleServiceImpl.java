package TTMS_Server.serviceimpl;

import TTMS_Server.dao.ScheduleDAO;
import TTMS_Server.model.Schedule;
import TTMS_Server.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ScheduleService")
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    private ScheduleDAO scheduleDAO;

    //根据id获取信息
    @Override
    public Schedule selectScheduleById(Integer id){return scheduleDAO.selectScheduleById(id);}

    //增加
    @Override
    public boolean addSchedule(Schedule schedule){
        if(scheduleDAO.selectScheduleById(schedule.getSched_id())==null){
            scheduleDAO.addSchedule(schedule);
            return true;
        }
        return false;
    }

    //删除
    @Override
    public boolean deleteScheduleById(Integer id){
        if(scheduleDAO.selectScheduleById(id)!=null){
            scheduleDAO.deleteScheduleById(id);
            return true;
        }
        return false;
    }

    //更新
    @Override
    public boolean updateScheduleById(Schedule schedule){
        Schedule schedule_old = scheduleDAO.selectScheduleById(schedule.getSched_id());

        if(schedule_old==null){
            return false;
        }

        //名称没有改变或者新名称可用
        if(schedule_old.getSched_id().equals(schedule.getSched_id())||
                scheduleDAO.selectScheduleById(schedule.getSched_id())==null){
            scheduleDAO.updateScheduleById(schedule);
            return true;
        }else{
            return false;
        }
    }

}
