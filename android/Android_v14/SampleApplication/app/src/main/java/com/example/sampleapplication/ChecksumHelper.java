package com.example.sampleapplication;

import android.content.Context;
import android.widget.Toast;
import java.nio.charset.StandardCharsets;

public class ChecksumHelper {

    public static void calculateAndShowChecksum(Context context) {
        // Create a CRC32 object
        java.util.zip.CRC32C crc32c = new java.util.zip.CRC32C();

        // Update the checksum with a byte array
        byte[] data = "Hello world".getBytes(StandardCharsets.UTF_8);
        crc32c.update(data);

        // Get the checksum value
        long value = crc32c.getValue();

        // Reset the checksum to zero
        crc32c.reset();

        // Update the checksum with a portion of a byte array
        byte[] data2 = "Hello world!".getBytes(StandardCharsets.UTF_8);
        crc32c.update(data2, 0, 6); // only use the first 6 bytes

        // Get the checksum value
        long value2 = crc32c.getValue();

        // Show the checksum values in toast messages
        Toast.makeText(context, "Checksum 1: " + value, Toast.LENGTH_SHORT).show();
        Toast.makeText(context, "Checksum 2: " + value2, Toast.LENGTH_SHORT).show();
    }
}
