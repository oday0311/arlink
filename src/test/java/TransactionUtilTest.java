import Types.Tag;
import Types.Transaction;
import Utils.TransactionUtils;
import Utils.base64;
import org.junit.jupiter.api.Test;

public class TransactionUtilTest {

    @Test
    public void testGetSignatureData() throws Exception{
        Transaction tx = new Transaction();
        tx.format = 2;
        tx.id = "";
        tx.target = "3vS0v6eUuu9IJohjb_NY_9KTQPPZvksEBno9rarfj5Q";
        tx.quantity = "1000000000";
        tx.data_size = "0";
        tx.data = "";
        tx.reward = "731880";
        tx.owner = 	"nQ9iy1fRM2xrgggjHhN1xZUnOkm9B4KFsJzH70v7uLMVyDqfyIJEVXeJ4Jhk_8KpjzYQ1kYfnCMjeXnhTUfY3PbeqY4PsK5nTje0uoOe1XGogeGAyKr6mVtKPhBku-aq1gz7LLRHndO2tvLRbLwX1931vNk94bSfJPYgMfU7OXxFXbTdKU38W6u9ShoaJGgUQI1GObd_sid1UVniCmu7P-99XPkixqyacsrkHzBajGz1S7jGmpQR669KWE9Z0unvH0KSHxAKoDD7Q7QZO7_4ujTBaIFwy_SJUxzVV8G33xvs7edmRdiqMdVK5W0LED9gbS4dv_aee9IxUJQqulSqZphPgShIiGNl9TcL5iUi9gc9cXR7ISyavos6VGiem_A-S-5f-_OKxoeZzvgAQda8sD6jtBTTuM5eLvgAbosbaSi7zFYCN7zeFdB72OfvCh72ZWSpBMH3dkdxsKCDmXUXvPdDLEnnRS87-MP5RV9Z6foq_YSEN5MFTMDdo4CpFGYl6mWTP6wUP8oM3Mpz3-_HotwSZEjASvWtiff2tc1fDHulVMYIutd52Fis_FKj6K1fzpiDYVA1W3cV4P28Q1-uF3CZ8nJEa5FXchB9lFrXB4HvsJVG6LPSt-y2R9parGi1_kEc6vOYIesKspgZ0hLyIKtqpTQFiPgKRlyUc-WEn5E";
        tx.last_tx = 	 "HyYuIY8U1vfB2Kyv2Y9tBo_B7CG3kZNd4uk7OKthfVR7nYmpHJN49OAS-e080ooF";
        tx.tags = new Tag[0];


        byte[] signData = TransactionUtils.GetSignatureData(tx);
        System.out.println("=========================");
        System.out.println("the signdata is " + base64.encode(signData));
    }
}
