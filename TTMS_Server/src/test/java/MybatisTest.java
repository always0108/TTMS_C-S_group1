

import TTMS_Server.config.database.DbConfig;
import TTMS_Server.config.database.MyBatis;
import TTMS_Server.config.spring.RootConfig;
import TTMS_Server.config.spring.WebConfig;
import TTMS_Server.config.spring.WebInit;
import TTMS_Server.model.Employee;
import TTMS_Server.service.EmployeeService;
import TTMS_Server.utils.MD5;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {RootConfig.class,WebConfig.class,WebInit.class,MyBatis.class,DbConfig.class})
@WebAppConfiguration("src/main/resources") //1 此注解指定web资源的位置，默认为src/main/webapp
/** 
* MybatisTest Tester. 
* 
* @author <Authors name> 
* @since <pre>五月 24, 2018</pre> 
* @version 1.0 
*/ 
public class MybatisTest {

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: test() 
* 
*/
    @Autowired
    private EmployeeService employeeService;

    @Test
    public void test(){
        Employee emp = new Employee("04163164",1,"limeng","123456",
                "17691169108","西安","ldx19980108@gmail.com");
        if (employeeService.addEmployee(emp))
           System.out.println("添加成功");
        else
           System.out.println("该用户已存在");
  }


} 
