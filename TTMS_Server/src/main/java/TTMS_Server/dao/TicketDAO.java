package TTMS_Server.dao;

import TTMS_Server.model.SeatAndTicket;
import TTMS_Server.model.Ticket;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface TicketDAO {
    //根据id获取
    @Select("select * from  ticket where ticket_id = #{id}")
    Ticket selectTicketById(Long id);

    //增加
    @Insert("insert into ticket (seat_id , sched_id , ticket_price , ticket_status ," +
            "ticket_locked_time) values (#{seat_id} , #{sched_id} , #{ticket_price} , " +
            "#{ticket_status} , #{ticket_locked_time})")
    void addTicket(Ticket ticket);

    //删除
    @Delete("delete from ticket where ticket_id = #{id}")
    void deleteTicketById(Long id);

    //更新
    @Update("update ticket set seat_id = #{seat_id} , sched_id = #{sched_id} , " +
            "ticket_price = #{ticket_price} , ticket_status = #{ticket_status} , " +
            "ticket_locked_time = #{ticket_locked_time} where ticket_id = #{ticket_id}")
    void  updateTicketById(Ticket ticket);

    //更新上锁时间
    @Update("update ticket set ticket_status = #{ticket_status}," +
            "ticket_locked_time = #{ticket_locked_time} where ticket_id = #{ticket_id}")
    void  updateLockedTime(Ticket ticket);

    //根据演出计划批量删除票
    @Delete("delete from ticket where sched_id = #{sched_id}")
    void deleteTicketByScheduleId(Integer sched_id);

    //根据演出计划批量提取票
    @Select("select ticket_id,seat_row,seat_column,ticket_price,ticket_status " +
            "from seat natural join ticket where sched_id = #{sched_id}")
    List<SeatAndTicket> selectTicketByScheduleId(Integer sched_id);

    //给取消的订单中的票解锁
    @Update("update ticket set ticket_status = 0,ticket_locked_time = NULL " +
            "where ticket_id in (select ticket_id from sale_item where sale_ID = #{SaleID})")
    void UnLockNotPayTickets(Long SaleID);
}
