import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.RSAKeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import org.bouncycastle.crypto.util.PrivateKeyInfoFactory;
import org.bouncycastle.crypto.util.SubjectPublicKeyInfoFactory;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Base64;

public class RSATest {
    @Test
    public void TestRSA() throws Exception{
        //生成密钥对
        RSAKeyPairGenerator rsaKeyPairGenerator = new RSAKeyPairGenerator();
        RSAKeyGenerationParameters rsaKeyGenerationParameters = new RSAKeyGenerationParameters(
                BigInteger.valueOf(65537),
                new SecureRandom(),
                2048,
                25);
        //初始化参数
        rsaKeyPairGenerator.init(rsaKeyGenerationParameters);
        AsymmetricCipherKeyPair keyPair = rsaKeyPairGenerator.generateKeyPair();
        //公钥
        AsymmetricKeyParameter publicKey = keyPair.getPublic();
        //私钥
        AsymmetricKeyParameter privateKey = keyPair.getPrivate();

        SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfoFactory.createSubjectPublicKeyInfo(publicKey);
        PrivateKeyInfo privateKeyInfo = PrivateKeyInfoFactory.createPrivateKeyInfo(privateKey);
        //变字符串
        ASN1Object asn1ObjectPublic = subjectPublicKeyInfo.toASN1Primitive();
        byte[] publicInfoByte = asn1ObjectPublic.getEncoded();
        ASN1Object asn1ObjectPrivate = privateKeyInfo.toASN1Primitive();
        byte[] privateInfoByte = asn1ObjectPrivate.getEncoded();
        //这里可以将密钥对保存到本地
        final Base64.Encoder encoder64 = Base64.getEncoder();
        System.out.println("the lenght " + publicInfoByte.length);
        System.out.println("PublicKey:\n" +  encoder64.encodeToString(publicInfoByte));
        System.out.println("the lenght " + privateInfoByte.length);
        System.out.println("PrivateKey:\n" + encoder64.encodeToString(privateInfoByte));


    }

    @Test
    public void TestRSASSA_PSS()
    {

    }
}
