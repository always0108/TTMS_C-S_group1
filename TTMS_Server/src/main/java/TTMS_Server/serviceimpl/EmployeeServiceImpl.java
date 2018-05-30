package TTMS_Server.serviceimpl;


import TTMS_Server.dao.EmployeeDAO;
import TTMS_Server.model.Employee;
import TTMS_Server.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("InfoService")
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
    public String getPasswordByName(String name) {return employeeDAO.getPasswordByName(name);}

}
