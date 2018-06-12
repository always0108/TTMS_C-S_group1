package TTMS_Server.serviceimpl;

import TTMS_Server.dao.SaleDAO;
import TTMS_Server.model.Sale;
import TTMS_Server.model.Sale_item;
import TTMS_Server.model.SeatAndTicket;
import TTMS_Server.model.Ticket;
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

        if(sale_old.getSale_ID().equals(sale.getSale_ID())||
                saleDAO.selectSaleById(sale.getSale_ID())==null){
            saleDAO.updateSaleById(sale);
            return  true;
        }
        else
            return false;
    }

    //生成一个订单
    public synchronized Sale dealSale(List<SeatAndTicket> seatAndTickets){
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
                total.add(seatAndTicket.getTicket_price());
                ticketTemp.setTicket_id(seatAndTicket.getTicket_id());
                ticketTemp.setTicket_locked_time(time);
                ticketService.updateLockedTime(ticketTemp);
        }
        newSale.setSale_time(Calendar.getInstance().getTime());
        //newSale.setEmp_id();
        newSale.setSale_payment(total);
        newSale.setSale_type(Short.parseShort("1"));
        newSale.setSale_status(Short.parseShort("0"));
        updateSaleById(newSale);
        return newSale;
    }
}
