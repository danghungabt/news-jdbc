package com.laptrinhjavaweb.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class TimestampUtils {
    public static Timestamp currentTimestampInHCM(){
        ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh"); // Chọn múi giờ của Việt Nam
        ZonedDateTime currentTimeInVietnam = ZonedDateTime.now(zoneId);
        Instant instant = currentTimeInVietnam.toInstant();
        return  Timestamp.from(instant);
    }

    public static String convertToString(Timestamp inputTimestamp){
        String outputFormat = "MMMM dd, yyyy";

        SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat);

        try {
            Date date = new Date(inputTimestamp.getTime()); // Chuyển đổi Timestamp thành Date
            return outputDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
