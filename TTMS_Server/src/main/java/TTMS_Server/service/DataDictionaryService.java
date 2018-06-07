package TTMS_Server.service;

import TTMS_Server.model.DataDictionary;

import java.util.List;

public interface DataDictionaryService {

    //根据ParentId获取下一个规则的下标
    Integer getNextIndexByParentID(Integer id);

    //根据id获取数据字典
    DataDictionary selectDataDictionaryById(Integer id);

    //根据名称获取数据字典
    DataDictionary selectDataDictionaryByName(String name);

    //根据关键字获取匹配所有规则的信息
    List<DataDictionary> getAllDataDcitByPartName(String name);

    //根据父id获取数据字典
    List<DataDictionary> selectDataDictionaryByParentId(Integer parentId);


    //根据名称获取子类型数据字典
    List<DataDictionary> selectSonDataDictionaryByName(String name);

    //新增数据字典
    boolean addDataDictionary(DataDictionary data_dict);

    //删除数据字典
    boolean deleteDataDictionaryById(Integer id);

    //更新数据字典
    boolean updateDataDictionaryById(DataDictionary data_dict);
}
