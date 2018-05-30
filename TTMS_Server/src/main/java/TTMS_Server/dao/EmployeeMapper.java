package TTMS_Server.dao;

import TTMS_Server.model.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EmployeeMapper {

    @Select("select * from employee where emp_name = #{name}")
    Employee selectByName(String name);

    @Select("select emp_passwd from employee where emp_name = #{name}")
    String getPasswordByName(String name);

    @Select("select * from employee")
    List<Employee> getAllEmployeeByName();

    @Select("select * from employee where emp_name like #{name}")
    List<Employee> getAllEmployeeByPartName(String name);

    @Insert("insert into employee(emp_no,emp_type,emp_name,emp_passwd,emp_tel_num,emp_addr,emp_email) values " +
            "(#{emp_no},#{emp_type},#{emp_name},#{emp_passwd},#{emp_tel_num},#{emp_addr},#{emp_email})")
    void addEmployee(Employee emp);

}