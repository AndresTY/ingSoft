package com.deltasystem.quietness.Encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPasswd {
    public static StringBuffer hashed(String passwd, String algoritm){
        StringBuffer sb = new StringBuffer();
        MessageDigest md;
        try {

            md = MessageDigest.getInstance(algoritm);
            md.update(passwd.getBytes());
            byte[] digest = md.digest();

            for(byte b: digest) {
                sb.append(String.format("%02x", b & 0xff));
            }

            return sb;

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return sb;

    }
}
