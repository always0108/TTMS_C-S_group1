import TTMS_Server.config.database.DbConfig;
import TTMS_Server.config.database.MyBatis;
import TTMS_Server.config.spring.RootConfig;
import TTMS_Server.config.spring.WebConfig;
import TTMS_Server.config.spring.WebInit;
import TTMS_Server.model.Employee;
import TTMS_Server.model.EmployeeSale;
import TTMS_Server.model.Sale;
import TTMS_Server.service.SaleService;
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
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {RootConfig.class,WebConfig.class,WebInit.class,MyBatis.class,DbConfig.class})
@WebAppConfiguration("src/main/resources") //1 此注解指定web资源的位置，默认为src/main/webapp
public class SaleTest {
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Autowired
    private SaleService saleService;

    @Test
    public void Test(){
//        //增加
//        Sale sale = new Sale(1,new Date(),new BigDecimal(100),new BigDecimal(100),new Short("-1"),new Short("-1"));
//        if(saleService.addSale(sale)){
//            System.out.println("添加成功");
//        }
//        else
//            System.out.println("已存在");

        //删除
//        if(saleService.deleteSaleById(new Long(1))){
//            System.out.println("删除成功");
//        }
//        else{
//            System.out.println("不存在");
//        }

        //更新
//        Sale sale_new = new Sale(1,new Date(),new BigDecimal(200),new BigDecimal(100),new Short("-1"),new Short("-1"));
//        sale_new.setSale_ID(new Long(1));
//        if(saleService.updateSaleById(sale_new)){
//            System.out.println("更新成功");
//        }
//        else{
//            System.out.println("已存在");
//        }
        List<EmployeeSale> employeeSales = saleService.selectSaleAmountsByEmployeeName();
        for(EmployeeSale employeeSale:employeeSales){
            System.out.println(employeeSale.getEmp_name());
            System.out.println(employeeSale.getSaleAmount());
        }
        }
    }

