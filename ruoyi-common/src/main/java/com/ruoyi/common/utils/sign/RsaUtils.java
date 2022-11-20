package com.ruoyi.common.utils.sign;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA加密解密
 *
 * @author ruoyi
 **/
public class RsaUtils {
    // Rsa 私钥
    // Rsa 私钥
    public static String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALzboy4soGvFBVoa\n" +
        "+lRFEPglkLOcrN6p7TIa+HHLJVe2kHz6r0o1lGm7Y7rX5qxwdoHk1TSdbPKvQV/q\n" +
        "wyyciI4TqxyHTOwWkJeNSWmML1CXAo3JCXvBLWMRroVHe/wyLYk4AOg7lL6/6xo7\n" +
        "eyxGa2lHIZg0tdFuQz86m0mC2ZpdAgMBAAECgYBcWwhcv0QgD7RDUSgT58aZoz01\n" +
        "KKw9I1o0g431kAON9mCITctE/hi68AGgUI6ky0aY1QASf93UfDH7JwIVIeOAx9OP\n" +
        "CYeUnwlP1X6en8wjn7Rfu0RODr1hIRWBL9fDJobsBvDTOqu0GsMIklcK7myEwN3Q\n" +
        "BTR5i2S9Bc2Hu4INXQJBAOns6cB26HbW1pWqnrjA7aImp5kmKZV3GWmo1N0r55lV\n" +
        "x2j/ztMO7HpTokUQ5Fy49qaG6TWclAaO5ghM+U5BAosCQQDOrgAPyImfLtbrUMRN\n" +
        "9yhiJ9foZau4pQ8kaSJ6VCVy7J3fa5tXY7ckQ/5m1o2N9DWrbFLPO1+kSA4PKc4P\n" +
        "eHu3AkBDosmPaZIbUlDnI1cTKk1LNgv9fwhE/BOv9i3d6STks+WdWWFuqwD6+ZWQ\n" +
        "jKrKmHxQd++UHbq7DrQ6MC9hTfCzAkA3qi+EELvYLrJd0HP+l6m9vgNtqSx136bT\n" +
        "ZKmPjuhDKq1ehknhYI7r6pAzPGgFqpdl4xfujow0399BgD3qdsXDAkAm1RJiK9Uo\n" +
        "FCd+OgNlRAQp27Yzbw0nM5qZXlSE2ZF4JeXsXZz/BxiJnkTpD4Rh1sYc13Vw896v\n" +
        "IQZlylxl7wd9";

    /**
     * 私钥解密 @param text 待解密的文本 @return 解密后的文本
     */
    public static String decryptByPrivateKey(String text) throws Exception {
        return decryptByPrivateKey(privateKey, text);
    }

    /**
     * 公钥解密
     *
     * @param publicKeyString 公钥
     * @param text            待解密的信息
     * @return 解密后的文本
     */
    public static String decryptByPublicKey(String publicKeyString, String text) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyString));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(Base64.decodeBase64(text));
        return new String(result);
    }

    /**
     * 私钥加密
     *
     * @param privateKeyString 私钥
     * @param text             待加密的信息
     * @return 加密后的文本
     */
    public static String encryptByPrivateKey(String privateKeyString, String text) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyString));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(text.getBytes());
        return Base64.encodeBase64String(result);
    }

    /**
     * 私钥解密
     *
     * @param privateKeyString 私钥
     * @param text             待解密的文本
     * @return 解密后的文本
     */
    public static String decryptByPrivateKey(String privateKeyString, String text) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec5 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyString));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec5);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(Base64.decodeBase64(text));
        return new String(result);
    }

    /**
     * 公钥加密
     *
     * @param publicKeyString 公钥
     * @param text            待加密的文本
     * @return 加密后的文本
     */
    public static String encryptByPublicKey(String publicKeyString, String text) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyString));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec2);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(text.getBytes());
        return Base64.encodeBase64String(result);
    }

    /**
     * 构建RSA密钥对
     *
     * @return 生成后的公私钥信息
     */
    public static RsaKeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        String publicKeyString = Base64.encodeBase64String(rsaPublicKey.getEncoded());
        String privateKeyString = Base64.encodeBase64String(rsaPrivateKey.getEncoded());
        return new RsaKeyPair(publicKeyString, privateKeyString);
    }

    /**
     * RSA密钥对对象
     */
    public static class RsaKeyPair {
        private final String publicKey;
        private final String privateKey;

        public RsaKeyPair(String publicKey, String privateKey) {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public String getPublicKey() {
            return publicKey;
        }

        public String getPrivateKey() {
            return privateKey;
        }
    }
}
