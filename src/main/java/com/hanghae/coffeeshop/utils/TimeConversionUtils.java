package com.hanghae.coffeeshop.utils;

import com.hanghae.coffeeshop.entity.CustomerEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class TimeConversionUtils {
    private ZoneId zoneId = ZoneId.of("Asia/Seoul");
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Timestamp getCurrentKoreanTimestamp() {
        ZonedDateTime currentTime = Instant.now().atZone(zoneId);
        return Timestamp.valueOf(currentTime.toLocalDateTime());
    }

    public String timestampToString(Timestamp tempTimestamp) {
        ZonedDateTime koreanTime = tempTimestamp.toLocalDateTime().atZone(zoneId);
        return koreanTime.format(formatter);
    }

    public Timestamp stringToTimestamp(String tempStr) {
        try {
            ZonedDateTime zonedDateTime = LocalDateTime.parse(tempStr, formatter).atZone(zoneId);
            return Timestamp.valueOf(zonedDateTime.toLocalDateTime());
        } catch (DateTimeParseException ex) {
            System.out.println("Error converting to Timestamp" + ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            System.out.println("Global exception caught " + ex.getMessage());
            throw ex;
        }
    }

    public Timestamp getOneYearAgo() {
        Timestamp nowKorea = getCurrentKoreanTimestamp();

        return Timestamp.valueOf(nowKorea.toLocalDateTime().minusYears(1));
    }
}
