
import TTMS_Server.config.database.DbConfig;
import TTMS_Server.config.database.MyBatis;
import TTMS_Server.config.spring.RootConfig;
import TTMS_Server.config.spring.WebConfig;
import TTMS_Server.config.spring.WebInit;
import TTMS_Server.model.Employee;
import TTMS_Server.model.Play;
import TTMS_Server.model.Seat;
import TTMS_Server.model.Studio;
import TTMS_Server.service.EmployeeService;
import TTMS_Server.service.PlayService;
import TTMS_Server.service.SeatService;
import TTMS_Server.service.StudioService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {RootConfig.class,WebConfig.class,WebInit.class,MyBatis.class,DbConfig.class})
@WebAppConfiguration("src/main/resources") //1 此注解指定web资源的位置，默认为src/main/webapp
public class SeatTest {


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Autowired
    private SeatService seatService;

    @Autowired
    private StudioService studioService;

    @Test
    public void test(){

//        //批量生成座位
//        if (seatService.initSeatByStudioId(3)){
//            System.out.println("初始化座位成功");
//        }else{
//            System.out.println("影厅已经被初始化过了");
//        }

//        //删除
//        if (seatService.deleteSeatById(1))
//           System.out.println("删除成功");
//        else
//           System.out.println("该座位不存在");
//
//        //修改
//        Seat seat = new Seat();
//        seat.setSeat_id(1);
//        if (seatService.updateSeatById(seat))
//            System.out.println("更新成功");
//        else
//            System.out.println("该座位不存在");

        Map<Integer,Integer> seats = new HashMap<>();
        seats.put(5,1);
        seats.put(6,1);
        seatService.updateStatusByStudioId(2,seats);
    }


}