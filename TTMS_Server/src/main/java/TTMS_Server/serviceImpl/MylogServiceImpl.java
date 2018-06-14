package TTMS_Server.serviceImpl;

import TTMS_Server.dao.MylogDAO;
import TTMS_Server.model.Mylog;
import TTMS_Server.service.MylogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MylogService")
public class MylogServiceImpl implements MylogService{
    @Autowired
    private MylogDAO mylogDAO;

    //根据id
    public Mylog selectMylogById(Integer id){
        return mylogDAO.selectMylogById(id);
    }

    //新增
    @Override
    public boolean addMylog(Mylog mylog){
        if(mylogDAO.selectMylogById(mylog.getLog_id())==null){
            mylogDAO.addMylog(mylog);
            return true;
        }
        return false;
    }

    //删除
    @Override
    public boolean deleteMylogById(Integer id){
        if(mylogDAO.selectMylogById(id)!=null){
            mylogDAO.deleteMylogById(id);
            return true;
        }
        return false;
    }
}
