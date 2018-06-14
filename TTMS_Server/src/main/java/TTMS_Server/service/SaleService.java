package TTMS_Server.service;

import TTMS_Server.model.EmployeeSale;
import TTMS_Server.model.PlaySale;
import TTMS_Server.model.Sale;
import TTMS_Server.model.SeatAndTicket;

import java.math.BigDecimal;
import java.util.List;

public interface SaleService {

    //根据id获取信息
    Sale selectSaleById(Long id);

    //获取所有票房
    List<EmployeeSale> selectAllTicketPrice();

    //根据剧目查询票房
    List<PlaySale> selectPlayTicketAmountByPlayID();

    //根据员工id查询纯销售额
    BigDecimal selectSaleAmountByEmployeeId(Integer id);

    //根据员工name获取销售额
    List<EmployeeSale> selectSaleAmountsByEmployeeName();


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
