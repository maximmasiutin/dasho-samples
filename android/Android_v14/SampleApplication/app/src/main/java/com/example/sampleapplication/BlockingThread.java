package com.example.sampleapplication;

import android.content.Context;
import android.widget.Toast;
import java.util.concurrent.locks.LockSupport;
public class BlockingThread {
    public static void performBlockingTask(Context context) {
        // Create a blocker object
        Object blocker = new Object();

        // Set the blocker object as the current blocker for the thread
        LockSupport.setCurrentBlocker(blocker);
        // Perform some work that may block the thread
        try {
            // Simulate a long-running operation
            Toast.makeText( context, "Thread sleep", Toast.LENGTH_LONG ).show();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Clear the current blocker for the thread
        LockSupport.setCurrentBlocker(null);

        // Continue with other tasks
        Toast.makeText( context, "Thread resumed", Toast.LENGTH_LONG ).show();
    }
}


