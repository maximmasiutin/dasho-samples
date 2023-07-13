package com.example.sampleapplication;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

public class NioFileDemo {

    public void performFileOperations(Context context)
    {
        String filePath = "NioFileDemo.txt";
        // Create a Path from a String
        java.nio.file.Path path1 = java.nio.file.Path.of(filePath);
        Toast.makeText(context, "Path from String: " + path1, Toast.LENGTH_LONG).show();

        // Create a Path from a URI
        Uri uri = Uri.parse("file:/NioFileDemo.txt");
        java.nio.file.Path path2 = java.nio.file.Path.of(uri.getPath());
        Toast.makeText(context, "Path from URI: " + path2, Toast.LENGTH_LONG).show();
    }

}
