package TTMS_Server.service;

import TTMS_Server.model.Sale_item;
import org.springframework.stereotype.Service;

public interface SaleItemService {

    //根据id获取
    Sale_item selectSaleItemById(Long id);

    //增加
    boolean addSaleItem(Sale_item sale_item);

    //删除
    boolean deleteSaleItemById(Long id);

    //更新
    boolean updateSaleItemById(Sale_item sale_item);
}

