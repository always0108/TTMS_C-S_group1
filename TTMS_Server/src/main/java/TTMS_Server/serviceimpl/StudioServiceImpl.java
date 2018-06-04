package TTMS_Server.serviceimpl;

import TTMS_Server.dao.StudioDAO;
import TTMS_Server.model.Studio;
import TTMS_Server.service.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("StudioService")
public class StudioServiceImpl implements StudioService {

    @Autowired
    private StudioDAO studioDAO;

    //根据id获取演出厅信息
    @Override
    public Studio selectStudioById(Integer id){
        return studioDAO.selectStudioById(id);
    }

    //根据名称获取演出厅信息
    @Override
    public Studio selectStudioByName(String name){
        return studioDAO.selectStudioByName(name);
    }

    //根据关键字获取匹配的演出厅信息
    @Override
    public List<Studio> getAllStudioByPartName(String name){
        return studioDAO.getAllStudioByPartName("%"+name+"%");
    }

    //新增演出厅
    @Override
    public boolean addStudio(Studio studio){
        if (studioDAO.selectStudioByName(studio.getStudio_name()) == null){
            studioDAO.addStudio(studio);
            return true;
        }
        return false;
    }

    //删除演出厅
    @Override
    public boolean deleteStudioById(Integer id){
        if (studioDAO.selectStudioById(id) != null){
            studioDAO.deleteStudioById(id);
            return true;
        }
        return false;
    }

    //更新演出厅
    @Override
    public boolean updateStudioById(Studio studio){
        Studio studio_old = studioDAO.selectStudioById(studio.getStudio_id());

        //该用户不存在
        if(studio_old == null){
            return false;
        }

        //名称没有改变或者新名称可用
        if(studio_old.getStudio_name().equals(studio.getStudio_name()) ||
                studioDAO.selectStudioByName(studio.getStudio_name()) == null){
            studioDAO.updateStudioById(studio);
            return true;
        }else{
            return false;
        }
    }
}
