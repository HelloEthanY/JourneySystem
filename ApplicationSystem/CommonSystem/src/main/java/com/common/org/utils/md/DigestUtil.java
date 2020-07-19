package com.common.org.utils.md;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.util.encoders.Hex;

public class DigestUtil extends BaseMessageDigest {
    /***
     * MD2信息摘要算法
     *
     * @param plainText
     *            明文
     * @return 加密结果
     */
    public static byte[] md2(String plainText) {
        return encrypt(plainText, "MD2");
    }

    /***
     * MD2信息摘要算法
     *
     * @param plainText
     *            明文
     * @return 加密结果
     */
    public static String md2Hex(String plainText) {
        byte[] cipher = md2(plainText);
        return new String(Hex.encode(cipher));
    }

    /***
     * MD2信息摘要算法
     *
     * @param plainText
     *            明文
     * @param cut16
     *            是否生成16位
     * @param upperCase
     *            是否大写
     * @return 加密结果
     */
    public static String md2Hex(String plainText, boolean cut16,
                                boolean upperCase) {
        byte[] cipher = md2(plainText);
        String result = new String(Hex.encode(cipher));
        if (cut16) {
            result = cut(result);
        }
        if (upperCase) {
            result = result.toUpperCase();
        }
        return result;
    }

    /***
     * MD4信息摘要算法
     *
     * @param plainText
     *            明文
     * @return 加密结果
     */
    public static byte[] md4(String plainText) {
        return encrypt(plainText, "MD4");
    }

    /***
     * MD4信息摘要算法
     *
     * @param plainText
     *            明文
     * @return 加密结果
     */
    public static String md4Hex(String plainText) {
        byte[] cipher = md4(plainText);
        return new String(Hex.encode(cipher));
    }

    /***
     * MD4信息摘要算法
     *
     * @param plainText
     *            明文
     * @param cut16
     *            是否生成16位
     * @param upperCase
     *            是否大写
     * @return 加密结果
     */
    public static String md4Hex(String plainText, boolean cut16,
                                boolean upperCase) {
        byte[] cipher = md4(plainText);
        String result = new String(Hex.encode(cipher));
        if (cut16) {
            result = cut(result);
        }
        if (upperCase) {
            result = result.toUpperCase();
        }
        return result;
    }

    /***
     * MD5信息摘要算法
     *
     * @param plainText
     *            明文
     * @return 加密结果
     */
    public static byte[] md5(String plainText) {
        return encrypt(plainText, "MD5");
    }

    /***
     * MD5信息摘要算法
     *
     * @param plainText
     *            明文
     * @return 加密结果
     */
    public static String md5Hex(String plainText) {
        byte[] cipher = md5(plainText);
        return new String(Hex.encode(cipher));
    }

    /***
     * MD5信息摘要算法
     *
     * @param plainText
     *            明文
     * @param cut16
     *            是否生成16位
     * @param upperCase
     *            是否大写
     * @return
     */
    public static String md5Hex(String plainText, boolean cut16,
                                boolean upperCase) {
        byte[] cipher = md5(plainText);
        String result = new String(Hex.encode(cipher));
        if (cut16) {
            result = cut(result);
        }
        if (upperCase) {
            result = result.toUpperCase();
        } else {
            result = result.toLowerCase();
        }
        return result;
    }

    /***
     * 去掉密文前后8位
     *
     * @param cipherText
     *            密文
     * @return cipherText.substring(8, 24)
     */
    private static String cut(String cipherText) {
        return cipherText.substring(8, 24);
    }

    public static void main(String[] args) {

        // String pass = "111111";
        // String salt = "0cXs";
        //System.out.println(md5Hex(md5Hex(pass) + salt));
		/*System.out.println(DigestUtil.md2Hex("admin"));
		System.out.println(DigestUtil.md4Hex("admin"));
		System.out.println(DigestUtil.md5Hex("admin"));*/
		/*
		byte[] array = "admin".getBytes();
		byte[] array2 = Hex.encode(array);
		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(array2));
		d477887b0636e5d87f79cc25c99d7dc9
		*/

        System.out.println(DigestUtil.md5Hex(DigestUtil.md5Hex("a123456") + ""));
    }

    //base64 解码
    public static String decode(byte[] bytes) {
        return new String(Base64.decodeBase64(bytes));
    }

    //base64 编码
    public static String encode(byte[] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }
}