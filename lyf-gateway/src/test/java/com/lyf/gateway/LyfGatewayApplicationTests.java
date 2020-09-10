//package com.lyf.gateway;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtBuilder;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Date;
//
//@SpringBootTest
//class LyfGatewayApplicationTests {
//
//    @Test
//    void jwtTest() {
//        JwtBuilder builder= Jwts.builder()
//                .setId("007") //设置唯一编号
//                .setSubject("小马")//设置主题 可以是JSON数据
//                .setIssuedAt(new Date())//设置签发日期
//                .setExpiration(new Date(System.currentTimeMillis()+360000))//设置过期时间
//                .signWith(SignatureAlgorithm.HS512, "lyf1234567897");//设置签名 使用HS256算法，并设置SecretKey(字符串)
////构建 并返回一个字符串
//        System.out.println( builder.compact() );
//
//    }
//
//    @Test
//    public void jwtparser(){
//        String compactJwt = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIwMDciLCJzdWIiOiLlsI_pqawiLCJpYXQiOjE1OTgxMjQyNjcsImV4cCI6MTU5ODEyNDYyN30.YIyRpbFo7_3G0w57Y2nZe-cE2N_DItN-6EFTDwJENA1247aLtF877wAD-4MgE324lNB4MlNV-V-HkWPUiwSH5g";
//
//        Claims claims = Jwts.parser()
//                .setSigningKey("lyf1234567897")
//                .parseClaimsJws(compactJwt)
//                .getBody();
//        String id = claims.getId();
//        System.out.println(id);
//        System.out.println(claims);
//
//    }
//
//}
