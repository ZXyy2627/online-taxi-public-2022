package com.mashibing.apipassenger.service;


import com.mashibing.apipassenger.remote.ServiceVerificationcodeClient;
import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.response.NumberCodeResponse;
import com.mashibing.internalcommon.response.TokenResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName VerificationCodeService
 * @Description 请描述类的业务用途
 * @Author zhangxiang
 * @Date 2022/7/20
 * @Version 1.0
 **/
@Service
public class VerificationCodeService {

    @Resource
    private ServiceVerificationcodeClient serviceVerificationcodeClient;

    /**
     * 生成验证码
     * @param passengerPhone
     * @return
     */
    public String generatorCode(String passengerPhone) {
        //调用验证码服务获取验证码
        System.out.println("调用验证码服务，获取验证码");

        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationcodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();

        System.out.println("remote number code :" + numberCode);

        //存入redis
        System.out.println("存入redis");

        //返回值
        JSONObject result = new JSONObject();
        result.put("code", 1);
        result.put("message", "success");
        return result.toString();
    }

    /**
     * 校验验证码
     * @param passengerPhone 手机号
     * @param verificationCode 用户输入的验证码
     */
    public ResponseResult<TokenResponse> checkCode(String passengerPhone, String verificationCode) {

        //根据手机号，去redis中取验证码
        System.out.println("根据手机号，去redis中取验证码");

        //校验验证码
        System.out.println("校验验证码");
        //如果用户不存在，做注册操作，就是插入数据
        System.out.println("注册用户");

        //颁发令牌
        System.out.println("颁发令牌");

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token value");

        return ResponseResult.success(tokenResponse);
    }
}
