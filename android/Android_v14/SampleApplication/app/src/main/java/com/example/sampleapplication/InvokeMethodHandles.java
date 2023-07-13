package com.example.sampleapplication;

import android.content.Context;
import android.widget.Toast;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
public class InvokeMethodHandles {
    public  static void dropReturnExample(Context context)
    {
        try {
            // A method handle that returns the sum of two integers
            MethodHandle add = MethodHandles.lookup().findStatic(Integer.class, "sum", MethodType.methodType(int.class, int.class, int.class));
            Toast.makeText( context, String.valueOf(add.invoke(2, 3)), Toast.LENGTH_LONG ).show(); // prints 5

            // A method handle that drops the return value of add
            MethodHandle drop = MethodHandles.dropReturn(add);
            Toast.makeText( context, String.valueOf(drop.type()), Toast.LENGTH_LONG ).show();  // prints (int,int)void
            drop.invoke(2, 3);

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}


