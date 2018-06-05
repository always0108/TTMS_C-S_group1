import TTMS_Server.config.database.DbConfig;
import TTMS_Server.config.database.MyBatis;
import TTMS_Server.config.spring.RootConfig;
import TTMS_Server.config.spring.WebConfig;
import TTMS_Server.config.spring.WebInit;
import TTMS_Server.model.Schedule;
import TTMS_Server.service.ScheduleService;
import TTMS_Server.service.StudioService;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {RootConfig.class,WebConfig.class,WebInit.class,MyBatis.class,DbConfig.class})
@WebAppConfiguration("src/main/resources") //1 此注解指定web资源的位置，默认为src/main/webapp
public class ScheduleTest {


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Autowired
    private ScheduleService scheduleService;

    @Test
    public void Test() {

        //增加
//        Schedule schedule = new Schedule(1,1,new Date(15),new BigDecimal(49));
//        if (scheduleService.addSchedule(schedule)) {
//            System.out.println("添加成功");
//        }
//        else
//            System.out.println("已存在");

        //删除
//        if(scheduleService.deleteScheduleById(7)){
//            System.out.println("删除成功");
//        }
//        else
//            System.out.println("不存在");

        //更新
        Schedule schedule_new = new Schedule(1,1,new Date(),new BigDecimal(69));
        schedule_new.setSched_id(8);
        if(scheduleService.updateScheduleById(schedule_new)){
            System.out.println("更新成功");
        }
        else
            System.out.println("已存在");
    }
}
