package com.mashibing.internalcommon.request;

import lombok.Data;

/**
 * @ClassName VerificationCodeDTO
 * @Description 请描述类的业务用途
 * @Author zhangxiang
 * @Date 2022/7/20
 * @Version 1.0
 **/
@Data
public class VerificationCodeDTO {

    private String passengerPhone;

    private String verificationCode;
}
