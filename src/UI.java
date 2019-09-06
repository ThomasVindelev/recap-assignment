import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {

    private Scanner scanner = new Scanner(System.in);
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
            System.out.println("1. Opret kursus  2. Slet kursus  3. Tilknyt lærer  " +
                    "4. Tilknyt elev  5. Tilknyt eksamen  6. Slet eksamen   " +
                    "7. Afmeld studerende  8. Afmeld lærer  'exit': Afslut");
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    createCourse();
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

    public void createCourse() {
        System.out.println("Angiv navnet for det nye kursus: ");
        String courseName = scanner.nextLine();

        System.out.println("Angiv undervisere og eksaminatorer til kurset");
        for (Teacher teacher : teachersInSystem) {
            System.out.println(teacher);
        }
        System.out.println("0 for at afslutte angivelse");

        ArrayList<Teacher> tempTeacherList = new ArrayList<>();

        int choice = -1;
        while (choice != 0) {
            choice = scanner.nextInt();
            if(choice != 0) {
                tempTeacherList.add(teachersInSystem.get(choice - 1));
                System.out.println(teachersInSystem.get(choice - 1).getName() + " has been added");
            }
        }

        coursesInSystem.add(new Course(2, courseName, new ArrayList<Student>(), tempTeacherList, new ArrayList<Exam>()));

        for(Course course : coursesInSystem) {
            System.out.println(course);
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