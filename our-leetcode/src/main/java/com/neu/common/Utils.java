package com.neu.common;

import java.sql.Timestamp;
import java.util.Calendar;

public class Utils {
    public static Timestamp currentTime() {
        return new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    }
}
