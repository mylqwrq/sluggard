package ${project.basePackage}.core.util;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESUtil {

    private AESUtil() {
    }

    private static final String ALGORITHM = "AES";
    private static final String CIPHER_INSTANCE = "AES/CBC/PKCS5Padding";

    public static String encrypt(String plaintext, String key) {
        if (plaintext == null || key == null) {
            return plaintext;
        }
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(key.getBytes(StandardCharsets.UTF_8)));
            byte[] encrypted = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
            return new BASE64Encoder().encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String ciphertext, String key) {
        if (ciphertext == null || key == null) {
            return ciphertext;
        }
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(key.getBytes(StandardCharsets.UTF_8)));
            byte[] original = cipher.doFinal(new BASE64Decoder().decodeBuffer(ciphertext));
            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
