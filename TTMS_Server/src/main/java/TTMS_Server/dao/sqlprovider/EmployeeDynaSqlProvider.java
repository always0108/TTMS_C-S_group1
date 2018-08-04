package TTMS_Server.dao.sqlprovider;
import TTMS_Server.model.Employee;
import org.apache.ibatis.jdbc.SQL;

public class EmployeeDynaSqlProvider {

    //方法中的关键字是区分大小写的  SQL SELECT WHERE
    //该方法会根据传递过来的map中的参数内容  动态构建sql语句
    public String selectSuitable(Employee emp) {
        return new SQL() {
            {
                SELECT("*");
                FROM("employee");
                if (emp.getEmp_name() != null && !emp.getEmp_name().equals("")) {
                    emp.setEmp_name(emp.getEmp_name()+"%");
                    WHERE("emp_name like #{emp_name}");
                }
            }
        }.toString() + " LIMIT #{offset},#{limit}";
    }
}
