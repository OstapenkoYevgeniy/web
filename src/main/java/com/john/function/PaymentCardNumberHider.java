package com.john.function;

public class PaymentCardNumberHider {
    public static String hide(String number) {
        String result = number.substring(0, 3);
        result += " **** **** ";
        return result + number.substring(12, 15);
    }
}
