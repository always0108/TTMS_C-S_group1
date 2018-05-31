package TTMS_Server.web.RESTful;


import TTMS_Server.model.ResponseResult;
import TTMS_Server.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("rest/employee")
public class Employee {

    @Autowired
    private EmployeeService employeeService;

    //列出所有用户
    @RequestMapping(value = "/getAllEmp",method = RequestMethod.GET)
    public ResponseResult getAllEmp(){
        return new ResponseResult(true,employeeService.getAllEmployeeByName());
    }

    //根据关键字查询用户
    @RequestMapping(value = "/getEmpByPartName",method = RequestMethod.GET)
    public ResponseResult getEmpByName(@RequestParam("name") String name){
        List<TTMS_Server.model.Employee> emps = employeeService.getAllEmployeeByPartName(name);
        if(emps == null){
            return new ResponseResult(false,"该用户不存在");
        }else{
            return new ResponseResult(true,emps);
        }
    }

    //添加用户
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseResult add(@ModelAttribute TTMS_Server.model.Employee emp){
        if (employeeService.addEmployee(emp))
            return new ResponseResult(true,"添加成功");
        else{
            return new ResponseResult(false,"该用户已存在");
        }
    }

    //删除用户
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public ResponseResult delete(@RequestParam("id") String id){
        if (employeeService.deleteEmployeeById(new Integer(id)))
            return new ResponseResult(true,"删除成功");
        else{
            return new ResponseResult(false,"该用户不存在");
        }
    }

    //修改用户
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public ResponseResult update(@ModelAttribute TTMS_Server.model.Employee emp){
        if (employeeService.updateEmployeeById(emp))
            return new ResponseResult(true,"修改成功");
        else{
            return new ResponseResult(false,"新用户已存在");
        }
    }
}
