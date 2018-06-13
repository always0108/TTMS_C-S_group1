package TTMS_Server.service;

import TTMS_Server.model.SeatAndTicket;
import TTMS_Server.model.Ticket;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface TicketService {
    //根据id获取信息
    Ticket selectTicketById(Long id);

    //增加
    boolean addTicket(Ticket ticket);

    //删除
    boolean deleteTicketById(Long id);

    //更新
    boolean updateTicketById(Ticket ticket);

    //根据演出计划id批量生成票
    boolean initTicketByScheduleId(Integer id);

    //根据演出计划id批量删除票
    boolean deleteTicketByScheduleId(Integer sched_id);

    //根据演出计划批量提取票
    List<SeatAndTicket> selectTicketByScheduleId(Integer sched_id);

    //判断该票是否能被锁定
    boolean isLocked(Long ticket_id);

    //更新上锁时间
    void  updateLockedTime(Ticket ticket);

    //给订单中的票解锁
    void UnLockTickets(Short status,Long saleID);

    //根据演出计划提取卖出的票
    List<Ticket> selectSelledTicketByScheduleId(Integer sched_id);
}


