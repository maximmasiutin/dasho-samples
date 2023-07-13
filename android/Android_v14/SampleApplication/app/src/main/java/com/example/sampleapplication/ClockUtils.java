package com.example.sampleapplication;
import android.content.Context;
import android.widget.Toast;

import java.time.Clock;
import java.time.Instant;

public class ClockUtils {
    public static void printCurrentInstant(Context context) {
        // Get the system clock
        Clock clock = Clock.systemUTC();
        // Get the current instant
        Instant instant = clock.instant();
        // Convert instant to string
        String instantString = instant.toString();
        // Display the instant using a Toast
        Toast.makeText(context,"Clock.instant: " + instantString, Toast.LENGTH_LONG).show();
    }
}
