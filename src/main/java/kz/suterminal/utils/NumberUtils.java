package kz.suterminal.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtils {
    public static BigDecimal withTwoDecimalPlaces(BigDecimal value) {
        var df = new DecimalFormat("#.00");
        return new BigDecimal(df.format(value));
    }
}
