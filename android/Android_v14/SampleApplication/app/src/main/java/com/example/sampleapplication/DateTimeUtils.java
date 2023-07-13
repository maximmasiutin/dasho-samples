package com.example.sampleapplication;

import android.content.Context;
import android.widget.Toast;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class DateTimeUtils {

    public static void formatAndParseDate(Context context) {
        // Get the current date
        LocalDate date = LocalDate.now();
        // Create a formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Format the date
        String text = formatter.format(date);
        // Print the formatted date
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
        // Parse the text
        LocalDate parsedDate = LocalDate.parse(text, formatter);
        // Print the parsed date
        Toast.makeText(context, parsedDate.toString(), Toast.LENGTH_LONG).show();

        DateTimeFormatter formatterBuilder = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")  // Append year, month, and day
                .appendLiteral(" ")
                .appendValue( ChronoField.HOUR_OF_DAY, 2)  // Append hour (24-hour format)
                .appendLiteral(":")
                .appendValue(ChronoField.MINUTE_OF_HOUR, 2)  // Append minute
                .appendLiteral(":")
                .appendValue(ChronoField.SECOND_OF_MINUTE, 2)  // Append second
                .toFormatter();

        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Format the LocalDateTime object using the formatter
        String formattedDateTime = now.format(formatterBuilder);

        // Print the formatted date and time
        Toast.makeText(context, formattedDateTime, Toast.LENGTH_LONG).show();
    }
}
