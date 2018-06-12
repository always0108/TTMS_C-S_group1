package TTMS_Server.web.RESTful;

import TTMS_Server.model.ResponseResult;
import TTMS_Server.model.SeatAndTicket;
import TTMS_Server.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest/ticket")
public class TicketRestController {

    @Autowired
    private TicketService ticketService;

    //获取所有演出厅
    @RequestMapping(value = "/getTicketByScheduleId",method = RequestMethod.GET)
    public ResponseResult getTicketByScheduleId(@RequestParam("id") Integer id){
        List<SeatAndTicket> seatAndTickets = ticketService.selectTicketByScheduleId(id);
        if(seatAndTickets == null){
            return new ResponseResult(false,"该演出厅不存在");
        }else{
            return new ResponseResult(true,seatAndTickets);
        }
    }
}
