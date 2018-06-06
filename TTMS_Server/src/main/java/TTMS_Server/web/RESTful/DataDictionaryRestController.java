package TTMS_Server.web.RESTful;

import TTMS_Server.model.DataDictionary;
import TTMS_Server.model.ResponseResult;
import TTMS_Server.service.DataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rest/dataDict")
public class DataDictionaryRestController{
    @Autowired
    private DataDictionaryService dataDictionaryService;

    //根据父名称获取数据字典
    @RequestMapping(value = "/getDataDictByParentName",method = RequestMethod.GET)
    public ResponseResult getDataDictByParentName(@RequestParam("name") String name){
        List<DataDictionary> dataDictionaries = dataDictionaryService.selectSonDataDictionaryByName(name);
        if(dataDictionaries==null || dataDictionaries.size() == 0){
            return new ResponseResult(false,"该数据字典不存在");
        }
        else {
            Map<String,Integer> res = new HashMap<>();
            for(DataDictionary dataDictionary:dataDictionaries){
                res.put(dataDictionary.getDict_name(),dataDictionary.getDict_id());
            }
            return new ResponseResult(true,res);
        }

    }

}
