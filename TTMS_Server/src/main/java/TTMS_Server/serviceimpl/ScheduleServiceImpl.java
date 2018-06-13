package TTMS_Server.serviceimpl;

import TTMS_Server.dao.ScheduleDAO;
import TTMS_Server.model.Play;
import TTMS_Server.model.Schedule;
import TTMS_Server.service.PlayService;
import TTMS_Server.service.ScheduleService;
import TTMS_Server.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.soap.Text;
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

    @Autowired
    private PlayService playService;


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

    //根据演出厅Id和日期获取相应的演出计划
    public List<Schedule> selectScheduleByStudioIdDate(Integer studio_id,
                                                String start,String end){
        return scheduleDAO.selectScheduleByStudioIdDate(studio_id,start,end);
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

    //根据演出厅id获取相应的演出计划
    @Override
    public List<Schedule> selectScheduleByStudioId(Integer id){ return scheduleDAO.selectScheduleByStudioId(id);}

    //增加
    @Override
    public boolean addSchedule(Schedule schedule){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar newTime = Calendar.getInstance();
        newTime.setTime(schedule.getSched_time());

        //一般电影都小于三个小时，判断前后3小时是否冲突
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.setTime(schedule.getSched_time());
        beforeTime.add(Calendar.HOUR,-3);

        Calendar afterTime = Calendar.getInstance();
        afterTime.setTime(schedule.getSched_time());
        afterTime.add(Calendar.HOUR,3);

        //进行容错处理
        List<Schedule> beforeSchedules = selectScheduleByStudioIdDate(schedule.getStudio_id(),df.format(beforeTime.getTime()),df.format(newTime.getTime()));
        for(Schedule schedule1:beforeSchedules){
            Play play = playService.selectPlayById(schedule1.getPlay_id());
            Calendar tmp = Calendar.getInstance();
            tmp.setTime(schedule1.getSched_time());
            tmp.add(Calendar.MINUTE,play.getPlay_length());
            if(tmp.after(newTime)){
                return false;
            }
        }

        List<Schedule> afterSchedules = selectScheduleByStudioIdDate(schedule.getStudio_id(),df.format(newTime.getTime()),df.format(afterTime.getTime()));
        for(Schedule schedule1:afterSchedules){
            Calendar tmp = Calendar.getInstance();
            tmp.setTime(schedule1.getSched_time());
            Play play = playService.selectPlayById(schedule.getPlay_id());
            newTime.add(Calendar.MINUTE,play.getPlay_length());
            if(tmp.before(newTime)){
                return false;
            }
        }
        scheduleDAO.addSchedule(schedule);
        ticketService.initTicketByScheduleId(schedule.getSched_id());
        return true;
    }

    //删除
    @Override
    public boolean deleteScheduleById(Integer id){
        //票没有卖出才能删除
        if(scheduleDAO.selectScheduleById(id)!=null && ticketService.selectSelledTicketByScheduleId(id) == null ){
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
