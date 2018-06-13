package TTMS_Server.dao;

import TTMS_Server.model.EmployeeSale;
import TTMS_Server.model.PlaySale;
import TTMS_Server.model.Sale;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Mapper
@Repository
public interface SaleDAO {
    //根据id获取信息
    @Select("select * from sale where sale_ID = #{ID}")
    Sale selectSaleById(Long id);

    //获取票房
    @Select("select emp_name,saleAmount from (select emp_id,sum(sale_payment * sale_type) " +
            "as saleAmount from sale where sale_status=1 group by emp_id)emp natural join employee;")
    List<EmployeeSale> selectAllTicketPrice();

    //根据剧目查询票房
    @Select("select play_name,SUM(sale_item_price*sale_type) AS playTicketAmount from " +
            "(select * from (select play_id,play_name from play) AS smallplay natural join " +
            "(select play_id,sale_item_price,sale_type from schedule natural join " +
            "(select sched_id,sale_item_price,sale_type from ticket natural join " +
            "(select ticket_id,sale_item_price,sale_type from sale_item natural join " +
            "(select sale_ID,sale_type from sale where sale_status = 1) " +
            "AS sonsale_item) AS sonticket) AS sonschedule) AS sonplay) AS result " +
            "GROUP BY play_id;")
    List<PlaySale> selectPlayTicketAmountByPlayID();

    //根据员工id查询销售额
    @Select("select sum(sale_payment * sale_type) as saleAmount" +
            " from sale where sale_status=1 and emp_id = #{emp_id}")
    BigDecimal selectSaleAmountByEmployeeId(Integer id);

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

    //更新状态
    @Update("update sale set sale_time = #{sale_time} , sale_change = #{sale_change} , " +
            "sale_status = #{sale_status} where sale_ID = #{sale_ID}")
    void updateStatusById(Sale sale);

    //取消订单
    @Update("update sale set sale_status = -1 where sale_ID = #{sale_ID}")
    void cancelSaleById(Long sale_ID);
}
