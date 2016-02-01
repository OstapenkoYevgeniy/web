package com.john.validator;

import javax.servlet.http.HttpServletRequest;

public class IinValidator {
    /**
     * Check on correctness of the entered IIN.
     *
     * @param iin - IIN in the form of a string.
     * @param req - request for addition of attributes.
     * @return - "true" if IIN true
     */
    public static boolean isValid(String iin, HttpServletRequest req) {
        if (iin == null || iin.trim().length() == 0) {
            req.setAttribute("iinError", "error.empty.iin");
            return false;
        }

        if (!checkDigitValidation(iin) || iin.length() != 12) {
            req.setAttribute("iinError", "error.incorrect.iin");
            return false;
        } else {
            req.setAttribute("iin", iin);
            return true;
        }
    }

    private static boolean checkDigitValidation(String iin) {
        // Номер состоит из четырёх частей:
        // первые 6 разрядов — это дата рождения в формате ггммдд.
        // 7 разряд отвечает за век рождения и пол:
        // 1 - для мужчин, родившихся в 19 веке
        // 2 - для женщин, родившихся в 19 веке
        // 3 - для мужчин, родившихся в 20 веке
        // 4 - для женщин, родившихся в 20 веке
        // 5 - для мужчин, родившихся в 21 веке
        // 6 - для женщин, родившихся в 21 веке
        // 8-11 разряды — заполняет орган Юстиции. Порядковый номер.
        // 12 разряд — контрольная цифра, при расчете которой используется следующий алгоритм:
        // a12 = (a1*b1 + a2*b2 + a3*b3 + a4*b4 + a5*b5
        // + a6*b6 + a7*b7 + a8*b8 + a9*b9 + a10*b10 + a11*b11) mod 11
        // ai - значение i-го разряда;
        // bi - вес i-го разряда.
        // Если полученное число равно 10, то расчет контрольного разряда
        // производится с другой последовательностью весов: ci - вес i-го разряда.
        // Если полученное число равно 10, то данный ИИН не используется.
        try {
            int a1 = Integer.parseInt(String.valueOf(iin.charAt(0)));
            int a2 = Integer.parseInt(String.valueOf(iin.charAt(1)));
            int a3 = Integer.parseInt(String.valueOf(iin.charAt(2)));
            int a4 = Integer.parseInt(String.valueOf(iin.charAt(3)));
            int a5 = Integer.parseInt(String.valueOf(iin.charAt(4)));
            int a6 = Integer.parseInt(String.valueOf(iin.charAt(5)));
            int a7 = Integer.parseInt(String.valueOf(iin.charAt(6)));
            int a8 = Integer.parseInt(String.valueOf(iin.charAt(7)));
            int a9 = Integer.parseInt(String.valueOf(iin.charAt(8)));
            int a10 = Integer.parseInt(String.valueOf(iin.charAt(9)));
            int a11 = Integer.parseInt(String.valueOf(iin.charAt(10)));
            int a12 = Integer.parseInt(String.valueOf(iin.charAt(11)));

            int b1 = 1, c1 = 3;
            int b2 = 2, c2 = 4;
            int b3 = 3, c3 = 5;
            int b4 = 4, c4 = 6;
            int b5 = 5, c5 = 7;
            int b6 = 6, c6 = 8;
            int b7 = 7, c7 = 9;
            int b8 = 8, c8 = 10;
            int b9 = 9, c9 = 11;
            int b10 = 10, c10 = 1;
            int b11 = 11, c11 = 2;

            int checkDigit = (a1 * b1 + a2 * b2 + a3 * b3 + a4 * b4
                    + a5 * b5 + a6 * b6 + a7 * b7 + a8 * b8 + a9 * b9 + a10 * b10 + a11 * b11) % 11;

            if (checkDigit == 10) {
                checkDigit = (a1 * c1 + a2 * c2 + a3 * c3 + a4 * c4
                        + a5 * c5 + a6 * c6 + a7 * c7 + a8 * c8 + a9 * c9 + a10 * c10 + a11 * c11) % 11;
            }

            if (checkDigit == 10 || checkDigit != a12) {
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
