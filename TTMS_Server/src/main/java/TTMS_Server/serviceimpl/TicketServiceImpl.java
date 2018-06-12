package TTMS_Server.serviceimpl;

import TTMS_Server.dao.ScheduleDAO;
import TTMS_Server.dao.SeatDAO;
import TTMS_Server.dao.TicketDAO;
import TTMS_Server.model.Schedule;
import TTMS_Server.model.Seat;
import TTMS_Server.model.SeatAndTicket;
import TTMS_Server.model.Ticket;
import TTMS_Server.service.ScheduleService;
import TTMS_Server.service.SeatService;
import TTMS_Server.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("TicketService")
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private SeatService seatService;

    @Autowired
    private ScheduleService scheduleService;

    //根据id获取信息
    @Override
    public Ticket selectTicketById(Long id) { return ticketDAO.selectTicketById(id);}

    //增加
    @Override
    public boolean addTicket(Ticket ticket){
        if(ticketDAO.selectTicketById(ticket.getTicket_id())==null){
            ticketDAO.addTicket(ticket);
            return true;
        }
        return  false;
    }

    //删除
    @Override
    public boolean deleteTicketById(Long id){
        if(ticketDAO.selectTicketById(id)!=null){
            ticketDAO.deleteTicketById(id);
            return true;
        }
        return  false;
    }

    //更新
    @Override
    public boolean updateTicketById(Ticket ticket){
        Ticket ticket_old = ticketDAO.selectTicketById(ticket.getTicket_id());

        //不存在
        if(ticket_old==null){
            return false;
        }

        if(ticket_old.getTicket_id().equals(ticket.getTicket_id())||
                ticket.getTicket_id()!=null){
            ticketDAO.updateTicketById(ticket);
            return true;
        }
        else
            return false;
    }

    //根据演出计划id批量生成票(添加演出计划时)
    public boolean initTicketByScheduleId(Integer id){
        Schedule schedule = scheduleService.selectScheduleById(id);
        if(schedule!=null){
            List<Seat> seats = seatService.selectStudioSeatsByStudioId(schedule.getStudio_id());
            for(Seat seat : seats){
                if(seat.getSeat_status() == 1){
                    //给正常的座位添加票
                    ticketDAO.addTicket(new Ticket(seat.getSeat_id(),schedule.getSched_id(),schedule.getSched_ticket_price(),Short.parseShort("0")));
                }
            }
            return true;
        }else{
            return false;
        }
    }

    //根据演出计划id批量删除票（删除演出计划时）
    @Override
    public boolean deleteTicketByScheduleId(Integer sched_id) {
        Schedule schedule = scheduleService.selectScheduleById(sched_id);
        if(schedule != null){
            ticketDAO.deleteTicketByScheduleId(sched_id);
            return true;
        }else{
            return false;
        }
    }

    //根据演出计划批量提取票
    public List<SeatAndTicket> selectTicketByScheduleId(Integer sched_id){
        //先给超时的订单的票解锁

        Schedule schedule = scheduleService.selectScheduleById(sched_id);
        if(schedule != null){
            return ticketDAO.selectTicketByScheduleId(sched_id);
        }else{
            return null;
        }
    }

    //判断该票是否能被锁定
    public boolean isLocked(Long ticket_id){
        Ticket ticket = ticketDAO.selectTicketById(ticket_id);
        if(ticket.getTicket_locked_time() == null){
            return true;
        }else{
            Calendar oldLockTime = Calendar.getInstance();
            oldLockTime.setTime(ticket.getTicket_locked_time());
            //锁定时间为15分钟
            oldLockTime.add(Calendar.MINUTE,15);
            Calendar newLockTime = Calendar.getInstance();
            //锁定的时间还没有到期
            if(oldLockTime.after(newLockTime)){
                return false;
            }else {
                return true;
            }
        }
    }

    //更新上锁时间
    public void  updateLockedTime(Ticket ticket){
        ticketDAO.updateLockedTime(ticket);
    }
}
