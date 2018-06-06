package TTMS_Server.web.RESTful;

import TTMS_Server.model.ResponseResult;
import TTMS_Server.model.Seat;
import TTMS_Server.service.SeatService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rest/seat")
public class SeatRestController {

    @Autowired
    private SeatService seatService;

    //根据演出厅ID获取该演出厅内的所有座位
    @RequestMapping(value = "/getStudioSeats",method = RequestMethod.GET)
    public ResponseResult getStudioSeats(@RequestParam("id") String studio_id){
        List<Seat> seats = seatService.selectStudioSeatsByStudioId(Integer.parseInt(studio_id));
        if(seats == null || seats.size() == 0 ){
            return new ResponseResult(false,"该演出厅没有初始化");
        }else{
            return new ResponseResult(true,seats);
        }
    }

    //根据演出厅ID初始化演出厅的座位
    @RequestMapping(value = "/init",method = RequestMethod.GET)
    public ResponseResult init(@RequestParam("id") String studio_id){
        if(seatService.initSeatByStudioId(Integer.parseInt(studio_id))){
            return new ResponseResult(false,"初始化成功");
        }else{
            return new ResponseResult(true,"该演出厅已经被初始化");
        }
    }

    //根据演出厅ID更新座位信息
    @RequestMapping(value = "/updateByStudioId",method = RequestMethod.POST)
    public ResponseResult update(@RequestParam("json") String json){
        JSONObject jsonObject = JSON.parseObject(json);
        Integer studio_id = (Integer) jsonObject.get("studio_id");
        Map<Integer,Integer> seats = (Map<Integer,Integer>)jsonObject.get("seats");
        if(seatService.updateStatusByStudioId(studio_id,seats)){
            return new ResponseResult(true,"修改成功");
        }else{
            return new ResponseResult(false,"修改失败");
        }
    }


}
