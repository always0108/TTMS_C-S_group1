package TTMS_Server.dao;

import TTMS_Server.model.DataDictionary;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DataDictionaryDAO {

    //根据ParentId获取下一个规则的下标
    @Select("select MAX(dict_index) from data_dict where dict_parent_id = #{id}")
    Integer getNextIndexByParentID(Integer id);

    //根据id获取数据字典的信息
    @Select("select * from data_dict where dict_id = #{id}")
    DataDictionary selectDataDictionaryById(Integer id);

    //根据名称获取数据字典的信息
    @Select("select * from data_dict where dict_name = #{name}")
    DataDictionary selectDataDictionaryByName(String name);

    //根据关键字获取匹配所有规则的信息
    @Select("select * from data_dict where dict_name like #{name}")
    List<DataDictionary> getAllDataDcitByPartName(String name);

    //根据父id获取所有信息
    @Select("select * from data_dict where dict_parent_id = #{dict_parent_id}")
    List<DataDictionary> selectDataDictionaryByParentId(Integer parentId);

    //根据name获取子类型的所有信息
    @Select("select * from data_dict where dict_parent_id in (select dict_id from data_dict " +
            "where dict_name = #{name})")
    List<DataDictionary> selectSonDataDictionaryByName(String name);

    //新增数据字典
    @Insert("insert into data_dict(dict_parent_id,dict_index," +
            "dict_name,dict_value) values (#{dict_parent_id},#{dict_index}," +
            "#{dict_name},#{dict_value})")
    void addDataDictionary(DataDictionary data_dict);

    //删除数据字典
    @Delete("delete from data_dict where dict_id = #{id}")
    void deleteDataDictionaryById(Integer id);

    //更新数据字典
    @Update("update data_dict set dict_parent_id = #{dict_parent_id}," +
            "dict_index = #{dict_index}, dict_name = #{dict_name}, " +
            "dict_value = #{dict_value} where dict_id = #{dict_id}")
    void updateDataDictionaryById(DataDictionary data_dict);
}
