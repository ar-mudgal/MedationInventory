package com.inventory.MedationInventory.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    public static final DateTimeFormatter DD_MM_YYYY = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static LocalDate stringToLocalDate(String date) {

        return LocalDate.parse(date, DD_MM_YYYY);
    }

    public static String loaclDateToString(LocalDate date){

        return date.toString();
    }

    public static LocalDate stringToLocalDateAnd(String date){
        return LocalDate.parse(date,DateTimeFormatter.ISO_DATE_TIME);
    }
}
