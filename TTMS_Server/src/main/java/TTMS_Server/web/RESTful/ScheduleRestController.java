package TTMS_Server.web.RESTful;

import TTMS_Server.model.ResponseResult;
import TTMS_Server.model.Schedule;
import TTMS_Server.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/schedule")
public class ScheduleRestController {
    @Autowired
    private ScheduleService scheduleService;

    //根据剧目id获取演出计划
    @RequestMapping(value = "/getScheduleByPlayId",method = RequestMethod.GET)
    public ResponseResult getScheduleByPlayId(@RequestParam("id") Integer id){
        List<Schedule> schedules = scheduleService.selectScheduleByPlayId(id);
        if(schedules==null){
            return new ResponseResult(false,"演出计划不存在");
        }else{
            return new ResponseResult(true,schedules);
        }
    }

    //根据演出厅id获取演出计划
    @RequestMapping(value = "/getScheduleByStudioId",method = RequestMethod.GET)
    public ResponseResult getScheduleByStudioId(@RequestParam("id") Integer id){
        List<Schedule> schedules = scheduleService.selectScheduleByStudioId(id);
        if(schedules==null){
            return new ResponseResult(false,"演出计划不存在");
        }else{
            return new ResponseResult(true,schedules);
        }
    }

    //增加演出计划
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseResult add(@ModelAttribute Schedule schedule){
        if(scheduleService.addSchedule(schedule)){
            return new ResponseResult(true,"添加成功");
        }
        else{
            return new ResponseResult(false,"演出计划已存在");
        }
    }

    //删除演出计划
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ResponseResult delete(@RequestParam("id") Integer id){
        if(scheduleService.deleteScheduleById(id)){
            return new ResponseResult(true,"删除成功");
        }
        else{
            return new ResponseResult(false,"演出计划不存在");
        }
    }

    //更新演出计划
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseResult update(@ModelAttribute Schedule schedule){
        if(scheduleService.updateScheduleById(schedule)){
            return new ResponseResult(true,"更新成功");
        }
        else{
            return new ResponseResult(false,"演出计划已存在");
        }
    }
}
