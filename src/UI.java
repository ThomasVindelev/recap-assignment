import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {

    private Scanner scanner = new Scanner(System.in);
    private CourseHandler courseHandler = new CourseHandler();
    private List<Student> studentsInSystem = new ArrayList<>();
    private List<Teacher> teachersInSystem = new ArrayList<>();
    private List<Course> coursesInSystem = new ArrayList<>();

    public UI() {
        run();
    }

    public void run() {
        System.out.println("Hej - velkommen til kursus-systemet");

        studentInitializer(studentsInSystem, scanner);
        teacherInitializer(teachersInSystem, scanner);

        String option = "";
        while (!option.equals("exit")) {
            System.out.println("1. Opret kursus  2. Slet kursus  3. Se oprettede kurser  " +
                    "4. Tilknyt lærer  5. Tilknyt studerende til kursus  6. Tilknyt eksamen  7. Slet eksamen   " +
                    "8. Afmeld studerende  9. Afmeld lærer  10. Tilføj studerende til system  11. Tilføj lærer til system  'exit': Afslut");
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    coursesInSystem.add(courseHandler.createCourse(scanner, studentsInSystem, teachersInSystem));
                    break;
                case "2":

                    break;
                case "3":

                    break;
                case "4":

                    break;
                case "5":

                    break;
                case "6":

                    break;
                case "7":

                    break;
                case "8":

                    break;
                case "9":

                    break;
                case "10":

                    break;
                case "11":

                    break;
                case "exit":
                    System.out.println("Lukker ned...");
                    break;
                default:
                    System.out.println("Ugyldigt valg...");
                    break;

            }
        }
    }


    private void studentInitializer(List<Student> students, Scanner scanner) {
        System.out.println("Skriv fem navne på studerende:");
        for (int i = 0; i < 5; i++) {
            students.add(new Student(i+1, scanner.nextLine()));
        }
    }

    private void teacherInitializer(List<Teacher> teachers, Scanner scanner) {
        System.out.println("Skriv fem navne på lærere:");
        for (int i = 0; i < 5; i++) {
            teachers.add(new Teacher(i+1, scanner.nextLine()));
        }
    }
}
