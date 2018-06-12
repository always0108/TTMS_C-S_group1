package Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import model.SeatAndTicket;
import model.Studio;
import util.Httpclient;

import java.util.List;

public class TicketSrv {

    public TicketSrv() {
    }

    public List<SeatAndTicket> getTicketByScheduleId(Integer id){
        String url = "/ticket/getTicketByScheduleId?id="+id;
        String res = Httpclient.get(url);
        JSONObject jsonObject = JSON.parseObject(res);
        if(jsonObject.get("flag").equals(true)) {
            List<SeatAndTicket> seatAndTickets = JSONArray.parseArray(jsonObject.getString("content"), SeatAndTicket.class);
            return seatAndTickets;
        }else{
            System.out.println("网络连接失败");
            return null;

        }
    }
}
