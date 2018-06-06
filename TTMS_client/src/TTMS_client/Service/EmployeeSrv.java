package Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.EmployeeProperty;
import util.Httpclient;

import java.util.List;

public class EmployeeSrv {

    public EmployeeSrv() {}

    public List<Employee> list() {
        return searchByName("");
    }

    public List<Employee> searchByName(String name){
        String url = "/employee/getEmpByPartName?name="+name;
        String res = Httpclient.get(url);
        JSONObject jsonObject = JSON.parseObject(res);
        if(jsonObject.get("flag").equals(true)) {
            List<Employee> emps = JSONArray.parseArray(jsonObject.getString("content"), Employee.class);
            return emps;
        }else{
            System.out.println("网络连接失败");
            return null;
        }
    }
}
