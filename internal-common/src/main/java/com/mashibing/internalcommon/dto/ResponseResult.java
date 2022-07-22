package com.mashibing.internalcommon.dto;

import com.mashibing.internalcommon.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName ResponseResutl
 * @Description 请描述类的业务用途
 * @Author zhangxiang
 * @Date 2022/7/20
 * @Version 1.0
 **/

@Data
@Accessors(chain = true)
public class ResponseResult<T> {

    private int code;
    private String message;
    private T data;

    /**
     * 成功响应的方法 无参
     * @return
     * @param <T>
     */
    public static <T> ResponseResult success() {
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getMessage());
    }

    /**
     * 成功响应的方法
     * @param data
     * @return
     * @param <T>
     */
    public static <T> ResponseResult success(T data) {
        return new ResponseResult<T>().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getMessage()).setData(data);
    }

    /**
     * 自定义默认的失败
     * @param data
     * @return
     * @param <T>
     */
    public static <T> ResponseResult fail(T data) {
        return new ResponseResult().setData(data);
    }

    /**
     * 自定义信息响应失败的方法
     * @param code
     * @param message
     * @return
     */
    public static ResponseResult fail(int code, String message) {
        return new ResponseResult().setCode(code).setMessage(message);
    }

    /**
     * 自定义失败
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static ResponseResult fail(int code, String message, String data) {
        return new ResponseResult().setCode(code).setMessage(message).setData(data);
    }
}
