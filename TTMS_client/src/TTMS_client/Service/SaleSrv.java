package Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import model.EmployeeSale;
import model.PlayPercent;
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

    public List<EmployeeSale> getselectSaleAmountsByEmployeeName(){
        String url = "/analysis/getselectSaleAmountsByEmployeeName";
        String res = Httpclient.get(url);
        JSONObject jsonObject = JSON.parseObject(res);
        if(jsonObject.get("flag").equals(true)) {
            List<EmployeeSale> employeeSales = JSONArray.parseArray(jsonObject.getString("content"), EmployeeSale.class);
            return employeeSales;
        }else{
            return null;
        }
    }

    public List<PlayPercent> getPlayPercentByName(){
        String url = "/analysis/getPlayPercentByName";
        String res = Httpclient.get(url);
        JSONObject jsonObject = JSON.parseObject(res);
        if(jsonObject.get("flag").equals(true)) {
            List<PlayPercent> playPercents = JSONArray.parseArray(jsonObject.getString("content"), PlayPercent.class);
            return playPercents;
        }else{
            return null;
        }
    }

    public List<PlayPercent> getAllPlayPercentByDate(String date){
        String url = "/analysis/getAllPlayPercentByDate?date="+date;
        String res = Httpclient.get(url);
        JSONObject jsonObject = JSON.parseObject(res);
        if(jsonObject.get("flag").equals(true)) {
            List<PlayPercent> playPercents = JSONArray.parseArray(jsonObject.getString("content"), PlayPercent.class);
            return playPercents;
        }else{
            return null;
        }
    }
}
