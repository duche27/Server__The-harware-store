package com.guille.hardware.store.commons;

import java.util.Objects;

public class Utils {

    public static boolean isNullOrBlank(String string) {
        return string == null || string.trim().length() == 0;
    }

    public static <T extends Enum<?>> boolean isNullOrNotInEnum(Class<T> enumeration,
                                                                String search) {
        if (Objects.isNull(search)) return true;
        for (T each : enumeration.getEnumConstants()) {
            if (each.name().compareToIgnoreCase(search) == 0) {
                return false;
            }
        }
        return true;
    }
}

