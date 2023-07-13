package com.example.sampleapplication;
import android.content.Context;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.regex.MatchResult;
public class WordMatcher {
    public static void matchAndShowWords(Context context, String input) {
        // Create a pattern for words
        Pattern pattern = Pattern.compile("\\w+");

        // Create a matcher for the input string
        Matcher matcher = pattern.matcher(input);

        // Get a stream of match results
        Stream<MatchResult> stream = matcher.results();

        // Iterate over the match results and display each matched word in a Toast
        stream.forEach(matchResult -> {
            String word = matchResult.group().toUpperCase();
            Toast.makeText(context, word, Toast.LENGTH_LONG).show();
        });

        Predicate<String> matchPredicate = Patterns.EMAIL_ADDRESS.asMatchPredicate();

        String matchPredicateExample = String.valueOf(matchPredicate.test("example@example.com"));
        String matchPredicateExample2 = String.valueOf(matchPredicate.test("invalid-email"));
        String matchPredicateExample3 = String.valueOf(matchPredicate.test("another@example.com"));

        // Test the predicate with input strings
        Log.d("matchPredicate", matchPredicateExample);
        Log.d("matchPredicate", matchPredicateExample2);
        Log.d("matchPredicate", matchPredicateExample3);

    }
}
