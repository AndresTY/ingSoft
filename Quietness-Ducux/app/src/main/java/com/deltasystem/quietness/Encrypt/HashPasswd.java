package com.deltasystem.quietness.Encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPasswd { //encrypt the password
    public static StringBuffer hashed(String passwd, String algoritm){
        StringBuffer sb = new StringBuffer();
        MessageDigest md;
        try {

            md = MessageDigest.getInstance(algoritm); //the encryption algorithm is defined
            md.update(passwd.getBytes()); //the bytes of the password to be encrypted are appended to it
            byte[] digest = md.digest();

            for(byte b: digest) {
                sb.append(String.format("%02x", b & 0xff)); //transforms into hex
            }

            return sb;

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace(); //report error
        }
        return sb;

    }
}
