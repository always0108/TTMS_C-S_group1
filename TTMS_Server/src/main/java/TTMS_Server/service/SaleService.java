package TTMS_Server.service;

import TTMS_Server.model.Sale;

public interface SaleService {

    //根据id获取信息
    Sale selectSaleById(Long id);

    //增加
    boolean addSale(Sale sale);

    //删除
    boolean deleteSaleById(Long id);

    //更新
    boolean updateSaleById(Sale sale);
}
