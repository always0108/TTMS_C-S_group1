package TTMS_Server.serviceimpl;

import TTMS_Server.dao.DataDictionaryDAO;
import TTMS_Server.model.DataDictionary;
import TTMS_Server.service.DataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("Data_dictService")
public class DataDictionaryServiceImpl implements DataDictionaryService {

    @Autowired
    private DataDictionaryDAO data_dictDAO;

    //根据id获取数据字典信息
    @Override
    public DataDictionary selectDataDictionaryById(Integer id) {
        return data_dictDAO.selectDataDictionaryById(id);
    }

    //根据名称获取数据字典信息
    @Override
    public DataDictionary selectDataDictionaryByName(String name){
        return data_dictDAO.selectDataDictionaryByName(name);
    }

    //根据关键字获取匹配的数据字典信息
    @Override
    public List<DataDictionary> getAllDataDictionaryByPartName(String name){
        return data_dictDAO.getAllDataDictionaryByPartName("%"+name+"%");
    }

    //新增数据字典
    @Override
    public boolean addDataDictionary(DataDictionary data_dict){
        if(data_dictDAO.selectDataDictionaryByName(data_dict.getDict_name())==null){
            data_dictDAO.addDataDictionary(data_dict);
            return true;
        }
        else{
            return false;
        }
    }

    //删除数据字典
    @Override
    public boolean deleteDataDictionaryById(Integer id){
        if(data_dictDAO.selectDataDictionaryById(id)!=null){
            data_dictDAO.deleteDataDictionaryById(id);
            return true;
        }
        else{
            return false;
        }
    }

    //更新数据字典
    @Override
    public boolean updateDataDictionaryById(DataDictionary data_dict){
        DataDictionary data_dict_old = data_dictDAO.selectDataDictionaryById(data_dict.getDict_id());

        if(data_dict_old==null){
            return false;
        }

        //名称没有改变或者新名称可用
        if(data_dict_old.getDict_name().equals(data_dict.getDict_name())||data_dictDAO.selectDataDictionaryByName(data_dict.getDict_name())==null){
            data_dictDAO.updateDataDictionaryById(data_dict);
            return true;
        }
        else{
            return false;
        }
    }
}
