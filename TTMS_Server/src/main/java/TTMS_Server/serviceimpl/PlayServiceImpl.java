package TTMS_Server.serviceimpl;

import TTMS_Server.dao.PlayDAO;
import TTMS_Server.model.Play;
import TTMS_Server.model.PlayPercent;
import TTMS_Server.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
        List<Play> plays = playDAO.getAllPlayByPartName("%"+name+"%");
        for(Play play:plays){
            play.byteToStr();
        }
        return plays;
    }

    //根据剧目名称获取剧目当天场次占比
    @Override
    public List<PlayPercent> getAllPlayPercentByName(){
        List<PlayPercent> playPercents = playDAO.getAllPlayPercentByName();
        return playPercents;
    }

    //剧目百分比
    public List<PlayPercent> getAllPlayPercentByDate(String date) {
        String start = date + " 00:00:00";
        String end = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date startDate = df.parse(start);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.DATE, 1);
            end = df.format(calendar.getTime());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return playDAO.getAllPlayPercentByDate(start,end);
    }

    //根据日期列出当天上映的演出计划
    @Override
    public List<Play> selectPlayByDate(String date){
        String start = date + " 00:00:00";
        String end = null;
        Date startDate = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            startDate = df.parse(start);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.DATE,1);
            end = df.format(calendar.getTime());
        }catch (ParseException e){
            e.printStackTrace();
        }
        List<Play> plays = playDAO.selectPlayByDate(start,end);
        for(Play play:plays){
            play.byteToStr();
        }
        return plays;
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
