package TTMS_Server.serviceimpl;

import TTMS_Server.dao.PlayDAO;
import TTMS_Server.dao.StudioDAO;
import TTMS_Server.model.Play;
import TTMS_Server.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PlayService")
public class PlayServiceImpl implements PlayService {

    @Autowired
    private PlayDAO playDAO;

    //根据id获取演出厅信息
    @Override
    public Play selectPlayById(Integer id){
        return playDAO.selectPlayById(id);
    }

    //根据名称获取演出厅信息
    @Override
    public Play selectPlayByName(String name){
        return playDAO.selectPlayByName(name);
    }

    //根据关键字获取匹配的演出厅信息
    @Override
    public List<Play> getAllPlayByPartName(String name){
        return playDAO.getAllPlayByPartName("%"+name+"%");
    }

    //新增演出厅
    @Override
    public boolean addPlay(Play play){
        if (playDAO.selectPlayByName(play.getPlay_name()) == null){
            playDAO.addPlay(play);
            return true;
        }
        return false;
    }

    //删除演出厅
    @Override
    public boolean deletePlayById(Integer id){
        if (playDAO.selectPlayById(id) != null){
            playDAO.deletePlayById(id);
            return true;
        }
        return false;
    }

    //更新演出厅
    @Override
    public boolean updatePlayById(Play play){
        Play play_old = playDAO.selectPlayById(play.getPlay_id());

        //该用户不存在
        if(play_old == null){
            return false;
        }

        //名称没有改变或者新名称可用
        if(play_old.getPlay_name().equals(play.getPlay_name()) ||
                playDAO.selectPlayByName(play.getPlay_name()) == null){
            playDAO.updatePlayById(play);
            return true;
        }else{
            return false;
        }
    }
}
