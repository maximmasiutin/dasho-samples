package dasho.samples;

import java.util.concurrent.ExecutionException;
import static dasho.jep424.JEP424_FFM.JEP424;
import dasho.jep405.JEP405_RecordPattern;
import dasho.jep425.JEP425_VirtualThread;
import dasho.jep426.JEP426_VectorApi;
import dasho.jep427.JEP427_PatternMatching;
import dasho.jep428.JEP428;

public class Applications {
    public static void main(String[] args) throws Throwable, Exception, ExecutionException, InterruptedException {

        //JEP405
        System.out.println("\nJEP405: Record_Patterns :");
        JEP405_RecordPattern pattern = new JEP405_RecordPattern();
        pattern.JEP405_Nested(new JEP405_RecordPattern.Total(new JEP405_RecordPattern.Marks(10, 5, 12),
                new JEP405_RecordPattern.Marks(2, 3, 3)));
        System.out.println("Area of a circle = " + pattern.JEP405_Switch(
                new JEP405_RecordPattern.Circle(1.25)));
        System.out.println("Area of a rectangle = " + pattern.JEP405_Switch(
                new JEP405_RecordPattern.Rectangle(24, 23)));
        System.out.println("Area of a square = " + pattern.JEP405_Switch(
                new JEP405_RecordPattern.Square(15)));
        System.out.println("\n#############################################################");

        //JEP424
        System.out.println("\nJEP424: Foreign_Functions_And_Memory_API :");
        System.out.println("Length of the given string using c lib is :" + JEP424("hello"));
        System.out.println("\n#############################################################");

        //JEP425
        System.out.println("\nJEP425: Virtual_Threads :");
        JEP425_VirtualThread function=new JEP425_VirtualThread();
        function.methodCall();
        System.out.println("\n#############################################################");

        //JEP426
        System.out.println("\nJEP426: Vector_API :");
        JEP426_VectorApi vector= new JEP426_VectorApi();
        vector.methodCall();
        System.out.println("\n#############################################################");

        //JEP427
        System.out.println("\nJEP427: Pattern_Matching_For_Switch :");
        JEP427_PatternMatching.test();
        System.out.println("\n#############################################################");

        //JEP428
        System.out.println("\nJEP428: Structured_Concurrency :");
        JEP428 obj = new JEP428();
        System.out.println(obj.handleStructure());
        System.out.println("\n#############################################################");
    }
}
