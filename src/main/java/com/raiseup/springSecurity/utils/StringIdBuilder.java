package com.raiseup.springSecurity.utils;

import java.security.SecureRandom;
import java.util.Random;

public class StringIdBuilder {
    private static final String CHARS="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefjhigklmnopqrstuvwxyz";
    private static final Random RANDOM= new SecureRandom();

    public static String buildID(int length){
        return generateId(length);
    }

    private static String generateId(int length) {

        StringBuilder stringBuilder= new StringBuilder();
        for(int i=1;i<=length;i++){
            stringBuilder.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
        }
        return stringBuilder.toString();
    }
}
