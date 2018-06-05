package TTMS_Server.dao;

import TTMS_Server.model.Sale_item;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SaleItemDAO {

    //根据id获取信息
    @Select("select * from sale_item where sale_item_id = #{id}")
    Sale_item selectSaleItemById(Long id);

    //增加
    @Insert("insert into sale_item( ticket_id ,sale_ID,sale_item_price) values " +
            "(#{ticket_id} , #{sale_ID} , #{sale_item_price}" )
    void addSaleItem(Sale_item sale_item);

    //删除
    @Delete("delete from sale_item where sale_id = #{id}")
    void deleteSaleItemById(Long id);

    //更新
    @Update("update sale_item set ticket_id = #{ticket_id} , " +
            "sale_ID = #{sale_ID} ,sale_item_price = #{sale_item_price} " +
            "where sale_item_id = #{sale_item_id}")
    void updateSaleItemById(Sale_item sale_item);
}
