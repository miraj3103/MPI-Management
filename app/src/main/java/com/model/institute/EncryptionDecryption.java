package com.model.institute;

import android.text.BoringLayout;
import android.util.Base64;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionDecryption {

    public static String MY_KEY = "";

    static {
        try {
            MY_KEY = encryption("Miraj6517","A8d7G6j2B5f3E1h9");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //Encrypt start
    public static String encryption(String text, String password) throws Exception {
        String message = text;
        byte[] bytes = message.getBytes("UTF-8");

        String encrypt_password = password;
        byte[] passwordBytes = encrypt_password.getBytes("UTF-8");
        SecretKeySpec secretKey = new SecretKeySpec(passwordBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] securedBytes = cipher.doFinal(bytes);
        String securedMessage = Base64.encodeToString(securedBytes, Base64.DEFAULT);

        return securedMessage;
    }
}

