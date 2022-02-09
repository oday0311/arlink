package Utils.RSA;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JceOpenSSLPKCS8DecryptorProviderBuilder;
import org.bouncycastle.operator.InputDecryptorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;
import org.bouncycastle.pkcs.PKCSException;
import org.bouncycastle.util.io.pem.PemObject;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileReader;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import java.security.spec.X509EncodedKeySpec;


/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2021/7/20 23:29
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description 使用BouncyCastle RSA 工具类
 * 参考：https://www.baeldung.com/java-read-pem-file-keys
 */
public class RSAUtilsBC {

    /**
     * 加密算法
     */
    private static final String ENCRYPT_ALGORITHM = "RSA/None/OAEPWithSHA-256AndMGF1Padding";

    /**
     * 签名算法 SHA256withRSA 算法，PSS 填充模式
     */
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA/PSS";

    /**
     * 签名填充盐值长度
     */
    private static final int SIGNATURE_SALT_LENGTH = 32;


    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 使用公钥加密
     *
     * @param publicKey 公钥
     * @param data      待加密数据明文
     * @return 密文
     * @throws NoSuchPaddingException    异常
     * @throws NoSuchAlgorithmException  异常
     * @throws NoSuchProviderException   异常
     * @throws InvalidKeyException       异常
     * @throws IllegalBlockSizeException 异常
     * @throws BadPaddingException       异常
     */
    public static byte[] encrypt(PublicKey publicKey, byte[] data)
            throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM, BouncyCastleProvider.PROVIDER_NAME);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 使用私钥解密
     *
     * @param privateKey 私钥
     * @param cipherData 待解密的密文
     * @return 明文
     * @throws NoSuchPaddingException    异常
     * @throws NoSuchAlgorithmException  异常
     * @throws NoSuchProviderException   异常
     * @throws InvalidKeyException       异常
     * @throws IllegalBlockSizeException 异常
     * @throws BadPaddingException       异常
     */
    public static byte[] decrypt(PrivateKey privateKey, byte[] cipherData)
            throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM, BouncyCastleProvider.PROVIDER_NAME);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(cipherData);
    }

    /**
     * 签名
     *
     * @param privateKey 私钥
     * @param data       数据
     * @return 数据的签名值
     * @throws NoSuchAlgorithmException           异常
     * @throws NoSuchProviderException            异常
     * @throws InvalidAlgorithmParameterException 异常
     * @throws InvalidKeyException                异常
     * @throws SignatureException                 异常
     */
    public static byte[] signature(PrivateKey privateKey, byte[] data) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM, BouncyCastleProvider.PROVIDER_NAME);
        PSSParameterSpec pssParameterSpec = new PSSParameterSpec(MGF1ParameterSpec.SHA256.getDigestAlgorithm(),
                "MGF1", MGF1ParameterSpec.SHA256, SIGNATURE_SALT_LENGTH, 1);
        signature.setParameter(pssParameterSpec);
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }

    /**
     * 验签
     *
     * @param publicKey 公钥
     * @param data      数据
     * @param sign      签名值
     * @return 验签是否成功
     * @throws NoSuchAlgorithmException           异常
     * @throws NoSuchProviderException            异常
     * @throws InvalidAlgorithmParameterException 异常
     * @throws InvalidKeyException                异常
     * @throws SignatureException                 异常
     */
    public static boolean verify(PublicKey publicKey, byte[] data, byte[] sign) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM, BouncyCastleProvider.PROVIDER_NAME);
        PSSParameterSpec pssParameterSpec = new PSSParameterSpec(MGF1ParameterSpec.SHA256.getDigestAlgorithm(),
                "MGF1", MGF1ParameterSpec.SHA256, SIGNATURE_SALT_LENGTH, 1);
        signature.setParameter(pssParameterSpec);
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(sign);
    }

    /**
     * 从文件加载口令保护的私钥
     *
     * @param filePath 私钥文件路径
     * @param password 口令
     * @return 私钥
     * @throws IOException               异常
     * @throws OperatorCreationException 异常
     * @throws PKCSException             异常
     */
    public static PrivateKey decodeEncryptedPrivateKey(String filePath, String password) throws IOException, OperatorCreationException, PKCSException {
        try (PEMParser pemParser = new PEMParser(new FileReader(filePath))) {
            Object pem = pemParser.readObject();
            if (pem instanceof PKCS8EncryptedPrivateKeyInfo) {
                JcaPEMKeyConverter converter = new JcaPEMKeyConverter()
                        .setProvider(BouncyCastleProvider.PROVIDER_NAME);
                PKCS8EncryptedPrivateKeyInfo keyInfo = (PKCS8EncryptedPrivateKeyInfo) pem;
                InputDecryptorProvider pkcs8Prov = new JceOpenSSLPKCS8DecryptorProviderBuilder()
                        .setProvider(BouncyCastleProvider.PROVIDER_NAME)
                        .build(password.toCharArray());
                return converter.getPrivateKey(keyInfo.decryptPrivateKeyInfo(pkcs8Prov));
            }
            throw new RuntimeException("invalid key file.");
        }
    }


    /**
     * 从文件加载私钥（无口令保护）
     *
     * @param filePath 私钥文件路径
     * @return 私钥
     * @throws IOException 异常
     */
    public static PrivateKey decodePrivateKey(String filePath) throws IOException {
        try (PEMParser pemParser = new PEMParser(new FileReader(filePath))) {
            Object pem = pemParser.readObject();
            if (pem instanceof PrivateKeyInfo) {
                JcaPEMKeyConverter converter = new JcaPEMKeyConverter()
                        .setProvider(BouncyCastleProvider.PROVIDER_NAME);
                PrivateKeyInfo keyInfo = (PrivateKeyInfo) pem;
                return converter.getPrivateKey(keyInfo);
            }
            throw new RuntimeException("invalid key file.");
        }
    }

    /**
     * 从文件加载公钥
     *
     * @param filePath 公钥文件路径
     * @return 私钥
     * @throws IOException 异常
     */
    public static PublicKey decodePublicKey(String filePath) throws IOException {
        try (PEMParser pemParser = new PEMParser(new FileReader(filePath))) {
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter()
                    .setProvider(BouncyCastleProvider.PROVIDER_NAME);
            SubjectPublicKeyInfo publicKeyInfo = SubjectPublicKeyInfo.getInstance(pemParser.readObject());
            return converter.getPublicKey(publicKeyInfo);
        }
    }

    /**
     * 从文件加载公钥2
     *
     * @param filePath 公钥文件路径
     * @return 公钥
     * @throws IOException              异常
     * @throws NoSuchAlgorithmException 异常
     * @throws InvalidKeySpecException  异常
     * @throws NoSuchProviderException  异常
     */
    public static PublicKey decodePublicKey2(String filePath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        try (PEMParser pemParser = new PEMParser(new FileReader(filePath))) {
            PemObject pemObject = pemParser.readPemObject();
            byte[] content = pemObject.getContent();
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(content);
            KeyFactory factory = KeyFactory.getInstance("RSA", BouncyCastleProvider.PROVIDER_NAME);
            return factory.generatePublic(pubKeySpec);
        }
    }

}

