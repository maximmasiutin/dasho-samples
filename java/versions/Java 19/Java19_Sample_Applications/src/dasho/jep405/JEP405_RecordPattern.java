package dasho.jep405;
public class JEP405_RecordPattern {

    sealed interface Shape permits Circle, Rectangle, Square {}
    public record Circle(double radius) implements Shape {}
    public record Rectangle(double width, double height) implements Shape {}
    public record Square(double side) implements Shape {}
    public record Marks(int student1, int student2, int student3) {}
    public record Total(Marks mark1, Marks mark2) {}

    public static double JEP405_Switch(Shape shape) {
        return switch (shape) {
            case Circle(var radius) -> Math.PI * radius * radius;
            case Rectangle(var width, var height) -> width * height;
            case Square(var side) -> side * side;
        };
    }
    public static void JEP405_Nested(Object object){
            // record nested pattern
            if (object instanceof Total(
                    Marks(var subject1, var subject2, var subject3),
                    Marks(var subject4, var subject5, var subject6)
            )) //Nested Record pattern
            {
                if ((subject1 + subject2 + subject3) > (subject4 + subject5 + subject6)) {
                    System.out.println("Student 1 got higher marks");
                } else if ((subject1 + subject2 + subject3) < (subject4 + subject5 + subject6)) {
                    System.out.println("Student 2 got higher marks");
                } else {
                    System.out.println(" Both are at same level");
                }
            } else {
                System.out.println(object);
            }
    }
}