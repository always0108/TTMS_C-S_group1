package TTMS_Server.serviceimpl;

import TTMS_Server.dao.Data_dictDAO;
import TTMS_Server.model.Data_dict;
import TTMS_Server.service.Data_dictService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("Data_dictService")
public class Data_dictServiceImpl implements Data_dictService {

    @Autowired
    private Data_dictDAO data_dictDAO;

    //根据id获取数据字典信息
    @Override
    public Data_dict selectDataDictionaryById(Integer id) {
        return data_dictDAO.selectDataDictionaryById(id);
    }

    //根据名称获取数据字典信息
    @Override
    public Data_dict selectDataDictionaryByName(String name){
        return data_dictDAO.selectDataDictionaryByName(name);
    }

    //根据关键字获取匹配的数据字典信息
    @Override
    public List<Data_dict> getAllDataDictionaryByPartName(String name){
        return data_dictDAO.getAllDataDictionaryByPartName("%"+name+"%");
    }

    //新增数据字典
    @Override
    public boolean addDataDictionary(Data_dict data_dict){
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
    public boolean updateDataDictionaryById(Data_dict data_dict){
        Data_dict data_dict_old = data_dictDAO.selectDataDictionaryById(data_dict.getDict_id());

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
