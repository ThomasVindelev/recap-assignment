import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Run {

    public Run() {
        run();
    }

    public void run() {
        System.out.println("Hej - velkommen til kursussystemet");
        Scanner scanner = new Scanner(System.in);
        String option = "";
        List<Student> students = new ArrayList<>();
        List<Teacher> teachers = new ArrayList<>();
        studentInitializer(students, scanner);
        teacherInitializer(teachers, scanner);
            while (!option.equals("exit")) {
            System.out.println("1. Opret kursus  2. Slet kursus  3. Tilknyt lærer  " +
                    "4. Tilknyt elev  5. Tilknyt eksamen  6. Slet eksamen   " +
                    "7. Afmeld studerende  8. Afmeld lærer  'exit': Afslut");
            option = scanner.nextLine();
            switch (option) {
                case "1":

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
            students.add(new Student(i, scanner.nextLine()));
        }
    }

    private void teacherInitializer(List<Teacher> teachers, Scanner scanner) {
        System.out.println("Skriv fem navne på lærere:");
        for (int i = 0; i < 5; i++) {
            teachers.add(new Teacher(i, scanner.nextLine()));
        }
    }
}
