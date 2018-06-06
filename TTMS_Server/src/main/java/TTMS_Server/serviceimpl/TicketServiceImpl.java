package TTMS_Server.serviceimpl;

import TTMS_Server.dao.ScheduleDAO;
import TTMS_Server.dao.TicketDAO;
import TTMS_Server.model.Schedule;
import TTMS_Server.model.Ticket;
import TTMS_Server.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("TicketService")
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private ScheduleDAO scheduleDAO;

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

    //根据演出计划id批量生成票
//    public boolean initTicketByScheduleId(Integer id){
//        Schedule schedule = scheduleDAO.selectScheduleById(id);
//        if(schedule!=null){
//
//        }
//    }
}
