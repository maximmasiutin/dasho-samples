package com.example.sampleapplication;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HexFormat;
import java.util.Locale;

public class HexFormatUtils {

    public static void formatAndParseHex(Context context) {
        HexFormat hexFormat = HexFormat.of();
        byte[] bytes = {72, 12, 17};
        String hexString = hexFormat.formatHex(bytes);

        // Show the result in a Toast message
        Toast.makeText(context, hexString, Toast.LENGTH_SHORT).show();

        // Retrieve the two-letter ISO3166-1 alpha-2 country code
        String alpha2CountryCode = Locale.getDefault().getCountry();
        Log.d("Country Code (alpha2)", alpha2CountryCode);

        // Retrieve the three-letter ISO3166-1 alpha-3 country code
        String alpha3CountryCode = Locale.getDefault().getISO3Country();
        Log.d("Country Code (alpha3)", alpha3CountryCode);
    }
}