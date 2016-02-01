package com.john.service;

import org.slf4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordService {
    static Logger log = org.slf4j.LoggerFactory.getLogger(PasswordService.class);

    public static String getSha2Password(String password, Long id) {
        password += "h2j34&*@#$1243KL#@!_~s";
        password += id * 7549 / 256;
        MessageDigest messageDigest;
        byte[] digest = new byte[0];
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(password.getBytes());
            messageDigest.update(password.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            log.error("", e);
        }
        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        log.debug("md5Hex: " + md5Hex);

        return md5Hex;
    }
}
