package com.mashibing.apipassenger.remote;

import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName VerificationCodeService
 * @Description 请描述类的业务用途
 * @Author zhangxiang
 * @Date 2022/7/21
 * @Version 1.0
 **/
@FeignClient("service-verificationcode")
public interface ServiceVerificationcodeClient {

    @RequestMapping(value = "/numberCode/{size}", method = RequestMethod.GET)
    ResponseResult<NumberCodeResponse> getNumberCode(@PathVariable("size") int size);
}
