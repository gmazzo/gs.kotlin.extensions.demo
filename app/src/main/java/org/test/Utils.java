package org.test;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class Utils {
    private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

    public static String toDateString(@NonNull Date date) {
        return dateFormat.format(date);
    }

    public static Date asDate(@NonNull String date) {
        try {
            return dateFormat.parse(date);

        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private Utils() {
    }

}
