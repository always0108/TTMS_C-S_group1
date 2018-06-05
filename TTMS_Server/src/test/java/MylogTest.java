import TTMS_Server.config.database.DbConfig;
import TTMS_Server.config.database.MyBatis;
import TTMS_Server.config.spring.RootConfig;
import TTMS_Server.config.spring.WebConfig;
import TTMS_Server.config.spring.WebInit;
import TTMS_Server.model.Mylog;
import TTMS_Server.model.Studio;
import TTMS_Server.service.MylogService;
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

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {RootConfig.class,WebConfig.class,WebInit.class,MyBatis.class,DbConfig.class})
@WebAppConfiguration("src/main/resources") //1 此注解指定web资源的位置，默认为src/main/webapp
public class MylogTest {


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Autowired
    private MylogService mylogService;

    @Test
    public void test(){
        //增加
//        Mylog mylog = new Mylog("6:30","100");
//        if (mylogService.addMylog(mylog))
//           System.out.println("添加成功");
//        else
//           System.out.println("已存在");
        //删除
        if (mylogService.deleteMylogById(2))
           System.out.println("删除成功");
        else
           System.out.println("不存在");
//

    }


}
