package TTMS_Server.serviceimpl;


import TTMS_Server.dao.EmployeeMapper;
import TTMS_Server.model.Employee;
import TTMS_Server.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("InfoService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee getEmployeeByName(String name){
        return employeeMapper.selectByName(name);
    }

    @Override
    public List<Employee> getAllEmployeeByName(){
        return employeeMapper.getAllEmployeeByName();
    }

    @Override
    public List<Employee> getAllEmployeeByPartName(String name){
        return employeeMapper.getAllEmployeeByPartName("%"+name+"%");
    }

    @Override
    public boolean addEmployee(Employee emp){
        if (employeeMapper.selectByName(emp.getEmp_name()) == null){
            employeeMapper.addEmployee(emp);
            return true;
        }
        return false;
    }

    @Override
    public String getPasswordByName(String name) {return employeeMapper.getPasswordByName(name);}

}
