package dasho.jep433;

public class JEP433_PatternMatching {

    //Declaring a record with name of Rectangle
    public record RectangleOne<Side1, Side2>(Side1 width, Side2 length ) { };
    sealed interface Shape permits Circle, Rectangle {}
    public static final class Circle implements Shape {
        public final int radius;
        public Circle(int radius) {
            this.radius = radius;
        }
        public int getRadius() {
            return radius;
        }
    }
    public record Rectangle(int reactangle_width, int rectangle_height) implements Shape {}
    public enum Days {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;}
    public record Square(int square) {}
    public record Number(int number1) {}
    // record declaration
    public record Record(int number) {
        public int number() {      // accessor method for i
            return number / 0;
        }
    }
    //Type Checking of given object and then execute the code block associated to it
    //And matching null

    public static void typeTester(Object obj) {
        switch (obj) {
            case String string -> System.out.println("String : " + string);
            case Days day -> System.out.println("Days : " + day.toString());
            case Number number -> System.out.println("Record class: " + number);
            case null -> System.out.println("null");
            default -> System.out.println("Something else was given as input");
        }
    }
    //Dominance checking
    public static void dominanceChecking(Integer status){
        switch(status){
            case 200 ->System.out.println("The website is working fine");
            case 404-> System.out.println("The site was not able to find");
            case 500->System.out.println("Their is error in server");
            case 503->System.out.println("Service is not able");
            case Integer codeNumber when 600 <= codeNumber -> System.out.println("Invalid");
            default->System.out.println("valid");
        }
    }
    //Dealing with null
    //New label forms arising from null label
    public static void nullHandle(Object object){
        //The given switch statement will not throw NullPointerException
        // because we had introduce the null case label
        switch (object){
            case Square(var square)     -> System.out.println("Square of "+ square + " : " + square*square);
            case null, default->System.out.println("The given input is null or was not present in the list");
        }
    }
    public static void exhaustiveSwitch(Shape shape){
        switch (shape) {
            case Circle circle -> System.out.println(circle.getRadius()*2*3.14);
            case Rectangle(var rectangle_width, var rectangle_height) -> System.out.println
                    ("Area of rectangle : " + rectangle_width * rectangle_height);
        };
    }

    //Passing rectangle record with different parameter sequence in method
    public static void inferenceType(RectangleOne<Integer, Double> rectangle) {
        switch (rectangle) {
            case RectangleOne(var length, var width)when length >= 5 ->
                    System.out.println("The area of rectangle : " + length * width);
            default -> System.out.println("The given pattern is no valid");
        }
    }

    //Creating method for matchexception error
    public static void matchException(Object object) {
        switch (object) {
            case Record error:
                try {
                    System.out.println(error.number / 0); // throw Exception
                } catch (ArithmeticException e) {
                    // Exception handler
                    System.out.println("Not define");
                }
            default:
                break;
        }
    }

    public static void test(){
        JEP433_PatternMatching typeChecking = new JEP433_PatternMatching();

        typeChecking.typeTester(JEP433_PatternMatching.Days.MONDAY);
        typeChecking.typeTester(null);
        typeChecking.dominanceChecking(500);
        nullHandle(new JEP433_PatternMatching.Square(5));
        nullHandle("");
        typeChecking.exhaustiveSwitch(new JEP433_PatternMatching.Rectangle(9,5));
        //Calling the InferenceType method and passing record as argument to it
        typeChecking.inferenceType(new JEP433_PatternMatching.RectangleOne(6, 7.6));
        //Calling the matchException method for checking error
        typeChecking.matchException(new JEP433_PatternMatching.Record(10));
    }

}
