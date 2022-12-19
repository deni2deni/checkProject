package com.checkproject.utils;

import com.checkproject.constants.Constants;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class CheckBuilderUtil {

    public String buildHeader() {
        var builder = new StringBuilder(Constants.CHECK_HEADER_PART1);
        var currentDate = LocalDateTime.now();
        var dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        var timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        builder.append(Constants.CHECK_HEADER_PART2);
        builder.append(dateFormatter.format(currentDate));
        builder.append(Constants.CHECK_HEADER_PART3);
        builder.append(timeFormatter.format(currentDate));
        builder.append(Constants.CHECK_BREAK_LINE);
        builder.append(Constants.CHECK_HEADER_PART4);
        return builder.toString();
    }

    public String buildFooter(double total) {
        var builder = new StringBuilder(Constants.CHECK_BREAK_LINE);
        builder.append(Constants.CHECK_FOOTER_PART1);
        builder.append(String.format(Locale.ENGLISH, "%.2f", total));
        builder.append(Constants.CHECK_FOOTER_PART2);
        builder.append(String.format(Locale.ENGLISH, "%.2f", total * 0.17));
        builder.append(Constants.CHECK_FOOTER_PART3);
        builder.append(String.format(Locale.ENGLISH, "%.2f", total * 1.17));
        return builder.toString();
    }
}