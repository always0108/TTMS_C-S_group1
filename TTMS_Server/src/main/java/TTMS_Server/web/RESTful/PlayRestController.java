package TTMS_Server.web.RESTful;

import TTMS_Server.model.Play;
import TTMS_Server.model.ResponseResult;
import TTMS_Server.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/play")
public class PlayRestController {

    @Autowired
    private PlayService playService;

    //列出所有的剧目
    @RequestMapping(value = "/getAllPlay",method = RequestMethod.GET)
    public ResponseResult getAllPlay(){
        List<Play> plays = playService.getAllPlayByPartName("");
        if(plays == null || plays.size()==0){
            return new ResponseResult(false,"没有涨到相应的剧目");
        }else{
            return new ResponseResult(true,plays);
        }
    }

    //根据关键字查询剧目
    @RequestMapping(value = "/getStudioByPartName",method = RequestMethod.GET)
    public ResponseResult getStudioByPartName(@RequestParam("name") String name){
        List<Play> plays = playService.getAllPlayByPartName(name);
        if(plays == null || plays.size()==0){
            return new ResponseResult(false,"该演出厅不存在");
        }else{
            return new ResponseResult(true,plays);
        }
    }

    //添加剧目
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseResult add(@ModelAttribute Play play){
        if (playService.addPlay(play))
            return new ResponseResult(true,"添加成功");
        else{
            return new ResponseResult(false,"该剧目已存在");
        }
    }

    //删除剧目
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public ResponseResult delete(@RequestParam("id") Integer id){
        if (playService.deletePlayById(id))
            return new ResponseResult(true,"删除成功");
        else{
            return new ResponseResult(false,"该剧目不存在");
        }
    }

    //修改剧目
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseResult update(@ModelAttribute Play play){
        if (playService.updatePlayById(play))
            return new ResponseResult(true,"修改成功");
        else{
            return new ResponseResult(false,"该剧目已存在");
        }
    }
}
