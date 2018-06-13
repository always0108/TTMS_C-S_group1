package Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import model.EmployeeSale;
import model.PlaySale;
import util.Httpclient;

import java.math.BigDecimal;
import java.util.List;

public class SaleSrv {

    public SaleSrv() {}

    public List<EmployeeSale> getEmployeeAnalysis() {
        String url = "/analysis/getEmployeeAnalysis";
        String res = Httpclient.get(url);
        JSONObject jsonObject = JSON.parseObject(res);
        if(jsonObject.get("flag").equals(true)) {
            List<EmployeeSale> employeeSales = JSONArray.parseArray(jsonObject.getString("content"), EmployeeSale.class);
            return employeeSales;
        }else{
            return null;
        }
    }

    public List<PlaySale> getPlayTicketAmount() {
        String url = "/analysis/getPlayTicketAmount";
        String res = Httpclient.get(url);
        JSONObject jsonObject = JSON.parseObject(res);
        if(jsonObject.get("flag").equals(true)) {
            List<PlaySale> playSales = JSONArray.parseArray(jsonObject.getString("content"), PlaySale.class);
            return playSales;
        }else{
            return null;
        }
    }

    public BigDecimal getEmployeeAnalysisByEmployeeId(Integer id){
        String url = "/analysis/getEmployeeAnalysisByEmployeeId?id="+id;
        String res = Httpclient.get(url);
        JSONObject jsonObject = JSON.parseObject(res);
        if(jsonObject.get("flag").equals(true)) {
            BigDecimal employeeSale = JSONArray.parseObject(jsonObject.getString("content"), BigDecimal.class);
            return employeeSale;
        }else{
            return null;
        }
    }
}
