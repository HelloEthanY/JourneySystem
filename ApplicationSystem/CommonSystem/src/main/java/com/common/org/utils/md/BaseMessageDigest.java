package com.common.org.utils.md;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class BaseMessageDigest {

    /***
     * 生成Mac密钥
     *
     * @param algorithm
     *            算法
     * @return 密钥(byte[])
     */
    public static byte[] generateKey(String algorithm) {
        Security.addProvider(new BouncyCastleProvider());
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
            SecretKey secretKey = keyGenerator.generateKey();

            return secretKey.getEncoded();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * MessageDigest加密
     *
     * @param plainText
     *            明文
     * @param algorithm
     *            算法
     * @return 加密结果(byte[])
     */
    public static byte[] encrypt(String plainText, String algorithm) {
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        digest.update(plainText.getBytes());

        return digest.digest();
    }

    public static byte[] encryptMac(String plainText, String algorithm,
                                    byte[] key) {
        Security.addProvider(new BouncyCastleProvider());
        byte[] input = null;
        try {
            input = plainText.getBytes("UTF-8");
            SecretKey secretKey = new SecretKeySpec(key, algorithm);
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);

            return mac.doFinal(input);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }
}
