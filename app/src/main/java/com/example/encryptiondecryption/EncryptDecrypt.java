package com.example.encryptiondecryption;

import android.util.Base64;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptDecrypt {
    private static final String ALGORITHM = "Blowfish";
    private static final String MODE = "Blowfish/CBC/PKCS5Padding";
    private static final String IV = "abcdefgh";

    public static String encrypt(String value , String alias) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(alias.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(MODE);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(IV.getBytes()));
        byte[] values = cipher.doFinal(value.getBytes());
        return Base64.encodeToString(values, Base64.DEFAULT);
    }

    public static String decrypt(String value , String alias ) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] values = Base64.decode(value, Base64.DEFAULT);
        SecretKeySpec secretKeySpec = new SecretKeySpec(alias.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(MODE);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(IV.getBytes()));
        return new String(cipher.doFinal(values));
    }
}