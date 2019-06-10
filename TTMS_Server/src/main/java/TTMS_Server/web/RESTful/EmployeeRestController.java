package TTMS_Server.web.RESTful;


import TTMS_Server.model.Employee;
import TTMS_Server.model.ResponseResult;
import TTMS_Server.model.ResultPagination;
import TTMS_Server.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.security.util.Password;

import java.util.List;

@RestController
@RequestMapping("rest/employee")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    //列出所有用户
    @RequestMapping(value = "/getAllEmp",method = RequestMethod.GET)
    public ResponseResult getAllEmp(@ModelAttribute Employee emp){
        return new ResponseResult(true,employeeService.getAllEmployee(emp));
    }

    //根据关键字查询用户
    @RequestMapping(value = "/getEmpByPartName",method = RequestMethod.GET)
    public ResponseResult getEmpByName(@RequestParam("name") String name){
        List<Employee> emps = employeeService.getAllEmployeeByPartName(name);
        if(emps == null){
            return new ResponseResult(false,"该用户不存在");
        }else{
            return new ResponseResult(true,emps);
        }
    }

    //添加用户
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseResult add(@ModelAttribute Employee emp){
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
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseResult update(@ModelAttribute Employee emp){
        if (employeeService.updateEmployeeById(emp))
            return new ResponseResult(true,"修改成功");
        else{
            return new ResponseResult(false,"用户名已存在");
        }
    }

    //修改用户
    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
    public ResponseResult updatePassword(@RequestParam("emp_id")Integer emp_id,
                                         @RequestParam("oldPassword")String oldPassword,
                                         @RequestParam("newPassword")String newPassword){
        if (employeeService.updatePasswordById(emp_id,oldPassword,newPassword))
            return new ResponseResult(true,"修改成功");
        else{
            return new ResponseResult(false,"原密码输入错误");
        }
    }
}
