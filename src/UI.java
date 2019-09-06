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


        List<Teacher> tempTeacherList = makeList("undervisere og eksaminatorer", teachersInSystem);
        List<Student> tempStudentList = makeList("studerende", studentsInSystem);


        System.out.println("Tilføj én eller flere Eksamner til kurset");
        List<Exam> tempExamList = new ArrayList<>();
        scanner.nextLine();

        String choice2;
        do {
            System.out.println("Navn på eksamen");
            choice2 = scanner.nextLine();
            tempExamList.add(new Exam(1, choice2));
            System.out.println("Eksamen tilføjet \n");

            System.out.println("Skriv 'afslut' for at færdiggøre kurset, tryk blot 'ENTER' for at tilføje flere eksamner");
            choice2 = scanner.nextLine();
        } while (!choice2.equals("afslut"));

        coursesInSystem.add(new Course(2, courseName, tempStudentList, tempTeacherList, tempExamList));

        //For Testing
        /*for(Course course : coursesInSystem) {
            for(Exam teacher : course.getExams()) {
                System.out.println(teacher);
            }
        }*/
    }

    private List makeList(String type, List systemList) {
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
