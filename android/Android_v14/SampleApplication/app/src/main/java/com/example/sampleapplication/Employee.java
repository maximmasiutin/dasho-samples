package com.example.sampleapplication;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;

public class Employee implements Serializable {
    public String name;
    public String address;
    public transient int SSN;
    public int number;

    public Employee(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public int getNumber()
    {
        return number;
    }

    @Serial
    public void writeObject(ObjectOutputStream out) throws IOException {
        out.writeUTF( name );
        out.writeInt( number );
        Log.d("Serial" , "Mailing a check to " + name + " " + address);
        //System.out.println("Mailing a check to " + name + " " + address);
    }
}
