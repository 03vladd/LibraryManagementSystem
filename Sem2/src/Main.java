import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //1.
        ArrayList<Animal> animals = new ArrayList<>();
        Dog dog = new Dog("Dawg");

        //2.
        Car car = new Car();
        car.drive();

        //3.
        Student student1 = new Student("A");
        Student student2 = new Student("B");
        Student student3 = new Student("C");

        List<Student> studentList = new ArrayList<>();

        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);

        Teacher teacher = new Teacher(studentList);

        teacher.printStudents();

        //4.
        Truck truck1 = new Truck("MAN");
        truck1.drive();

        Truck truck2 = new Truck("ROMAN");
        truck2.drive();

        Bicycle bicycle = new Bicycle();

        bicycle.drive();


    }
}