package com.example.sampleapplication;

import android.content.Context;
import android.widget.Toast;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.util.Locale;
public class ChronologyDemo {
    private  Context context;

    public static void printAvailableChronologies(Context context) {
        // Enumerate the list of available chronologies and print today's date for each
        for (Chronology chrono : Chronology.getAvailableChronologies()) {
            ChronoLocalDate date = chrono.dateNow();
            String output = String.format(Locale.getDefault(), "%20s: %s", chrono.getId(), date);
            Toast.makeText(context, "Chronology: " + output, Toast.LENGTH_SHORT).show();
        }
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}





