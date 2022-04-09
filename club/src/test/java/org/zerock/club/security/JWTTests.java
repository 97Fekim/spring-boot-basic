package org.zerock.club.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.club.security.util.JWTUtil;

public class JWTTests {

    private JWTUtil jwtUtil;

    @BeforeEach
    public void testBefore() {
        System.out.println("testBefore................");
        jwtUtil = new JWTUtil();
    }

    @Test
    public void testEncode() throws Exception {
        String email = "user95@gmail.com";
        String str = jwtUtil.generateToken(email);

        System.out.println(str);
    }

    @Test
    public void testValidate() throws Exception{

        String email = "글자수가16글자이상이어야한다는것이정말유감입니다";

        String str = jwtUtil.generateToken(email);

        Thread.sleep(5000);

        String resultEmail = jwtUtil.validateAndExtract(str);

        System.out.println(resultEmail);
    }

}
