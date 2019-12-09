package com.mylq.sluggard.core.common.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Objects;

/**
 * 密码工具类
 *
 * @author WangRunQian
 * @date 2019/8/19
 * @since 1.0.0
 */
public class PwdUtil {

    private PwdUtil() {
    }

    private static final String MD5 = "MD5";
    private static final String SHA1 = "SHA1";
    private static final String HMAC_MD5 = "HmacMD5";
    private static final String HMAC_SHA1 = "HmacSHA1";
    private static final String DES = "DES";
    private static final String AES = "AES";

    private static final int KEY_SIZE_DES = 0;
    private static final int KEY_SIZE_AES = 128;

    /**
     * MD5算法加密（不可逆）
     *
     * @param plainText 要加密的明文
     * @return 加密后的密文
     */
    public static String encryptByMD5(String plainText) {
        return messageDigest(plainText, MD5);
    }

    /**
     * MD5算法加密（不可逆）
     *
     * @param plainText 要加密的明文
     * @param key 密钥
     * @return 加密后的密文
     */
    public static String encryptByMD5(String plainText, String key) {
        return keyGeneratorMac(plainText, HMAC_MD5, key);
    }

    /**
     * SHA1算法加密（不可逆）
     *
     * @param plainText 要加密的明文
     * @return 加密后的密文
     */
    public static String encryptBySHA1(String plainText) {
        return messageDigest(plainText, SHA1);
    }

    /**
     * SHA1算法加密（不可逆）
     *
     * @param plainText 要加密的明文
     * @param key 密钥
     * @return 加密后的密文
     */
    public static String encryptBySHA1(String plainText, String key) {
        return keyGeneratorMac(plainText, HMAC_SHA1, key);
    }

    /**
     * DES算法加密
     *
     * @param plainText 要加密的明文
     * @param key 密钥
     * @return 加密后的密文
     */
    public static String encryptByDES(String plainText, String key) {
        return keyGeneratorES(plainText, DES, key, KEY_SIZE_DES, true);
    }

    /**
     * DES算法解密
     *
     * @param cipherText 要解密的密文
     * @param key 密钥
     * @return 解密后的明文
     */
    public static String decryptByDES(String cipherText, String key) {
        return keyGeneratorES(cipherText, DES, key, KEY_SIZE_DES, false);
    }

    /**
     * AES算法加密
     *
     * @param plainText 要加密的明文
     * @param key 密钥
     * @return 加密后的密文
     */
    public static String encryptByAES(String plainText, String key) {
        return keyGeneratorES(plainText, AES, key, KEY_SIZE_AES, true);
    }

    /**
     * AES算法解密
     *
     * @param cipherText 要解密的密文
     * @param key 密钥
     * @return 解密后的明文
     */
    public static String decryptByAES(String cipherText, String key) {
        return keyGeneratorES(cipherText, AES, key, KEY_SIZE_AES, false);
    }

    /**
     * 使用异或加密
     *
     * @param plainText 要加密的明文
     * @param key 密钥
     * @return 加密后的密文
     */
    public static String encryptByXOR(String plainText, String key) {
        byte[] bs = plainText.getBytes();
        for (int i = 0; i < bs.length; i++) {
            bs[i] = (byte) ((bs[i]) ^ key.hashCode());
        }
        return parseByte2HexStr(bs);
    }

    /**
     * 使用异或解密
     *
     * @param cipherText 要解密的密文
     * @param key 密钥
     * @return 解密后的明文
     */
    public static String decryptByXOR(String cipherText, String key) {
        byte[] bs = parseHexStr2Byte(cipherText);
        for (int i = 0; i < bs.length; i++) {
            bs[i] = (byte) ((bs[i]) ^ key.hashCode());
        }
        return new String(bs);
    }

    /**
     * 使用Base64加密
     *
     * @param plainText 要加密的明文
     * @return 加密后的密文
     */
    public static String encryptByBase64(String plainText) {
        return Base64.encode(plainText.getBytes());
    }

    /**
     * 使用Base64解密
     *
     * @param cipherText 要解密的密文
     * @return 解密后的明文
     */
    public static String decryptByBase64(String cipherText) {
        return new String(Base64.decode(cipherText));
    }

    private static String base64(byte[] res) {
        return Base64.encode(res);
    }

    /**
     * 使用MessageDigest进行非对称算法单向加密（无密钥）
     *
     * @param plainText 要加密的明文
     * @param algorithm 加密算法名称
     * @return 加密后的密文
     */
    private static String messageDigest(String plainText, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] resBytes = plainText.getBytes(StandardCharsets.UTF_8);
            return base64(md.digest(resBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用KeyGenerator进行非对称算法单向加密（需要密钥）
     *
     * @param plainText 要加密的明文
     * @param algorithm 加密算法名称
     * @param key 密钥
     * @return 加密后的密文
     */
    private static String keyGeneratorMac(String plainText, String algorithm, String key) {
        try {
            SecretKey sk;
            if (key == null) {
                KeyGenerator kg = KeyGenerator.getInstance(algorithm);
                sk = kg.generateKey();
            } else {
                byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
                sk = new SecretKeySpec(keyBytes, algorithm);
            }
            Mac mac = Mac.getInstance(algorithm);
            mac.init(sk);
            byte[] result = mac.doFinal(plainText.getBytes());
            return base64(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用KeyGenerator进行对称算法双向加密（需要密钥） 注意这里转化为字符串的时候是将二进制转为十六进制格式的字符串，不能直接转否则会出错
     *
     * @param text 要加密或解密的文本
     * @param algorithm 加密算法名称
     * @param key 密钥
     * @param keySize 键大小
     * @param isEncode 是否加密：true为加密，false为解密
     * @return 加密或解密后的文本
     */
    private static String keyGeneratorES(String text, String algorithm, String key, int keySize, boolean isEncode) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(algorithm);
            if (keySize == 0) {
                byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
                kg.init(new SecureRandom(keyBytes));
            } else if (key == null) {
                kg.init(keySize);
            } else {
                byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
                kg.init(keySize, new SecureRandom(keyBytes));
            }
            SecretKey sk = kg.generateKey();
            SecretKeySpec sks = new SecretKeySpec(sk.getEncoded(), algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            if (isEncode && Objects.nonNull(key)) {
                cipher.init(Cipher.ENCRYPT_MODE, sks);
                byte[] resBytes = key.getBytes(StandardCharsets.UTF_8);
                return parseByte2HexStr(cipher.doFinal(resBytes));
            } else {
                cipher.init(Cipher.DECRYPT_MODE, sks);
                return new String(cipher.doFinal(parseHexStr2Byte(text)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将二进制转换成十六进制
     */
    private static String parseByte2HexStr(byte[] buf) {
        StringBuilder sb = new StringBuilder();
        for (byte b : buf) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将十六进制转换为二进制
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (Objects.isNull(hexStr) || hexStr.isEmpty()) {
            throw new IllegalArgumentException("Parameter hexStr must not be null or empty.");
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
