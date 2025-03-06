package com.cduestc.dorm.Controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.cduestc.dorm.Service.LoginService;
import com.cduestc.dorm.entity.MLoginTest;
import com.cduestc.dorm.entity.SLoginTest;
import com.cduestc.dorm.entity.result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/student")
    public result SLoginTest(String ID, String pwd) {
        SLoginTest sLoginTest = loginService.STestAccount(ID, pwd);
        if (sLoginTest != null) {
            return result.success(sLoginTest);
        } else {
            return result.error("账号不存在或密码输入错误");
        }
    }

    @GetMapping("/manager")
    public result MLoginTest(String ID, String pwd) {
        MLoginTest mLoginTest = loginService.MTestAccount(ID, pwd);
        if (mLoginTest != null) {
            return result.success(mLoginTest);
        } else {
            return result.error("账号不存在或密码输入错误");
        }

    }


    @GetMapping("/captcha")
    public void Verify(HttpServletRequest request, HttpServletResponse response) throws IOException {
            //定义图形验证码的长、宽、验证码字符数、干扰线宽度
            ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(150, 40, 5, 4);
            //图形验证码写出，可以写出到文件，也可以写出到流
            captcha.write(response.getOutputStream());
            //获取验证码中的文字内容
            String verifyCode = captcha.getCode();
            request.getSession().setAttribute("verifyCode", verifyCode);
    }
    @PostMapping("/loginc")
    public String loginByHutool(@RequestParam("verifyCode") String verifyCode,
                                HttpSession session){
        String captchaCode = session.getAttribute("verifyCode") + "";
        if(verifyCode.equals(captchaCode)){
            return "success";
        }
        return "false";
    }
}
