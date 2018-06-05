package TTMS_Server.serviceimpl;

import TTMS_Server.dao.SaleItemDAO;
import TTMS_Server.model.Sale_item;
import TTMS_Server.service.SaleItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SaleItemService")
public class SaleItemServiceImpl implements SaleItemService{
    @Autowired
    private SaleItemDAO saleItemDAO;

    //根据id获取信息
    @Override
    public Sale_item selectSaleItemById(Long id) {return saleItemDAO.selectSaleItemById(id);}

    //增加
    @Override
    public boolean addSaleItem(Sale_item sale_item) {
        if(saleItemDAO.selectSaleItemById(sale_item.getSale_item_id()) == null){
            saleItemDAO.addSaleItem(sale_item);
            return true;
        }
        else
            return false;
    }

    //删除
    @Override
    public boolean deleteSaleItemById(Long id){
        if(saleItemDAO.selectSaleItemById(id)!=null){
            saleItemDAO.deleteSaleItemById(id);
            return true;
        }
        return  false;
    }

    //更新
    @Override
    public boolean updateSaleItemById(Sale_item sale_item) {
        Sale_item sale_item_old = saleItemDAO.selectSaleItemById(sale_item.getSale_item_id());

        //不存在
        if (sale_item_old == null) {
            return false;
        }

        if (sale_item_old.getSale_item_id().equals(sale_item.getSale_item_id()) ||
                saleItemDAO.selectSaleItemById(sale_item.getSale_item_id()) == null) {
            saleItemDAO.updateSaleItemById(sale_item);
            return  true;
        }
        else
            return  false;
    }
}
