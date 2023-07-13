package com.example.sampleapplication;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import java.io.*;

public class SerialDemo {

    public static void skipNBytesTask(InputStream inputStream, long n) {
        DataInputStream dataInputStream = new DataInputStream(inputStream);

        try {
            dataInputStream.skipNBytes(n);
            // Bytes skipped successfully
            // Continue with your logic here
        } catch (EOFException e) {
            // End of stream encountered before skipping n bytes
            e.printStackTrace();
        } catch (IOException e) {
            // I/O error occurred while skipping bytes
            e.printStackTrace();
        } finally {
            // Close the input stream when done
            try {
                dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



