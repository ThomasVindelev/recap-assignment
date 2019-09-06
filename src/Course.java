import java.util.List;

public class Course {

    private int id;
    private String courseName;
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Exam> exams;

    public Course(int id, String courseName, List<Student> students, List<Teacher> teachers, List<Exam> exams) {
        this.id = id;
        this.courseName = courseName;
        this.students = students;
        this.teachers = teachers;
        this.exams = exams;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public String toString() {
        return "ID: " + id + "  Kursus-Navn: " + courseName;
    }
}