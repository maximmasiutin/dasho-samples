package dasho.samples;

import dasho.jep433.JEP433_PatternMatching;
import dasho.jep429.jep429_ScopeValue;
import java.util.concurrent.ExecutionException;
import dasho.jep432.jep432_RecordPattern;
import dasho.jep434.jep434_FFM;
import dasho.jep436.jep436_VirtualThread;
import dasho.jep437.jep437_StructuredConcurrency;

public class Applications {
    public static void main(String[] args) throws Throwable, Exception, ExecutionException, InterruptedException {

        //JEP429
        System.out.println("\nJEP429: Scoped_Values :");
        //Call a class that contain scope value
        jep429_ScopeValue calculator = new jep429_ScopeValue();
        calculator.Calculator();
        System.out.println("\n#############################################################");

        //JEP432
        //calling switch Record Pattern class
        System.out.println("\nJEP432: Record_Patterns :");
        System.out.println("Square of a Number: " + jep432_RecordPattern.JEP432_Switch(new jep432_RecordPattern.Square(1F)));
        System.out.println("Cube of a Number: " + jep432_RecordPattern.JEP432_Switch(new jep432_RecordPattern.Cube(24.3443F)));
        // adding new 'for statment' feature of java 20 and calling that method
        jep432_RecordPattern.JEP432_ForStatment();
        //calling Nested Record pattern class
        jep432_RecordPattern.JEP432_Nested(new jep432_RecordPattern.Details("Jack", 1,
                new jep432_RecordPattern.TotalMarks(45, 89,65)));
        System.out.println("\n#############################################################");

        //jep433
        System.out.println("\nJEP433: Pattern Matching :");
        JEP433_PatternMatching.test();
        System.out.println("\n#############################################################");

        //JEP434
        System.out.println("\nJEP434: Foreign_Functions_And_Memory_API :");
        System.out.println("len = " + jep434_FFM.JEP434());
        System.out.println("\n#############################################################");

        //JEP436
        System.out.println("\nJEP436: Virtual_Threads :");
        jep436_VirtualThread function=new jep436_VirtualThread();
        function.methodCall();
        System.out.println("\n#############################################################");

        //JEP437
        System.out.println("\nJEP437: Structured_Concurrency :");
        jep437_StructuredConcurrency object = new jep437_StructuredConcurrency();
        object.test();
        System.out.println("\n#############################################################");
    }
}
