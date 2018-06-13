package TTMS_Server.service;

import TTMS_Server.model.Sale;
import TTMS_Server.model.SeatAndTicket;

import java.util.List;

public interface SaleService {

    //根据id获取信息
    Sale selectSaleById(Long id);

    //增加
    boolean addSale(Sale sale);

    //删除
    boolean deleteSaleById(Long id);

    //更新
    boolean updateSaleById(Sale sale);

    //生成一个订单
    Sale dealSale(List<SeatAndTicket> seatAndTickets,Integer flag,Integer emp_id);

    //更新状态
    boolean updateStatusById(Sale sale,Integer flag);

    //取消订单
    boolean cancelSaleById(Long sale_ID,Integer flag);
}
