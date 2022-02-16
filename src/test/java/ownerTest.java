import org.junit.jupiter.api.Test;

import static Utils.owner.OwnerToAddress;

public class ownerTest {

    @Test
    public void TestOwnerToAddress() {
        String str = OwnerToAddress("oA4bDVEjBSVf324cQPfyJcE-90rwPo1xOvrvc7g2-Lminag5lT0JZAVfcSjg1vgeoivSu2I0yO-jznomZ1m4H2CUJGY8Hc3wEsO3SUqxPIEaOKuFvezeAZpRuh__SlzYGfvhwLfoGf7KJ6UlvcuNm49xyIfsRGTc52u-fTDevqvBtz5YtYsyk6LcMoCMDoPzE4ldTBZ8V3ucaFXz-kbwkN1aU2Ph8MYfKIySOtjeVsCxU5BBRn39cHm4dVbDqEZu8QT-l26d8QjITl91tpTatzGivvFioR5_dJEtlo1xkNsOYIsPXT6WrwgPkRYflqhJ8GW9wqp7QCoekYIxr2nQXxOm0lLsPoA_gx4ZCDGsfUtumWdk2UV-cKsvQo-iuxPPVWX-7bWTP9aMKDTyyywW9Ho6r99ggeSEpNYYGqYBmLb-v2S0L-8EdvjAQ2M6yCw0qpNN6XhFvNVcPZXATrhhDIijp-KXDHx2zy2IDubjShCmZOSRrJy5OV6EfJOpPEgBXO-CdKtVk48uh8htb5SMEZ48hDyeU-4Htjaryz-N_M1n9Rv9ffqVQf-pIrP_cpw0DzxcrvFsFRYnAkFLnXlY9FX5mha2FW5veeL0ZMCN7ETEVovCrO1Q8_sV9v2rs6S8NuMjA4RW93nKJjqvAJFkt7rMpv4a_tuftZvuX8OzxOc");
        System.out.println("str is " + str);
        String address = "dQzTM9hXV5MD1fRniOKI3MvPF_-8b2XDLmpfcMN9hi8";
        assert address.equals(str);


    }
    @Test
    public void TestOwnerAddress() {
        String str =OwnerToAddress("AJ0PYstX0TNsa4IIIx4TdcWVJzpJvQeChbCcx-9L-7izFcg6n8iCRFV3ieCYZP_CqY82ENZGH5wjI3l54U1H2Nz23qmOD7CuZ043tLqDntVxqIHhgMiq-plbSj4QZLvmqtYM-yy0R53Ttrby0Wy8F9fd9bzZPeG0nyT2IDH1Ozl8RV203SlN_FurvUoaGiRoFECNRjm3f7IndVFZ4gpruz_vfVz5IsasmnLK5B8wWoxs9Uu4xpqUEeuvSlhPWdLp7x9Ckh8QCqAw-0O0GTu_-Lo0wWiBcMv0iVMc1VfBt98b7O3nZkXYqjHVSuVtCxA_YG0uHb_2nnvSMVCUKrpUqmaYT4EoSIhjZfU3C-YlIvYHPXF0eyEsmr6LOlRonpvwPkvuX_vzisaHmc74AEHWvLA-o7QU07jOXi74AG6LG2kou8xWAje83hXQe9jn7woe9mVkqQTB93ZHcbCgg5l1F7z3QyxJ50UvO_jD-UVfWen6Kv2EhDeTBUzA3aOAqRRmJeplkz-sFD_KDNzKc9_vx6LcEmRIwEr1rYn39rXNXwx7pVTGCLrXedhYrPxSo-itX86Yg2FQNVt3FeD9vENfrhdwmfJyRGuRV3IQfZRa1weB77CVRuiz0rfstkfaWqxotf5BHOrzmCHrCrKYGdIS8iCraqU0BYj4CkZclHPlhJ-R");
        System.out.println("str is " + str);

    }

}
