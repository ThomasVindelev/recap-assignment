import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class CourseHandler {

    //autoincrement til kursus-ID på tværs af threads
    private AtomicInteger courseId = new AtomicInteger();
    private AtomicInteger examId = new AtomicInteger();

    @SuppressWarnings("unchecked")
    public Course createCourse(Scanner scanner, List studentList, List teacherList) {
        System.out.println("Angiv navnet for det nye kursus: ");
        String courseName = scanner.nextLine();

        List<Teacher> tempTeacherList = makeList(scanner, "undervisere og eksaminatorer", teacherList);
        List<Student> tempStudentList = makeList(scanner , "studerende", studentList);


        System.out.println("Tilføj én eller flere Eksamner til kurset");
        List<Exam> tempExamList = new ArrayList<>();
        scanner.nextLine();

        String choice;
        do {
            System.out.println("Navn på eksamen");
            choice = scanner.nextLine();
            tempExamList.add(new Exam(choice, examId));
            System.out.println("Eksamen tilføjet \n");

            System.out.println("Skriv 'afslut' for at færdiggøre kurset, tryk blot 'ENTER' for at tilføje flere eksamner");
            choice = scanner.nextLine();
        } while (!choice.equals("afslut"));

        System.out.println("Kursus oprettet");
        return new Course(courseId.incrementAndGet(), courseName, tempStudentList, tempTeacherList, tempExamList);
    }

    public void addExamToCourse(Scanner scanner, List<Course> coursesInSystem) {
        System.out.println("Vælg et kursus:");
        int choice = scanner.nextInt();
        Course course = coursesInSystem.get(choice-1);
        System.out.println("Skriv navnet på eksamen for at tilføje den til et kursus: ");
        scanner.nextLine();
        String examName = scanner.nextLine();

        while (!examName.equals("afslut")) {
            course.getExams().add(new Exam(examName, examId));
            examName = scanner.nextLine();
        }
    }


    private List makeList(Scanner scanner, String type, List systemList) {
        List<Object> tempList = new ArrayList<>();
        System.out.println("Angiv " + type + " til kurset");
        for (Object o : systemList) {
            System.out.println(o);
        }
        System.out.println("0 for at afslutte angivelse");

        int choice = scanner.nextInt();
        while (choice != 0) {
            tempList.add(systemList.get(choice - 1));
            System.out.println(systemList.get(choice - 1) + " has been added");
            choice = scanner.nextInt();
        }
        return tempList;
    }

}
