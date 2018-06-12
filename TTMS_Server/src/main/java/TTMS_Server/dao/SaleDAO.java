package TTMS_Server.dao;

import TTMS_Server.model.Sale;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SaleDAO {
    //根据id获取信息
    @Select("select * from sale where sale_ID = #{ID}")
    Sale selectSaleById(Long id);

    //增加
    @Insert("insert into sale (emp_id , sale_time , sale_payment , sale_change ," +
            " sale_type , sale_status ) values (#{emp_id} , #{sale_time} , #{sale_payment} , " +
            "#{sale_change} , #{sale_type} , #{sale_status} )")
    @Options(useGeneratedKeys=true, keyProperty="sale_ID", keyColumn="sale_ID")
    void addSale(Sale sale);

    //删除
    @Delete("delete from sale where sale_ID = #{ID} ")
    void deleteSaleById(Long id);

    //更新
    @Update("update sale set emp_id = #{emp_id} , sale_time = #{sale_time} ," +
            "sale_payment = #{sale_payment} , sale_change = #{sale_change} , " +
            "sale_type = #{sale_type} , sale_status = #{sale_status} where sale_ID = #{sale_ID}")
    void updateSaleById(Sale sale);
}
