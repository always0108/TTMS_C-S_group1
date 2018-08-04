package TTMS_Server.service;

import TTMS_Server.model.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeService {

    String getPasswordByName(String name);

    List<Employee> getAllEmployee(Employee emp);

    List<Employee> getAllEmployeeByPartName(String name);

    Employee getEmployeeByName(String name);

    boolean addEmployee(Employee emp);

    boolean deleteEmployeeById(Integer id);

    boolean updateEmployeeById(Employee emp);

    //修改密码
    boolean updatePasswordById(Integer emp_id,String oldPassword,String newPassword);

}
