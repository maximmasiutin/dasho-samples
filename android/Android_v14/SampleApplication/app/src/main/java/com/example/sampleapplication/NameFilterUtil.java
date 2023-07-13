package com.example.sampleapplication;import android.content.Context;
import android.widget.Toast;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NameFilterUtil {

    public static void dropWhileAndDisplayResult(Context context) {
        Stream<Integer> stream = Stream.of(4, 4, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> list = stream.dropWhile(number -> (number % 4 == 0))
                .collect(Collectors.toList());

        // Convert the list to a string representation
        String result = list.toString();

        // Show the result in a Toast message
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
    }
}