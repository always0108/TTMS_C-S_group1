package TTMS_Server.web.RESTful;

import TTMS_Server.model.EmployeeSale;
import TTMS_Server.model.PlaySale;
import TTMS_Server.model.ResponseResult;
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

    //根据员工id获取销售分析
    @RequestMapping(value = "/getEmployeeAnalysisByEmployeeId",method = RequestMethod.GET)
    public ResponseResult getEmployeeAnalysisByEmployeeId(@RequestParam("id") Integer id){
        BigDecimal employeeSaleById = saleService.selectSaleAmountByEmployeeId(id);
        if(employeeSaleById == null){
            return new ResponseResult(false,"没有任何数据");
        }else{
            return new ResponseResult(true,employeeSaleById);
        }
    }
}
