package TTMS_Server.dao;

import TTMS_Server.dao.sqlprovider.EmployeeDynaSqlProvider;
import TTMS_Server.model.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EmployeeDAO {
    //根据用户名取得密码
    @Select("select emp_passwd from employee where emp_name = #{name}")
    String getPasswordByName(String name);

    //根据id获取员工信息
    @Select("select * from employee where emp_id = #{id}")
    Employee selectById(Integer id);

    //根据用户名获取员工信息
    @Select("select * from employee where emp_name = #{name}")
    Employee selectByName(String name);

    //获取所有员工的信息
    //@Select("select * from employee")
    @SelectProvider(type = EmployeeDynaSqlProvider.class, method = "selectSuitable")
    List<Employee> getAllEmployeeByName(Employee emp);

    //根据关键字获取匹配多有员工的信息
    @Select("select * from employee where emp_name like #{name}")
    List<Employee> getAllEmployeeByPartName(String name);

    //新增职员
    @Insert("insert into employee(emp_no,emp_type,emp_name,emp_passwd,emp_tel_num,emp_addr,emp_email) values " +
            "(#{emp_no},#{emp_type},#{emp_name},#{emp_passwd},#{emp_tel_num},#{emp_addr},#{emp_email})")
    void addEmployee(Employee emp);

    //删除职员
    @Delete("delete from employee where emp_id = #{id}")
    void deleteEmployeeById(Integer id);

    //修改职员
    @Update("update employee set emp_no = #{emp_no} , emp_type = #{emp_type}, emp_name = #{emp_name} ," +
            "emp_tel_num = #{emp_tel_num} , emp_addr = #{emp_addr} , emp_email = #{emp_email} " +
            "where emp_id = #{emp_id}")
    void updateEmployeeById(Employee emp);

    //修改密码
    @Update("update employee set emp_passwd = #{emp_passwd} where emp_id = #{emp_id}")
    void updatePasswordById(@Param("emp_id")Integer emp_id ,@Param("emp_passwd")String emp_passwd);

}