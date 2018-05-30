package Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.EmployeeProperty;
import util.Httpclient;

import java.util.List;

public class EmployeeSrv {

    public EmployeeSrv(){ }

    public ObservableList<EmployeeProperty> list() {
        String url = "/employee/getAllEmp";
        String res = Httpclient.get(url);
        JSONObject jsonObject = JSON.parseObject(res);
        List<EmployeeProperty> emps = JSONArray.parseArray(jsonObject.getString("content"), EmployeeProperty.class);
        ObservableList<EmployeeProperty> employeePropertyObservableList = FXCollections.observableArrayList();
        for (int i = 0; i < emps.size(); i++) {
            employeePropertyObservableList.add(emps.get(i));
        }
        return employeePropertyObservableList;
    }

    public ObservableList<EmployeeProperty> searchByName(String name){
        String url = "/employee/getEmpByPartName?name="+name;
        String res = Httpclient.get(url);
        JSONObject jsonObject = JSON.parseObject(res);
        List<EmployeeProperty> emps = JSONArray.parseArray(jsonObject.getString("content"), EmployeeProperty.class);
        ObservableList<EmployeeProperty> employeePropertyObservableList = FXCollections.observableArrayList();
        for (int i = 0; i < emps.size(); i++) {
            employeePropertyObservableList.add(emps.get(i));
        }
        return employeePropertyObservableList;
    }
}
