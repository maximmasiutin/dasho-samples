package com.example.sampleapplication;

import android.content.Context;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class PrintStreamDemo {
    public static void PrintStream(Context context)
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out, true);
        byte[] buf = new byte[10];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte)i;
        }
        ps.writeBytes(buf); // writes 10 bytes to the output stream
        // Convert the captured output to a string
        String output = Arrays.toString(buf);
        Toast.makeText(context, output, Toast.LENGTH_LONG).show();

    }
}
