package TTMS_Server.serviceimpl;


import TTMS_Server.dao.EmployeeDAO;
import TTMS_Server.model.Employee;
import TTMS_Server.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public Employee getEmployeeByName(String name){
        return employeeDAO.selectByName(name);
    }

    @Override
    public List<Employee> getAllEmployeeByName(){
        return employeeDAO.getAllEmployeeByName();
    }

    @Override
    public List<Employee> getAllEmployeeByPartName(String name){
        return employeeDAO.getAllEmployeeByPartName("%"+name+"%");
    }

    @Override
    public boolean addEmployee(Employee emp){
        if (employeeDAO.selectByName(emp.getEmp_name()) == null){
            employeeDAO.addEmployee(emp);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEmployeeById(Integer id){
        if (employeeDAO.selectById(id) != null){
            employeeDAO.deleteEmployeeById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateEmployeeById(Employee emp){
        Employee emptemp = employeeDAO.selectById(emp.getEmp_id());
        //该用户不存在
        if(emptemp == null){
            return false;
        }

        //名称没有改变或者新名称可用
        if(emptemp.getEmp_name().equals(emp.getEmp_name()) ||
                employeeDAO.selectByName(emp.getEmp_name()) == null){
            employeeDAO.updateEmployeeById(emp);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String getPasswordByName(String name) {return employeeDAO.getPasswordByName(name);}

}
