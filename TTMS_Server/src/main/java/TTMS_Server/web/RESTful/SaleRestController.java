package TTMS_Server.web.RESTful;

import TTMS_Server.model.Employee;
import TTMS_Server.model.ResponseResult;
import TTMS_Server.model.Sale;
import TTMS_Server.model.SeatAndTicket;
import TTMS_Server.service.SaleService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("rest/sale")
public class SaleRestController {

    @Autowired
    private SaleService saleService;

    //根据演出厅ID更新座位信息
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseResult update(HttpServletRequest request,@RequestParam("json") String json,@RequestParam("flag") Integer flag){
        JSONObject jsonObject = JSON.parseObject(json);
        List<SeatAndTicket> seatAndTickets = JSONArray.parseArray(jsonObject.getString("tickets"),SeatAndTicket.class);
        Employee employee = (Employee)request.getSession().getAttribute("user");
        Sale sale = saleService.dealSale(seatAndTickets,flag,employee.getEmp_id());
        if(sale == null){
            return new ResponseResult(false,"订单生成失败,已被别人锁定");
        }else {
            return new ResponseResult(true,sale);
        }
    }

    //付款
    @RequestMapping(value = "/pay",method = RequestMethod.GET)
    public ResponseResult pay(@RequestParam("id")Long id,@RequestParam("flag")Integer flag){
        Sale sale = new Sale();
        sale.setSale_time(Calendar.getInstance().getTime());
        sale.setSale_ID(id);
        sale.setSale_change(BigDecimal.ZERO);
        sale.setSale_status(Short.parseShort("1"));
        if(saleService.updateStatusById(sale,flag)){
            return new ResponseResult(true,"交易成功");
        }else{
            return new ResponseResult(false,"交易失败");
        }
    }

    //取消
    @RequestMapping(value = "/cancel",method = RequestMethod.GET)
    public ResponseResult cancel(@RequestParam("id")Long id,@RequestParam("flag")Integer flag){
        if(saleService.cancelSaleById(id,flag)){
            return new ResponseResult(true,"取消成功");
        }else{
            return new ResponseResult(false,"取消失败");
        }
    }


}
