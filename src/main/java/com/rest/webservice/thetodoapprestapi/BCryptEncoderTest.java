package com.rest.webservice.thetodoapprestapi;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.IntStream;

public class BCryptEncoderTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        IntStream.rangeClosed(1, 10).forEach(index ->
                System.out.println(bCryptPasswordEncoder.encode("password@123@#!")));
    }
}
