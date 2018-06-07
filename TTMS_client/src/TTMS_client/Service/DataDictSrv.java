package Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import model.Data_dict;
import model.Play;
import util.Httpclient;

import java.util.List;
import java.util.Map;

public class DataDictSrv {

    public Map<String,Integer> getDataDictByParentName(String name){
        String url = "/dataDict/getDataDictByParentName?name="+name;
        String res = Httpclient.get(url);
        JSONObject jsonObject = JSON.parseObject(res);
        if(jsonObject.get("flag").equals(true)) {
            return (Map<String, Integer>)jsonObject.get("content");
        }else{
            return null;
        }
    }

    public List<Data_dict> getAllDataDict() {
        return searchDataDictByName("");
    }

    public List<Data_dict> searchDataDictByName(String name){
        String url = "/dataDict/getDataDictByPartName?name="+name;
        String res = Httpclient.get(url);
        JSONObject jsonObject = JSON.parseObject(res);
        if(jsonObject.get("flag").equals(true)) {
            List<Data_dict> data_dicts = JSONArray.parseArray(jsonObject.getString("content"), Data_dict.class);
            return data_dicts;
        }else{
            System.out.println("网络连接失败");
            return null;
        }
    }
}
