import TTMS_Server.config.database.DbConfig;
import TTMS_Server.config.database.MyBatis;
import TTMS_Server.config.spring.RootConfig;
import TTMS_Server.config.spring.WebConfig;
import TTMS_Server.config.spring.WebInit;
import TTMS_Server.model.Sale_item;
import TTMS_Server.service.SaleItemService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {RootConfig.class,WebConfig.class,WebInit.class,MyBatis.class,DbConfig.class})
@WebAppConfiguration("src/main/resources") //1 此注解指定web资源的位置，默认为src/main/webapp
public class SaleItemTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Autowired
    private SaleItemService saleItemService;

    @Test
    public void Text(){
        //增加
        Sale_item  sale_item = new Sale_item(new Long(1),new Long(1),new BigDecimal(59));
        if(saleItemService.addSaleItem(sale_item)){
            System.out.println("添加成功");
        }
        else {
            System.out.println("已存在");
        }

        //删除
//        if(saleItemService.deleteSaleItemById(new Long(1))){
//            System.out.println("删除成功");
//        }
//        else{
//            System.out.println("不存在");
//        }

        //更新
//        Sale_item sale_item_new = new Sale_item();
//        sale_item_new.setSale_item_id(new Long(2));
//        if(saleItemService.updateSaleItemById(sale_item_new)){
//            System.out.println("更新成功");
//        }
//        else{
//            System.out.println("已存在");
//        }
    }
}
