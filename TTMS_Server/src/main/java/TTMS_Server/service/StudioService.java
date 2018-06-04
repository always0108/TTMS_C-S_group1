package TTMS_Server.service;

import TTMS_Server.model.Studio;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudioService {

    //根据id获取演出厅信息
    Studio selectStudioById(Integer id);

    //根据名称获取演出厅信息
    Studio selectStudioByName(String name);

    //根据关键字获取匹配的演出厅信息
    List<Studio> getAllStudioByPartName(String name);

    //新增演出厅
    boolean addStudio(Studio studio);

    //删除演出厅
    boolean deleteStudioById(Integer id);

    //更新演出厅
    boolean updateStudioById(Studio studio);
}
