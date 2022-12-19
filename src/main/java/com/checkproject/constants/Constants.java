package com.checkproject.constants;

import lombok.Data;

@Data
public class Constants {

    public static final String CHECK_HEADER_PART1 = "CASH RECEIPT";
    public static final String CHECK_HEADER_PART2 = "\nDate: ";
    public static final String CHECK_HEADER_PART3 = "\nTime: ";
    public static final String CHECK_BREAK_LINE = "\n------------------------------------------------------------";
    public static final String CHECK_FOOTER_PART1 = "\nTAXABLE TOT: ";
    public static final String CHECK_FOOTER_PART2 = "\nVAT17%: ";
    public static final String CHECK_FOOTER_PART3 = "\nTOTAL: ";
    public static final String CHECK_HEADER_PART4 = String.format("\n%-10s %5s %10s %12s %20s\n", "Item", "Qty", "Price", "Discount", "Total Price");
    public static final Integer DISCOUNT_CONDITION = 5;
    public static final Float DEFAULT_DISCOUNT_RATE = 0.1f;
    public static final Float DISCOUNT_CARD_RATE = 0.05f;
}
