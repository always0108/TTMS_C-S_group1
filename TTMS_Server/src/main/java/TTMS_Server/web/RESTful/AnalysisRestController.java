package TTMS_Server.web.RESTful;

import TTMS_Server.model.EmployeeSale;
import TTMS_Server.model.PlayPercent;
import TTMS_Server.model.PlaySale;
import TTMS_Server.model.ResponseResult;
import TTMS_Server.service.PlayService;
import TTMS_Server.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("rest/analysis")
public class AnalysisRestController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private PlayService playService;

    //售票分析
    @RequestMapping(value = "/getEmployeeAnalysis",method = RequestMethod.GET)
    public ResponseResult getEmployeeAnalysis(){
        List<EmployeeSale> employeeSales = saleService.selectAllTicketPrice();
        if(employeeSales == null){
            return new ResponseResult(false,"没有任何数据");
        }else{
            return new ResponseResult(true,employeeSales);
        }
    }

    //根据剧目查询票房
    @RequestMapping(value = "/getPlayTicketAmount",method = RequestMethod.GET)
    public ResponseResult getPlayTicketAmount(){
        List<PlaySale> playSales = saleService.selectPlayTicketAmountByPlayID();
        if(playSales == null){
            return new ResponseResult(false,"没有任何数据");
        }else{
            return new ResponseResult(true,playSales);
        }
    }

    //根据员工id获取纯销售分析
    @RequestMapping(value = "/getEmployeeAnalysisByEmployeeId",method = RequestMethod.GET)
    public ResponseResult getEmployeeAnalysisByEmployeeId(@RequestParam("id") Integer id){
        BigDecimal employeeSaleById = saleService.selectSaleAmountByEmployeeId(id);
        if(employeeSaleById == null){
            return new ResponseResult(false,"没有任何数据");
        }else{
            return new ResponseResult(true,employeeSaleById);
        }
    }

    //根据员工name获取销售分析
    @RequestMapping(value = "/getselectSaleAmountsByEmployeeName",method = RequestMethod.GET)
    public ResponseResult getselectSaleAmountsByEmployeeName(){
        List<EmployeeSale> employeeSales = saleService.selectSaleAmountsByEmployeeName();
        if(employeeSales == null){
            return new ResponseResult(false,"没有任何数据");
        }else{
            return new ResponseResult(true,employeeSales);
        }
    }


    //根据剧目名称获取电影上映比
    @RequestMapping( value = "/getPlayPercentByName",method = RequestMethod.GET)
    public ResponseResult getPlayPercent(){
        List<PlayPercent> playPercents = playService.getAllPlayPercentByName();
        if(playPercents == null){
            return new ResponseResult(false,"没有任何数据");
        }else{
            return new ResponseResult(true,playPercents);
        }
    }

    //根据剧目名称获取今日上映百分比
    @RequestMapping(value = "/getAllPlayPercentByDate",method = RequestMethod.GET)
    public ResponseResult getAllPlayPercentByDate(@RequestParam("date") String date){
        List<PlayPercent> playPercents = playService.getAllPlayPercentByDate(date);
        if(playPercents == null){
            return new ResponseResult(false,"没有任何数据");
        }else{
            return new ResponseResult(true,playPercents);
        }
    }
}
