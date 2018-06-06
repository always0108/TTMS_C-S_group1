package TTMS_Server.web.RESTful;

import TTMS_Server.model.ResponseResult;
import TTMS_Server.model.Studio;
import TTMS_Server.service.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/studio")
public class StudioRestController {

    @Autowired
    private StudioService studioService;

    //获取所有演出厅
    @RequestMapping(value = "/getAllStudio",method = RequestMethod.GET)
    public ResponseResult getAllStudio(){
        List<Studio> studios = studioService.getAllStudioByPartName("");
        if(studios == null){
            return new ResponseResult(false,"该演出厅不存在");
        }else{
            return new ResponseResult(true,studios);
        }
    }

    //根据关键字查询演出厅
    @RequestMapping(value = "/getStudioByPartName",method = RequestMethod.GET)
    public ResponseResult getStudioByPartName(@RequestParam("name") String name){
        List<Studio> studios = studioService.getAllStudioByPartName(name);
        if(studios == null){
            return new ResponseResult(false,"该演出厅不存在");
        }else{
            return new ResponseResult(true,studios);
        }
    }

    //添加演出厅
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseResult add(@ModelAttribute Studio studio){
        if (studioService.addStudio(studio))
            return new ResponseResult(true,"添加成功");
        else{
            return new ResponseResult(false,"该演出厅已存在");
        }
    }

    //删除演出厅
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public ResponseResult delete(@RequestParam("id") String id){
        if (studioService.deleteStudioById(new Integer(id)))
            return new ResponseResult(true,"删除成功");
        else{
            return new ResponseResult(false,"该演出厅不存在");
        }
    }

    //修改演出厅
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseResult update(@ModelAttribute Studio studio){
        if (studioService.updateStudioById(studio))
            return new ResponseResult(true,"修改成功");
        else{
            return new ResponseResult(false,"演出厅已存在");
        }
    }
}
