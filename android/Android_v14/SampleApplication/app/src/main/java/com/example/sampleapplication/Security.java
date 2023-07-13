package com.example.sampleapplication;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;


import java.security.PrivateKey;
import java.security.PublicKey;


public class Security {
    public static void getPrivateKeySerialVersionUID(Context context)
    {
        try {
            // Get the serialVersionUID values using Security
            long privateKeySerialVersionUID = PrivateKey.class.getDeclaredFields()[0].getLong(null);

            // Display the serialVersionUID values
            Toast.makeText(context, "PrivateKey serialVersionUID: " + privateKeySerialVersionUID, Toast.LENGTH_LONG).show();
        } catch (IllegalAccessException e) {
            throw new RuntimeException( e );
        }
    }

    public static void getPublicKeySerialVersionUID(Context context)
    {
        try {
            long publicKeySerialVersionUID =  PublicKey.class.getDeclaredFields()[0].getLong(null);
            Toast.makeText(context, "PublicKey serialVersionUID: " + publicKeySerialVersionUID, Toast.LENGTH_LONG).show();
        } catch (IllegalAccessException e) {
            throw new RuntimeException( e );
        }
    }
}