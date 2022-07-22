package com.mashibing.servicepassengeruser.service;

import com.mashibing.internalcommon.dto.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Description 请描述类的业务用途
 * @Author zhangxiang
 * @Date 2022/7/22
 * @Version 1.0
 **/
@Service
public class UserService {
    public ResponseResult loginOrRegister(String passengerPhone) {
        //根据手机号查询用户信息
        System.out.println("user-service被调用，用户的手机号是："+passengerPhone);
        //判断用户信息是否存在

        //如果不存在插入用户信息

        //如果存在
        return ResponseResult.success();
    }
}
