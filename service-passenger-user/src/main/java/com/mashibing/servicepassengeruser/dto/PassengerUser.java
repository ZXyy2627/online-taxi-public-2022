package com.mashibing.servicepassengeruser.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName PassengerUser
 * @Description 请描述类的业务用途
 * @Author zhangxiang
 * @Date 2022/7/22
 * @Version 1.0
 **/
@Data
public class PassengerUser {

    private Long id;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private String passengerPhone;

    private String passengerName;

    private byte passengerGender;

    private byte state;
}
