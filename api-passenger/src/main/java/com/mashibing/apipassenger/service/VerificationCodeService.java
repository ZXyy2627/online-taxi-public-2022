package com.mashibing.apipassenger.service;


import com.mashibing.apipassenger.remote.ServicePassengerUserClient;
import com.mashibing.apipassenger.remote.ServiceVerificationcodeClient;
import com.mashibing.internalcommon.constant.CommonStatusEnum;
import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.request.VerificationCodeDTO;
import com.mashibing.internalcommon.response.NumberCodeResponse;
import com.mashibing.internalcommon.response.TokenResponse;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName VerificationCodeService
 * @Description 请描述类的业务用途
 * @Author zhangxiang
 * @Date 2022/7/20
 * @Version 1.0
 **/
@Service
public class VerificationCodeService {


    private String verificationCodePrefix = "verification-code-prefix-";
    @Resource
    private ServiceVerificationcodeClient serviceVerificationcodeClient;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 生成验证码
     * @param passengerPhone
     * @return
     */
    public ResponseResult generatorCode(String passengerPhone) {
        //调用验证码服务获取验证码
        System.out.println("调用验证码服务，获取验证码");

        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationcodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();

        System.out.println("remote number code :" + numberCode);

        //存入redis
        String key = generatorKeyByPhone(passengerPhone);
        stringRedisTemplate.opsForValue().set(key, numberCode+"", 2, TimeUnit.MINUTES);

        //通过短信服务商，将对应的验证码发送到手机上，阿里短信服务，腾讯短信通，华信

        return ResponseResult.success();
    }

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    /**
     * 校验验证码
     * @param passengerPhone 手机号
     * @param verificationCode 用户输入的验证码
     */
    public ResponseResult<TokenResponse> checkCode(String passengerPhone, String verificationCode) {

        //根据手机号，去redis中取验证码
        System.out.println("根据手机号，去redis中取验证码");

        //生成key
        String key = generatorKeyByPhone(passengerPhone);
        //根据key获取验证码
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println("获取到redis中的验证码是:"+codeRedis);

        //校验验证码
        System.out.println("校验验证码");
        if (StringUtils.isBlank(codeRedis)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getMessage());
        }
        if (!verificationCode.trim().equals(codeRedis.trim())) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getMessage());
        }
        //如果用户不存在，做注册操作，就是插入数据
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.loginOrRegister(verificationCodeDTO);

        //颁发令牌
        System.out.println("颁发令牌");

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token value");

        return ResponseResult.success(tokenResponse);
    }

    private String generatorKeyByPhone(String passengerPhone) {
        return verificationCodePrefix + passengerPhone;
    }
}
