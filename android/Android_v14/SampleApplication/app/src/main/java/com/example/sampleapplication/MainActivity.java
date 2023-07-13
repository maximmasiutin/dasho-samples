package com.example.sampleapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static boolean myBoolean = false;

    private TextView labelTextView;
    private Spinner dropdown;
    private Button submitButton;
    String SelectedOption;
    private PopupWindow dropdownPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //labelTextView = findViewById(R.id.labelTextView);
        TextView labelTextView = new TextView(this);
        labelTextView.setId( View.generateViewId());
        labelTextView.setTypeface( null, Typeface.BOLD );

        dropdown = findViewById(R.id.dropdown);
        submitButton = findViewById(R.id.submitButton);

        //the dropdown with options
        List<String> options = new ArrayList<>();
        options.add("Select");
        options.add("java.io");
        options.add("java.lang");
        options.add("java.lang.invoke");
        options.add("java.nio");
        options.add("java.nio.file");
        options.add("java.security");
        options.add("java.text");
        options.add("java.time");
        options.add("java.time.chrono");
        options.add("java.time.format");
        options.add("java.util");
        options.add("java.util.concurrent");
        options.add("java.util.concurrent.locks");
        options.add("java.util.regex");
        options.add("Java.util.stream");
        options.add("java.util.zip");
        options.add("Emulator Check");
        options.add("Root Check");
        options.add("Debug Check");
        options.add("Temper Check");
        options.add("Hook Check");



        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        dropdown.setAdapter(adapter);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click event here
                SelectedOption = dropdown.getSelectedItem().toString();

                // Do something with the input text
                switch (SelectedOption)
                {
                    case "java.io" :
                        // Write the object to a file
                        PrintStreamDemo.PrintStream(MainActivity.this);

                        Employee emp = new Employee("Alice", 1234);
                        // Serializing the object to a file
                        try {

                            FileOutputStream fileOut = new FileOutputStream(getFilesDir() + "/Serial.txt");
                            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                            objectOut.writeObject(emp);
                            objectOut.close();
                            fileOut.close();
                            Log.d("File", getFilesDir()+"/Serial.txt");
                            Toast.makeText(MainActivity.this, "Employee object serialized and saved to NioFileDemo.txt", Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            Log.d( "Serial", e.toString() );
                            e.printStackTrace();
                        }

                        new Thread(()-> {

                            // Example usage
                            InputStream inputStream = null;
                            long bytesToSkip = 100;

                            try {
                                URL url = new URL("https://www.google.com");
                                URLConnection connection = url.openConnection();
                                inputStream = connection.getInputStream();
                                SerialDemo.skipNBytesTask(inputStream, bytesToSkip);
                                Log.d("SkipNBytes: ", "Successful" + inputStream.toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                                Log.d("onClick: ", "Not done");
                            } finally {
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }).start();

                        break;

                    case "java.lang" :
                        // Call the computeAndShowClassName method from ClassValueUtils class
                        ClassValueUtils.computeAndShowClassName(MainActivity.this, String.class);
                        ClassValueUtils.computeAndShowClassName(MainActivity.this, Integer.class);
                        ClassValueUtils.StackWalkerTest(MainActivity.this, 5);
                        break;
                    case "java.lang.invoke" :
                        // Call the invokeGreet method of the InvokeMethodHandles class
                        InvokeMethodHandles.dropReturnExample(MainActivity.this);
                        break;
                    case "java.nio" :
                        NioUtils.runSampleLogic(MainActivity.this);
                        break;
                    case "java.nio.file" :
                        // Perform file operations
                        NioFileDemo fileDemo = new NioFileDemo();
                        fileDemo.performFileOperations(MainActivity.this);
                        break;
                    case "java.security" :

                        // Get the serialVersionUID values using Security class
                        Security security = new Security();
                        security.getPrivateKeySerialVersionUID(MainActivity.this);
                        security.getPublicKeySerialVersionUID(MainActivity.this);

                        break;
                    case "java.text" :
                        // Call the displayMonetarySeparators method //7.1.5.3.7
                        MonetarySeparatorUtils.displayMonetarySeparators(MainActivity.this);
                        break;
                    case "java.time" :
                        // Call the printCurrentInstant method from ClockUtils //7.1.5.3.8
                        ClockUtils.printCurrentInstant(MainActivity.this);
                        break;
                    case "java.time.chrono" :
                        // Call the ThaiBuddhistChronologyPrint method from ChronologyDemo //7.1.5.3.9
                        ChronologyDemo chronologyDemo = new ChronologyDemo( );
                        chronologyDemo.printAvailableChronologies(MainActivity.this);
                        break;
                    case "java.time.format" :

                        // Call the formatAndParseDate method 7.1.5.3.10
                        DateTimeUtils.formatAndParseDate(MainActivity.this);
                        break;
                    case "java.util" :
                        // Call the formatAndParseHex method //7.1.5.3.11
                        HexFormatUtils.formatAndParseHex(MainActivity.this);
                        break;
                    case "java.util.concurrent" :
                        // Run concurrent tasks
                        UtilConcurrentDemo concurrentDemo = new UtilConcurrentDemo(MainActivity.this);
                        concurrentDemo.runConcurrentTasks();
                        concurrentDemo.CompletableFutureTask();
                        concurrentDemo.completionStageTask();
                        break;
                    case "java.util.concurrent.locks" :
                        BlockingThread.performBlockingTask( MainActivity.this );
                        break;

                    case "java.util.regex" :
                        // Call the method from the WordMatcher class //7.1.5.3.14
                        String input = "Hello_world!";
                        WordMatcher.matchAndShowWords(MainActivity.this, input);
                        break;
                    case "Java.util.stream" :
                        // Call the filterAndShowNames method from NameFilterUtil class // 7.1.5.3.15
                        NameFilterUtil.dropWhileAndDisplayResult(MainActivity.this);
                       break;
                    case "java.util.zip" :
                        // Call the calculateAndShowChecksum() method from the ChecksumHelper class //7.1.5.3.16
                        ChecksumHelper.calculateAndShowChecksum(MainActivity.this);
                        break;
                    case "Root Check" :
                        boolean rootCheckResult =  RootCheck();
                        if(!rootCheckResult)
                        {
                            Toast.makeText( MainActivity.this, "Non Rooted device", Toast.LENGTH_LONG ).show();
                        }
                        else
                        {
                            Toast.makeText( MainActivity.this, "The App is running on Emulator or Rooted device", Toast.LENGTH_LONG ).show();
                        }
                        break;
                    case "Emulator Check" :
                        boolean emulatorCheckResult =  EmulatorCheck();
                        if(emulatorCheckResult)
                        {
                            Toast.makeText( MainActivity.this, "The App is running on Emulator", Toast.LENGTH_LONG ).show();
                        }
                        else
                        {
                            Toast.makeText( MainActivity.this, "The App is running on physical device", Toast.LENGTH_LONG ).show();
                        }
                        break;
                    case "Temper Check" :
                        boolean temperCheckResult =  TemperCheck();
                        if(!temperCheckResult)
                        {
                            Toast.makeText( MainActivity.this, "This is not Tempered", Toast.LENGTH_LONG ).show();
                        }
                        else
                        {
                            Toast.makeText( MainActivity.this, "This is Tempered", Toast.LENGTH_LONG ).show();
                        }
                        break;
                    case "Hook Check" :
                        boolean hookCheckResult =  HookCheck();
                        if(!hookCheckResult)
                        {
                            Toast.makeText( MainActivity.this, "This app is not being hooked", Toast.LENGTH_LONG ).show();
                        }
                        else
                        {
                            Toast.makeText( MainActivity.this, "This app is being hooked", Toast.LENGTH_LONG ).show();
                        }

                        break;
                    case "Debug Check" :
                        boolean debugCheckResult =  DebugCheck();
                        if(!debugCheckResult)
                        {
                            Toast.makeText( MainActivity.this, "This app is not currently being debugged", Toast.LENGTH_LONG ).show();
                        }
                        else
                        {
                            Toast.makeText( MainActivity.this, "This app is currently being debugged", Toast.LENGTH_LONG ).show();
                        }
                        break;
                    default:
                        // Handle unknown input
                        Toast.makeText(MainActivity.this,"Choose at least one class",Toast.LENGTH_LONG).show();
                        break;
                }


            }
        });
    }
    public boolean RootCheck()
    {
        return myBoolean;
    }

    public boolean DebugCheck()
    {
        return myBoolean;
    }

    public boolean EmulatorCheck()
    {
        return myBoolean;
    }

    public boolean HookCheck()
    {
        return myBoolean;
    }

    public boolean TemperCheck()
    {
        return myBoolean;
    }



    private void DebugcheckAction(boolean b) {
        myBoolean=b;
    }

    private void EmulatorcheckAction(boolean b) {
        myBoolean=b;
    }

    private void HookcheckAction(boolean b) {
        myBoolean=b;
    }

    private void RootcheckAction(boolean b) {
        myBoolean=b;
    }

    private void TampercheckAction(boolean b) {
        myBoolean=b;
    }
}