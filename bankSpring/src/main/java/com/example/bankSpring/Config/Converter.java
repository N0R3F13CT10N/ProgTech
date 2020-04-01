package com.example.bankSpring.Config;

import java.math.BigDecimal;
import java.util.Map;

public class Converter {
    private static Map<String, Double> convert = Map.of("RUB_TO_USD", 0.013,
            "RUB_TO_EUR", 0.012, "EUR_TO_RUB", 84.09,
            "EUR_TO_USD", 1.09, "USD_TO_RUB", 77.72,
            "USD_TO_EUR", 0.92, "RUB_TO_RUB", 1.0,
            "EUR_TO_EUR", 1.0, "USD_TO_USD", 1.0);


    public static BigDecimal convert(BigDecimal sum, String from, String to){
        return sum.multiply(BigDecimal.valueOf(convert.get(from + "_TO_" + to)));
    }
}
