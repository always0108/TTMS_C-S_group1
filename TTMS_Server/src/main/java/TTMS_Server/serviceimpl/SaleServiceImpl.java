package TTMS_Server.serviceimpl;

import TTMS_Server.dao.SaleDAO;
import TTMS_Server.model.Sale;
import TTMS_Server.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SaleService")
public class SaleServiceImpl implements SaleService{

    @Autowired
    private SaleDAO saleDAO;

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
}
