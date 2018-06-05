package TTMS_Server.service;

import TTMS_Server.model.Ticket;

public interface TicketService {
    //根据id获取信息
    Ticket selectTicketById(Long id);

    //增加
    boolean addTicket(Ticket ticket);

    //删除
    boolean deleteTicketById(Long id);

    //更新
    boolean updateTicketById(Ticket ticket);
}


