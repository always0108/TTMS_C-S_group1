package TTMS_Server.service;

import TTMS_Server.model.Employee;

import java.util.List;

public interface EmployeeService {

    String getPasswordByName(String name);

    List<Employee> getAllEmployeeByName();

    List<Employee> getAllEmployeeByPartName(String name);

    Employee getEmployeeByName(String name);

    boolean addEmployee(Employee emp);
}
