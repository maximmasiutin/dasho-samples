package dasho.jep432;

import java.util.ArrayList;
import java.util.List;

//nested feature of record pattern
public class jep432_RecordPattern {
    sealed interface Number permits Square, Cube {}
    public record Square(float number) implements Number {}
    public record Cube(float number1) implements Number {}
    public record TotalMarks(double physicsMarks, double mathsMarks, double chemistryMarks) {}
    public record Details(String name, int idNumber, TotalMarks marks) {}
    public record Detail(String name, int idNumber,double percentage){}
    public static double JEP432_Switch(Number object) {
        return switch (object) {
            case Square(var number2) -> number2*number2;
            case Cube(var number2) -> number2*number2*number2;
        };
    }
    public static void JEP432_Nested(Object object) {
        // record nested pattern
        if (object instanceof Details(String name2, int id_Number, TotalMarks(double subject1, double subject2, double subject3))) {
            double TotalsMark = subject1 + subject2 + subject3;
            System.out.println("Details of students ");
            System.out.println("Student ID_number : " + id_Number);
            System.out.println("Student Name : " + name2);
            System.out.println("Student Total marks obtain  : " + TotalsMark);
        }
    }
    public static void JEP432_ForStatment(){
        List<Detail> details = new ArrayList<>();
        details.add(new Detail("Tom",1,76 ));
        details.add(new Detail("Jerry",2,54 ));
        details.add(new Detail("Jack",3,89 ));
        double percentage = 84.5;
        for(Detail(String nameFirst,int id_Number,double percentage1) : details){
        if(percentage1>percentage){
            System.out.println("Name of student : " + nameFirst + " \nNumber of Student : " + percentage1);}
        }
    }
}