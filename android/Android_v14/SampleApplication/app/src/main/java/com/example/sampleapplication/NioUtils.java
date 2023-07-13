package com.example.sampleapplication;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class NioUtils {
    public static void runSampleLogic(Context context) {
        ByteBuffer buffer1 = ByteBuffer.allocate(10);
        buffer1.put((byte) 1);
        buffer1.put((byte) 2);
        buffer1.put((byte) 3);

        ByteBuffer buffer2 = ByteBuffer.allocate(10);
        buffer2.put((byte) 1);
        buffer2.put((byte) 2);
        buffer2.put((byte) 4);

        ByteBuffer duplicateBuffer = buffer1.duplicate();
        showToast(context, "Original buffer: " + buffer1);
        showToast(context, "Duplicate buffer: " + duplicateBuffer);

        int mismatchIndex = duplicateBuffer.mismatch(buffer2);
        showToast(context, "Mismatch index: " + mismatchIndex);

        ByteBuffer slice = buffer2.slice();
        buffer2.put( 0, (byte) 10 );
        String value = String.valueOf( slice.get(1) );
        showToast(context, "Bytebuffer Slice :" + value);

        CharBuffer buffer3 = CharBuffer.allocate( 10 );
        buffer3.put( "a" );
        buffer3.put("b");
        buffer3.put("c");

        CharBuffer buffer4 = CharBuffer.allocate( 10 );
        buffer4.put("a" );
        buffer4.put("b");
        buffer4.put("c");


        int Charmismatch = buffer4.mismatch(buffer3);
        showToast(context, "Char Mismatch index: " + Charmismatch);

        CharBuffer sliceChar = buffer3.slice(1,2);
        buffer3.put( (char) 10 );
        String out = String.valueOf( sliceChar.get() );
        Log.d("Char buffer Slice",  out);

    }

    // A helper method to show a Toast message
    private static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}




