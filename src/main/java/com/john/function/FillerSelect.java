package com.john.function;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FillerSelect {
    public static List<String> getDays() {
        List<String> result = new ArrayList();
        for (int i = 1; i <= 31; i++) {
            result.add(String.valueOf(i));
        }
        return result;
    }

    public static List<String> getMonths() {
        List<String> result = new ArrayList();
        for (int i = 1; i <= 12; i++) {
            result.add(String.valueOf(i));
        }
        return result;
    }

    public static List<Integer> getYears(int from, int period) {
        List<Integer> result = new ArrayList();
        for (int i = from; i >= from - period; i--) {
            result.add(i);
        }
        return result;
    }

    public static int getCurrentYear() {
        return LocalDate.now().getYear();
    }
}
