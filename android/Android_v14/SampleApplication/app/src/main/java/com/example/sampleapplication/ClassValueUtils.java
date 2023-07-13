package com.example.sampleapplication;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import java.util.List;

public class ClassValueUtils {
    public static void computeAndShowClassName(Context context, Class<?> type) {
        ClassValue<String> className = new ClassValue<String>() {
            @Override
            protected String computeValue(Class<?> type) {
                return type.getName();
            }
        };

        // Get the class name using the ClassValue
        String name = className.get(type);

        // Display the output on a Toast
        Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
    }


    public static void StackWalkerTest(Context context, int frameCount) {

        List<StackWalker.StackFrame> stackFrames = StackWalker.getInstance()
                .walk(stackFrameStream -> stackFrameStream.limit(frameCount).toList());

        Log.d("Stack Trace", "Stack Trace:");

        for (int i = 0; i < stackFrames.size(); i++) {
            StackWalker.StackFrame frame = stackFrames.get(i);
            Log.d("Stack Frame " + i, "Class: " + frame.getClassName());
            Log.d("Stack Frame " + i, "Method: " + frame.getMethodName());
            Log.d("Stack Frame " + i, "File: " + frame.getFileName());
            Log.d("Stack Frame " + i, "Line: " + frame.getLineNumber());
        }

    }
}
