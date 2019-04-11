package com.qianfeng.fxmall.memeber.service;

import com.qianfeng.fxmall.memeber.exception.PasswordErrorException;
import com.qianfeng.fxmall.memeber.mapper.MemeberMapper;
import com.qianfeng.fxmall.memeber.po.WxbMemeber;
import com.qianfeng.fxmall.memeber.utils.MD5Utils;
import com.qianfeng.fxmall.memeber.vo.LoginInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemeberService {
    @Autowired
    private MemeberMapper memeberMapper;

    public WxbMemeber login(LoginInfoVO loginInfoVO) throws Exception{
        if(loginInfoVO == null){
            throw new NullPointerException("param is null");
        }
        String qqNum = loginInfoVO.getQqNum();
        String password = loginInfoVO.getPassword();
        if(qqNum == null){
            throw new NullPointerException("qqNum is null");
        }
        WxbMemeber memeber = memeberMapper.checkQQNum(qqNum);
        if(memeber == null){
            throw new NullPointerException("memeber not found");
        }
        String salt = memeber.getAccount();
        String md5 = MD5Utils.md5(password, salt);
        if(!md5.equals(memeber.getPassword())){
            throw new PasswordErrorException("password is error");
        }
        return memeber;
    }

}
