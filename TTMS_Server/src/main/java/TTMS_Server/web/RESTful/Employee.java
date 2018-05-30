package TTMS_Server.web.RESTful;


import TTMS_Server.model.ResponseResult;
import TTMS_Server.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class Employee {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseResult login(@ModelAttribute TTMS_Server.model.Employee emp){
        if (employeeService.addEmployee(emp))
            return new ResponseResult(true,"添加成功");
        else{
            return new ResponseResult(false,"该用户已存在");
        }
    }

    @RequestMapping(value = "/getAllEmp",method = RequestMethod.GET)
    public ResponseResult getAllEmp(){
        return new ResponseResult(true,employeeService.getAllEmployeeByName());
    }

    @RequestMapping(value = "/getEmpByPartName",method = RequestMethod.GET)
    public ResponseResult getEmpByName(@RequestParam("name") String name){
        List<TTMS_Server.model.Employee> emps = employeeService.getAllEmployeeByPartName(name);
        if(emps == null){
            return new ResponseResult(false,"该用户不存在");
        }else{
            return new ResponseResult(true,emps);
        }
    }
}
