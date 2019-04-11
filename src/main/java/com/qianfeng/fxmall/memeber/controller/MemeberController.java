package com.qianfeng.fxmall.memeber.controller;

import com.qianfeng.fxmall.memeber.exception.PasswordErrorException;
import com.qianfeng.fxmall.memeber.exception.UsernameNotFoundException;
import com.qianfeng.fxmall.memeber.po.WxbMemeber;
import com.qianfeng.fxmall.memeber.service.MemeberService;
import com.qianfeng.fxmall.memeber.vo.JsonResultVO;
import com.qianfeng.fxmall.memeber.vo.LoginInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/memeber")
public class MemeberController {
    @Autowired
    private MemeberService memeberService;

    @PostMapping("/login")
    public JsonResultVO login(LoginInfoVO loginInfoVO, HttpSession session){
        JsonResultVO jsonResultVO = new JsonResultVO();
        try {
            WxbMemeber memeber = memeberService.login(loginInfoVO);
            session.setAttribute("memeber",memeber);
            jsonResultVO.setCode(1);
        } catch (NullPointerException e){
            e.printStackTrace();
            jsonResultVO.setCode(0);
            jsonResultVO.setMsg("请输入QQ号");
        } catch(UsernameNotFoundException | PasswordErrorException e){
            e.printStackTrace();
            jsonResultVO.setCode(0);
            jsonResultVO.setMsg("QQ号或密码错误");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResultVO.setCode(0);
            jsonResultVO.setMsg("请联系管理员");
        }
        return jsonResultVO;
    }
}
