import Utils.winston;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class winstonTest {
    @Test
    public void TestWinstonToAr()
    {
        BigDecimal winstonCount = new BigDecimal("1000000000000");
        BigDecimal ar = winston.winstonToAr(winstonCount);
        System.out.println("ar count is " + ar);
        assert ar.intValue() == 1;
    }


    @Test
    public void TestArToWinston()
    {
        BigDecimal arCount = new BigDecimal("1.000001000000");

        BigDecimal winstonCount = new BigDecimal("0");

        winstonCount = winston.ArToWinston(arCount).stripTrailingZeros();
        System.out.println("winston count is " + winstonCount);


        assert winstonCount.compareTo(new BigDecimal("1000001000000"))==0;
    }


}
