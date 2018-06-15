package TTMS_Server.serviceimpl;

import TTMS_Server.dao.SaleDAO;
import TTMS_Server.model.*;
import TTMS_Server.service.SaleItemService;
import TTMS_Server.service.SaleService;
import TTMS_Server.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("SaleService")
public class SaleServiceImpl implements SaleService{

    @Autowired
    private SaleDAO saleDAO;

    @Autowired
    private SaleItemService saleItemService;

    @Autowired
    private TicketService ticketService;

    //根据id获取信息
    public Sale selectSaleById(Long id) { return saleDAO.selectSaleById(id); }

    //获取所有票房
    public List<EmployeeSale> selectAllTicketPrice(){
        return saleDAO.selectAllTicketPrice();
    }

    //根据剧目查询票房
    public List<PlaySale> selectPlayTicketAmountByPlayID(){
        return saleDAO.selectPlayTicketAmountByPlayID();
    }

    //根据员工id获取销售额
    @Override
    public BigDecimal selectSaleAmountByEmployeeId(Integer id){
        return saleDAO.selectSaleAmountByEmployeeId(id);
    }

    //根据员工id获取未完成的订单
    @Override
    public List<Sale> selectCancelSaleByEmpoyeeId(Integer id){
        return saleDAO.selectCancelSaleByEmpoyeeId(id);
    }

    //根据员工name获取销售额
    @Override
    public List<EmployeeSale> selectSaleAmountsByEmployeeName(){
        return saleDAO.selectSaleAmountsByEmployeeName();
    }

    //增加
    public boolean addSale(Sale sale){
        if(saleDAO.selectSaleById(sale.getSale_ID())==null){
            saleDAO.addSale(sale);
            return true;
        }
        return false;
    }

    //删除
    public boolean deleteSaleById(Long id){
        if(saleDAO.selectSaleById(id)!=null){
            saleDAO.deleteSaleById(id);
            return true;
        }
        return false;
    }

    //更新
    public boolean updateSaleById(Sale sale){
        Sale sale_old = saleDAO.selectSaleById(sale.getSale_ID());
        //不存在
        if(sale_old==null){
            return false;
        }
        saleDAO.updateSaleById(sale);
        return  true;
    }

    //生成一个订单
    public synchronized Sale dealSale(List<SeatAndTicket> seatAndTickets,Integer flag,Integer emp_id){
        Sale newSale = new Sale();
        //先判断是否有座位被锁定
        for(SeatAndTicket seatAndTicket:seatAndTickets){
            if(!ticketService.isLocked(seatAndTicket.getTicket_id())){//座位被别人锁定
                return null;
            }
        }
        //生成订单
        addSale(newSale);
        BigDecimal total  = new BigDecimal("0");
        Date time = Calendar.getInstance().getTime();
        Ticket ticketTemp = new Ticket();
        ticketTemp.setTicket_status(Short.parseShort("-1"));
        for(SeatAndTicket seatAndTicket:seatAndTickets){
                saleItemService.addSaleItem(new Sale_item(seatAndTicket.getTicket_id(),
                        newSale.getSale_ID(),seatAndTicket.getTicket_price()));
            total = total.add(seatAndTicket.getTicket_price());
                ticketTemp.setTicket_id(seatAndTicket.getTicket_id());
                ticketTemp.setTicket_locked_time(time);
                ticketService.updateLockedTime(ticketTemp);
        }
        newSale.setSale_time(Calendar.getInstance().getTime());
        newSale.setEmp_id(emp_id);
        newSale.setSale_payment(total);
        newSale.setSale_type(flag.shortValue());
        newSale.setSale_status(Short.parseShort("0"));
        updateSaleById(newSale);
        return newSale;
    }

    //付款完成后更新状态，将座位解锁
    public boolean updateStatusById(Sale sale,Integer flag){
        Sale sale_old = saleDAO.selectSaleById(sale.getSale_ID());
        //不存在
        if(sale_old==null){
            return false;
        }
        saleDAO.updateStatusById(sale);
        if(flag == 1){
            ticketService.UnLockTickets(Short.parseShort("-1"),sale.getSale_ID());
        }else {
            ticketService.UnLockTickets(Short.parseShort("0"),sale.getSale_ID());
        }
        return true;
    }

    //取消订单
    public boolean cancelSaleById(Long sale_ID,Integer flag){
        Sale sale_old = saleDAO.selectSaleById(sale_ID);
        //不存在
        if(sale_old==null){
            return false;
        }
        if(flag == 1){
            //买票时若取消状态该变为0（可选）
            ticketService.UnLockTickets(Short.parseShort("0"),sale_ID);
        }else{
            //退票时若取消状态该变为-1（已选）
            ticketService.UnLockTickets(Short.parseShort("-1"),sale_ID);
        }
        saleDAO.cancelSaleById(sale_ID);
        return true;
    }
}
