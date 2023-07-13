package dasho.jep427;

import java.util.Objects;

public class JEP427_PatternMatching {
    sealed interface Shape permits Circle, Rectangle {
    }
    public static final class Circle implements Shape {
        public final int radius;
        public Circle(int radius) {
            this.radius = radius;
        }
        public int getRadius() {
            return radius;
        }
    }
    public record Rectangle(int width, int height) implements Shape {
    }
    public enum Days {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY}
    public record Square(int square) {
    }
    public record Number(int number) {
    }

    //Type Checking of given object and then execute the code block associated to it
    //And matching null
    public static void typeTester(Object object) {
        switch (object) {
            case String string -> System.out.println("String : " + string);
            case Days day -> System.out.println("Days : " + day);
            case Number number1 -> System.out.println("Record class: " + number1);
            case null -> System.out.println("Null");
            default -> System.out.println("Something else was given as input");
        }
    }

    //Dominance checking
    public static void dominanceChecking(Integer status) {
        switch (status) {
            case 200 -> System.out.println("The website is working fine");
            case 404 -> System.out.println("The site was not able to find");
            case 500 -> System.out.println("Their is error in server");
            case 503 -> System.out.println("Service is not able");
            case Integer codeNumber when 600 <= codeNumber -> System.out.println("Invalid");
            default -> System.out.println("valid");
        }
    }

    //Dealing with null
    //New label forms arising from null label
    public static void nullHandle(Object object) {
        //The given switch statement will not throw NullPointerException
        // because we had introduce the null case label
        switch (object) {
            case Square(var square) -> System.out.println("Square of " + square + " : " + square * square);
            case null, default -> System.out.println("The given input is null or was not present in the list");
        }
    }

    public static void exhaustiveSwitch(Shape shape) {
        switch (shape) {
            case Circle circle -> System.out.println(circle.getRadius() * 2 * 3.14);
            case Rectangle(var width, var height) -> System.out.println("Area of rectangle : " + width * height);
        }
    }

    public static void scopeVariable(Object object) {
        //checking the scope of variables in switch
        if (Objects.requireNonNull(object) instanceof String string2 && string2.length() > 10) {
            System.out.println("The given input is string ");
        }
        System.out.println("Something else");
    }

    public static void test() {
        //Type checking
        //And matching null
        // JEP427_PatternMatching typeChecking = new JEP427_PatternMatching();
        JEP427_PatternMatching.typeTester(Days.MONDAY);
        JEP427_PatternMatching.typeTester(new Number(5));
        JEP427_PatternMatching.typeTester(null);
        JEP427_PatternMatching.dominanceChecking(500);
        //Dealing with null
        //New label forms arising from null label
        nullHandle(new Square(5));
        nullHandle("");
        JEP427_PatternMatching.exhaustiveSwitch(new Rectangle(5, 7));
        //calling the scopeVariable for checking the scope of variable
        JEP427_PatternMatching.scopeVariable("hello");
    }
}