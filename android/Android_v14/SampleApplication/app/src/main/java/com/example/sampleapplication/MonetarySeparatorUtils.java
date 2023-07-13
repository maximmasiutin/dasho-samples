package com.example.sampleapplication;

import android.content.Context;
import android.widget.Toast;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MonetarySeparatorUtils {

    public static void displayMonetarySeparators(Context context) {
        // Create a DecimalFormatSymbols object for US locale
        DecimalFormatSymbols dfsUS = DecimalFormatSymbols.getInstance(Locale.US);
        // Get the monetary grouping separator for US locale
        char mgsUS = dfsUS.getMonetaryGroupingSeparator();
        // Display the monetary grouping separator for US locale
        Toast.makeText(context, "Monetary grouping separator for US locale: " + mgsUS, Toast.LENGTH_LONG).show();

        // Create a DecimalFormatSymbols object for France locale
        DecimalFormatSymbols dfsGR = DecimalFormatSymbols.getInstance(Locale.GERMANY);
        // Get the monetary grouping separator for France locale
        char mgsFR = dfsGR.getMonetaryGroupingSeparator();
        // Display the monetary grouping separator for France locale
        Toast.makeText(context, "Monetary grouping separator for Germany locale: " + mgsFR, Toast.LENGTH_LONG).show();
    }
}
