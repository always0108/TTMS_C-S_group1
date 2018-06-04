package TTMS_Server.service;

import TTMS_Server.model.Data_dict;

import java.util.List;

public interface Data_dictService {

    //根据id获取数据字典
    Data_dict selectDataDictionaryById(Integer id);

    //根据名称获取数据字典
    Data_dict selectDataDictionaryByName(String name);

    //根据关键字获取匹配的数据字典的信息
    List<Data_dict> getAllDataDictionaryByPartName(String name);

    //新增数据字典
    boolean addDataDictionary(Data_dict data_dict);

    //删除数据字典
    boolean deleteDataDictionaryById(Integer id);

    //更新数据字典
    boolean updateDataDictionaryById(Data_dict data_dict);
}
