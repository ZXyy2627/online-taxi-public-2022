package com.mashibing.apipassenger.remote;

import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName ServicePassengerUserClient
 * @Description 请描述类的业务用途
 * @Author zhangxiang
 * @Date 2022/7/22
 * @Version 1.0
 **/
@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO);
}
