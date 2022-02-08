package Utils;

import java.math.BigDecimal;

public class winston {
    public static BigDecimal winstonToAr(BigDecimal w){
        BigDecimal decimal = new BigDecimal("1000000000000");

        BigDecimal result = w.divide(decimal);
        return result;
    }

    public static BigDecimal ArToWinston(BigDecimal w){
        BigDecimal decimal = new BigDecimal("1000000000000");
        BigDecimal result = w.multiply(decimal);
        return result;
    }


}
