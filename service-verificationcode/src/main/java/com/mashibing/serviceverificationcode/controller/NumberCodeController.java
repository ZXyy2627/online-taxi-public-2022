package com.mashibing.serviceverificationcode.controller;

import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName NumberCodeController
 * @Description 请描述类的业务用途
 * @Author zhangxiang
 * @Date 2022/7/20
 * @Version 1.0
 **/
@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public String numberCode(@PathVariable("size") int size) {
        
        System.out.println("size: " + size);
        
        //生成验证码
        int numberCode = (int) ((Math.random() * 9 + 1) * (Math.pow(10, size - 1)));
        System.out.println("生成的验证码为:" + numberCode);
        JSONObject result = new JSONObject();
        result.put("code", 1);
        result.put("message", "success");
        JSONObject data = new JSONObject();
        data.put("numberCode", numberCode);
        result.put("data", data);
        return result.toString();
    }
}
