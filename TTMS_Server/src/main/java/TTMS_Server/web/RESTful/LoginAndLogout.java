package TTMS_Server.web.RESTful;

import TTMS_Server.model.ResponseResult;
import TTMS_Server.service.EmployeeService;
import TTMS_Server.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class LoginAndLogout {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseResult login(HttpServletRequest request, @RequestParam("username") String username,
                                @RequestParam("password") String password){
        String vaildPasswd = employeeService.getPasswordByName(username);
        if(MD5.codeByMD5(password).equals(vaildPasswd)){
            if(request.getSession(false) != null) {
                request.getSession(false).invalidate();
            }
            TTMS_Server.model.Employee emp = employeeService.getEmployeeByName(username);
            request.getSession().setAttribute("user", emp);
            return new ResponseResult(true,emp);
        }else{
            return new ResponseResult(false,"身份验证失败");
        }
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public void logout(HttpServletRequest request){
        if(request.getSession(false) != null) {
            request.getSession(false).invalidate();
        }
    }
}
