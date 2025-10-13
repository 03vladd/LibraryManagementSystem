import java.util.List;

class Teacher {
    private List<Student> students;

    public Teacher(List<Student> students) {
        this.students = students;
    }

    public void printStudents() {
        for (Student s : students)
            System.out.println("Schüler: " + s.getName());
    }
}