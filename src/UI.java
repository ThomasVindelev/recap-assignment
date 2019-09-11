import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class UI {

    private Scanner scanner = new Scanner(System.in);
    private ConsoleColors cc = new ConsoleColors();
    private CourseHandler courseHandler = new CourseHandler();
    private List<Student> studentsInSystem = new ArrayList<>();
    private List<Teacher> teachersInSystem = new ArrayList<>();
    private List<Course> coursesInSystem = new ArrayList<>();
    private AtomicInteger teacherId = new AtomicInteger();
    private AtomicInteger studentId = new AtomicInteger();

    public UI() {
        run();
    }

    private void run() {
        System.out.println("Hej - velkommen til kursus-systemet");
        System.out.println("Vælg rolle, 1. Sekretær  2. Andet");
        int role = scanner.nextInt();
        scanner.nextLine();

        readFromFile("student", studentsInSystem);
        readFromFile("teacher", teachersInSystem);

        String option = "";
        while (!option.equals("exit")) {
            String menuOptions;
            menuOptions = "1. Opret kursus  " +
                    "2. Slet kursus  " +
                    "3. Se oprettede kurser  " +
                    "4. Tilknyt lærer til kursus " +
                    "5. Tilknyt eksamen til kursus " +
                    "6. Slet eksamen fra kursus  " +
                    "7. Afmeld lærer fra kursus  " +
                    "8. Tilføj studerende til system  " +
                    "9. Tilføj lærer til system  ";
            if(role == 1) {
                menuOptions += "10. Tilknyt studerende til kursus  " +
                        "11. Afmeld studerende fra kursus  " +
                        "12. Tilføj notat til studerende  ";
            }
            menuOptions += "'exit': Afslut";
            cc.printTxtGreen(menuOptions).print(true);

            option = scanner.nextLine();
            switch (option) {
                case "1":
                    coursesInSystem.add(courseHandler.createCourse(scanner, studentsInSystem, teachersInSystem));
                    break;
                case "2":
                    for (Course course : coursesInSystem) {
                        System.out.println(course);
                    }
                    coursesInSystem.remove(scanner.nextInt()-1);
                    scanner.nextLine();
                    break;
                case "3":
                    viewCourses();
                    break;
                case "4":
                    viewCourses();
                    courseHandler.addPeopleToCourse("lærer", teachersInSystem, scanner, coursesInSystem);
                    break;
                case "5":
                    viewCourses();
                    courseHandler.addExamToCourse(scanner, coursesInSystem);
                    break;
                case "6":
                    viewCourses();
                    courseHandler.removeFromCourse("exam", scanner, coursesInSystem);
                    break;
                case "7":
                    viewCourses();
                    courseHandler.removeFromCourse("teacher", scanner, coursesInSystem);
                    break;
                case "8":
                    viewCourses();
                    addPeopleToSystem("student");
                    break;
                case "9":
                    viewCourses();
                    addPeopleToSystem("teacher");
                    break;
                case "10":
                    viewCourses();
                    courseHandler.addPeopleToCourse("student", studentsInSystem, scanner, coursesInSystem);
                    break;
                case "11":
                    viewCourses();
                    courseHandler.removeFromCourse("student", scanner, coursesInSystem);
                    break;
                case "12":
                    addNoteToStudent();
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

    private void addPeopleToSystem(String type) {
        System.out.println("Tilføj " + type + " til systemet. Tryk 0 og 'ENTER' for at afslutte: ");
        String personName = scanner.nextLine();
        while (!personName.equals("0")) {
            switch (type){
                case "student":
                    studentsInSystem.add(new Student(studentId.incrementAndGet(), personName, ""));
                    break;
                case "teacher":
                    teachersInSystem.add(new Teacher(teacherId.incrementAndGet(),personName));
                    break;
            }
            writeToFile(personName, type);

            System.out.println("Added new " + type);
            personName = scanner.nextLine();
        }
    }



    private void writeToFile(String write, String file) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter("src\\" + file + ".txt", true));
            out.append(write + "\n");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viewCourses() {
        for (Course course : coursesInSystem) {
            System.out.println(course);
            for (int j = 0; j < course.getStudents().size(); j++) {
                System.out.println(course.getStudents().get(j));
            }
            for (int k = 0; k < course.getTeachers().size(); k++) {
                System.out.println(course.getTeachers().get(k));
            }
            for (int L = 0; L < course.getExams().size(); L++) {
                System.out.println(course.getExams().get(L));
            }
        }
    }

    private void addNoteToStudent() {
        for (Student s: studentsInSystem) {
            System.out.println(s);
        }
        System.out.println("Vælg student du vil tilføje note til: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Skriv din note: ");
        studentsInSystem.get(choice-1).setNote(scanner.nextLine());
    }

    private void readFromFile(String fileName, List list) {
        File file = new File("src\\" + fileName + ".txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while((st = br.readLine()) != null) {
                switch (fileName) {
                    case "student":
                        list.add(new Student(studentId.incrementAndGet(), st, ""));
                        break;
                    case "teacher":
                        list.add(new Teacher(teacherId.incrementAndGet(), st));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
