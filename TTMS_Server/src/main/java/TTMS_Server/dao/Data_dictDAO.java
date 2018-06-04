package TTMS_Server.dao;

import TTMS_Server.model.Data_dict;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Data_dictDAO {

    //根据id获取数据字典的信息
    @Select("select * from data_dict where dict_id = #{id}")
    Data_dict selectDataDictionaryById(Integer id);

    //根据名称获取数据字典的信息
    @Select("select * from data_dict where dict_name = #{name}")
    Data_dict selectDataDictionaryByName(String name);

    //根据关键字获取匹配的数据字典的信息
    @Select("select * from data_dict where dict_name like #{name}")
    List<Data_dict> getAllDataDictionaryByPartName(String name);

    //新增数据字典
    @Insert("insert into data_dict(dict_parent_id,dict_index," +
            "dict_name,dict_value) values (#{dict_parent_id},#{dict_index}," +
            "#{dict_name},#{dict_value})")
    void addDataDictionary(Data_dict data_dict);

    //删除数据字典
    @Insert("delete from data_dict where dict_id = #{id}")
    void deleteDataDictionaryById(Integer id);

    //更新数据字典
    @Insert("update data_dict set dict_parent_id = #{dict_parent_id},dict_index = #{dict_index}," +
            "dict_name = #{dict_name},dict_value = #{dict_value}")
    void updateDataDictionaryById(Data_dict data_dict);
}
