package com.mashibing.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JwtUtils
 * @Description 请描述类的业务用途
 * @Author zhangxiang
 * @Date 2022/7/22
 * @Version 1.0
 **/
public class JwtUtils {
    //盐
    private static final String SIGN = "CPFmsb!@#$$";

    private static final String JWT_KEY_PHONE = "phone";

    private static final String JWT_KEY_IDENTITY = "identity";

    /**
     * 生成Token
     * @param passengerPhone
     * @return
     */
    public static String generatorToken(String passengerPhone, String identity) {
        Map<String, String> map = new HashMap<>();
        map.put("passengerPhone", passengerPhone);
        //token 过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        Date date = calendar.getTime();

        JWTCreator.Builder builder = JWT.create();
        //整合map
        map.forEach(
                (k,v) -> {
                    builder.withClaim(k, v);
                }
        );
        //整合过期时间
        builder.withExpiresAt(date);

        //生成token
        return builder.sign(Algorithm.HMAC256(SIGN));
    }


    public static String parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        Claim claim = verify.getClaim(JWT_KEY_PHONE);
        return claim.toString();
    }

    public static void main(String[] args) {

        String token = generatorToken("18507142627","passenger");
        System.out.println("生成的token是:" + token);
        String passengerPhone = parseToken(token);
        System.out.println("解析的电话号码是:" + passengerPhone);
    }
}
