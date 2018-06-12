package TTMS_Server.web.RESTful;

import TTMS_Server.model.ResponseResult;
import TTMS_Server.model.Sale;
import TTMS_Server.model.SeatAndTicket;
import TTMS_Server.service.SaleService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest/sale")
public class SaleRestController {

    @Autowired
    private SaleService saleService;

    //根据演出厅ID更新座位信息
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseResult update(@RequestParam("json") String json){
        JSONObject jsonObject = JSON.parseObject(json);
        List<SeatAndTicket> seatAndTickets = JSONArray.parseArray(jsonObject.getString("tickets"),SeatAndTicket.class);
        Sale sale = saleService.dealSale(seatAndTickets);
        if(sale == null){
            return new ResponseResult(false,"生成订单失败,已被别人上锁");
        }else {
            return new ResponseResult(true,sale);
        }
    }
}
