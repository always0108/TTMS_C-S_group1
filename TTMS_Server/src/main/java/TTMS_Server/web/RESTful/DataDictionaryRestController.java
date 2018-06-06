package TTMS_Server.web.RESTful;

import TTMS_Server.model.DataDictionary;
import TTMS_Server.model.ResponseResult;
import TTMS_Server.service.DataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest/datadictionary")
public class DataDictionaryRestController{
    @Autowired
    private DataDictionaryService dataDictionaryService;

    //根据父id获取数据字典
    @RequestMapping(value = "/getAllDataDictByParentId",method = RequestMethod.GET)
    public ResponseResult getAllDataDictionary(@RequestParam("parentId") Integer parentId){
        List<DataDictionary> dataDictionaries = dataDictionaryService.selectDataDictionaryByParentId(parentId);
        if(dataDictionaries==null){
            return new ResponseResult(false,"该数据字典不存在");
        }
        else {
            return new ResponseResult(true,dataDictionaries);
        }

    }

}
