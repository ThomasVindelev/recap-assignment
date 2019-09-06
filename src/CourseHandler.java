import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CourseHandler {

    private Random rand = new Random();

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
            tempExamList.add(new Exam(rand.nextInt(), choice));
            System.out.println("Eksamen tilføjet \n");

            System.out.println("Skriv 'afslut' for at færdiggøre kurset, tryk blot 'ENTER' for at tilføje flere eksamner");
            choice = scanner.nextLine();
        } while (!choice.equals("afslut"));

        System.out.println("Kursus oprettet");
        return new Course(rand.nextInt(), courseName, tempStudentList, tempTeacherList, tempExamList);
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
