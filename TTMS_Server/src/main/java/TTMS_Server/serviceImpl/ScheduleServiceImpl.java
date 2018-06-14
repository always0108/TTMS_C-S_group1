package TTMS_Server.serviceImpl;

import TTMS_Server.dao.ScheduleDAO;
import TTMS_Server.model.Schedule;
import TTMS_Server.service.ScheduleService;
import TTMS_Server.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("ScheduleService")
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    private ScheduleDAO scheduleDAO;

    @Autowired
    private TicketService ticketService;

    //根据id获取信息
    @Override
    public Schedule selectScheduleById(Integer id){return scheduleDAO.selectScheduleById(id);}

    //根据剧目id获取相应演出计划
    @Override
    public List<Schedule> selectScheduleByPlayId(Integer id){ return scheduleDAO.selectScheduleByPlayId(id);}

    //根据剧目Id和日期获取相应演出计划
    public List<Schedule> selectScheduleByPlayIdDate(Integer id,String date){
        String start = date + " 00:00:00";
        String end = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date startDate = df.parse(start);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.DATE,1);
            end = df.format(calendar.getTime());
        }catch (ParseException ex){
            ex.printStackTrace();
        }
        return scheduleDAO.selectScheduleByPlayIdDate(id,start,end);
    }

    //根据剧目Id获取当天剩余演出计划
    public List<Schedule> selectTodayLeastSchedules(Integer id){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar now = Calendar.getInstance();
        String start = df.format(now.getTime());
        now.add(Calendar.DATE,1);
        String end = df.format(now.getTime()).substring(0,11) + "00:00:00";
        return scheduleDAO.selectScheduleByPlayIdDate(id,start,end);
    }

    //根据演出厅Id和日期获取相应的演出计划
    public List<Schedule> selectScheduleByStudioIdDate(Integer studio_id,
                                                       String start,String end){
        return scheduleDAO.selectScheduleByStudioIdDate(studio_id,start,end);
    }

    //根据演出厅id获取相应的演出计划
    @Override
    public List<Schedule> selectScheduleByStudioId(Integer id){ return scheduleDAO.selectScheduleByStudioId(id);}

    //增加
    @Override
    public boolean addSchedule(Schedule schedule){
        scheduleDAO.addSchedule(schedule);
        ticketService.initTicketByScheduleId(schedule.getSched_id());
        return true;
    }

    //删除
    @Override
    public boolean deleteScheduleById(Integer id){
        if(scheduleDAO.selectScheduleById(id)!=null){
            //先将票销毁
            ticketService.deleteTicketByScheduleId(id);
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
        }else{
            scheduleDAO.updateScheduleById(schedule);
            return true;
        }
    }

}
