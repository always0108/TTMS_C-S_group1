package TTMS_Server.web.RESTful;

import TTMS_Server.model.DataDictionary;
import TTMS_Server.model.Play;
import TTMS_Server.model.ResponseResult;
import TTMS_Server.service.DataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "rest/dataDict",method = RequestMethod.GET)
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

    //列出所有的剧目
    @RequestMapping(value = "/getAllDataDict",method = RequestMethod.GET)
    public ResponseResult getAllDataDict(){
        List<DataDictionary> dataDictionaries = dataDictionaryService.getAllDataDcitByPartName("");
        if(dataDictionaries == null){
            return new ResponseResult(false,"没有找到相应的剧目");
        }else{
            return new ResponseResult(true,dataDictionaries);
        }
    }

    //根据关键字查询剧目
    @RequestMapping(value = "/getDataDictByPartName",method = RequestMethod.GET)
    public ResponseResult getDataDcitByPartName(@RequestParam("name") String name){
        List<DataDictionary> dataDictionaries = dataDictionaryService.getAllDataDcitByPartName(name);
        if(dataDictionaries == null){
            return new ResponseResult(false,"该剧目不存在");
        }else{
            return new ResponseResult(true,dataDictionaries);
        }
    }

    //添加数据字典
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseResult add(@ModelAttribute DataDictionary dataDictionary){
        if(dataDictionaryService.selectDataDictionaryByName(dataDictionary.getDict_name()) != null){
            return new ResponseResult(false,"该名称已存在");
        }
        dataDictionary.setDict_index(dataDictionaryService.getNextIndexByParentID(dataDictionary.getDict_parent_id()));
        if(dataDictionaryService.addDataDictionary(dataDictionary)){
            return new ResponseResult(true,"添加成功");
        }else{
            return new ResponseResult(true,"添加失败");
        }
    }

    //删除数据字典
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public ResponseResult delete(@RequestParam("id") Integer id){
        if (dataDictionaryService.deleteDataDictionaryById(id))
            return new ResponseResult(true,"删除成功");
        else{
            return new ResponseResult(false,"无法删除非叶子节点");
        }
    }

    //修改数据字典
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseResult update(@ModelAttribute DataDictionary dataDictionary){
        if (dataDictionaryService.updateDataDictionaryById(dataDictionary))
            return new ResponseResult(true,"修改成功");
        else{
            return new ResponseResult(false,"修改失败");
        }
    }







}
