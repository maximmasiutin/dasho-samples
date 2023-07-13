package com.example.sampleapplication;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class UtilConcurrentDemo {
    private Context context;
    public UtilConcurrentDemo(Context context) {
        this.context = context;
    }
    public void runConcurrentTasks() {
        // Create a thread pool with 4 threads
        ExecutorService executor = Executors.newFixedThreadPool(4);
        // Submit some tasks to the thread pool
        for (int i = 0; i < 10; i++) {
            final int taskNumber = i + 1;
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    String message = "Task " + taskNumber + " is running on thread " + Thread.currentThread().getName();
                    showToast(message);
                }
            });
        }
        // Shutdown the thread pool
        executor.shutdown();
    }

    public void CompletableFutureTask() {
        // Create a CompletableFuture
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running task
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello from CompletableFuture!";
        });

        // Perform an action when the CompletableFuture completes
        completableFuture.thenAccept(result -> {
            // Print the result in the log
            Log.d("Result",  result);
        });

        // Handle exceptions if any
        completableFuture.exceptionally(throwable -> {
            // Handle and log the exception
            Log.e("Exception occurred: ", throwable.getMessage());
            return null;
        });
    }

    public void completionStageTask(){
        // Create a CompletionStage using CompletableFuture
        CompletionStage<String> completionStage = CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running task
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello from CompletionStage!";
        });

        // Perform an action when the CompletionStage completes
        completionStage.thenAccept(result -> {
            // Print the result in the log
            Log.d("Result: ", result);
        });

        // Handle exceptions if any
        completionStage.exceptionally(throwable -> {
            // Handle and log the exception
            Log.e( "Exception occurred: ", throwable.getMessage());
            return null;
        });
    }

    private void showToast(final String message) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

