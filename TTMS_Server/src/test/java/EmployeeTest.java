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
* EmployeeTest Tester.
* 
* @author <Authors name> 
* @since <pre>五月 24, 2018</pre> 
* @version 1.0 
*/ 
public class EmployeeTest {

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
       //增加
        init();
//        //删除
//        if (employeeService.deleteEmployeeById(3))
//           System.out.println("删除成功");
//        else
//           System.out.println("该用户不存在");
//
//        //修改
//        Employee emp = new Employee("04163164",1,"limeng","123456",
//                "17691169108","西安","test@gmail.com");
//        emp.setEmp_id(3);
//        if (employeeService.updateEmployeeById(emp))
//            System.out.println("更新成功");
//        else
//            System.out.println("该用户名已存在");
  }

  //初始化数据，增加一个经理、一个售票员、一个管理员
  public void init(){
      Employee manager = new Employee("00000002",1,"manager","123456",
              "13815360180","西安","manager@gmail.com");
      employeeService.addEmployee(manager);

      Employee sellman = new Employee("00000003",2,"sellman","123456",
              "18915479666","西安","sellman@gmail.com");
      employeeService.addEmployee(sellman);

      Employee admin = new Employee("00000001",3,"admin","123456",
              "17691145006","西安","admin@gmail.com");
      employeeService.addEmployee(admin);
  }


} 
