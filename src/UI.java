import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class UI {

    /*
    TO DO
    -Check for duplicates
    -Make proper id's
    -Better toString
    -Method for getting course
     */


    private Scanner scanner = new Scanner(System.in);
    private ConsoleColors cc = new ConsoleColors();
    private CourseHandler courseHandler = new CourseHandler();
    private List<Student> studentsInSystem = new ArrayList<>();
    private List<Teacher> teachersInSystem = new ArrayList<>();
    private List<Course> coursesInSystem = new ArrayList<>();
    private AtomicInteger teacherId = new AtomicInteger();
    private AtomicInteger studentId = new AtomicInteger();

    public UI() throws IOException {
        run();
    }

    private void run() throws IOException {
        System.out.println("Hej - velkommen til kursus-systemet");
        System.out.println("Vælg rolle, 1. Sekretær  2. Andet");
        int role = scanner.nextInt();

        studentInitializer(studentsInSystem);
        teacherInitializer(teachersInSystem);

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
            System.out.println(menuOptions);

            option = scanner.nextLine();
            switch (option) {
                case "1":
                    coursesInSystem.add(courseHandler.createCourse(scanner, studentsInSystem, teachersInSystem));
                    break;
                case "2":
                    for (Course course : coursesInSystem) {
                        System.out.println(course);
                    }
                    coursesInSystem.remove(scanner.nextInt());
                    break;
                case "3":
                    viewCourses();
                    break;
                case "4":
                    addPeopleToCourse("lærer", teachersInSystem);
                    break;
                case "5":
                    viewCourses();
                    courseHandler.addExamToCourse(scanner, coursesInSystem);
                    break;
                case "6":
                    viewCourses();
                    removeFromCourse("exam");
                    break;
                case "7":
                    viewCourses();
                    removeFromCourse("teacher");
                    break;
                case "8":
                    addPeopleToSystem("student");
                    break;
                case "9":
                    addPeopleToSystem("teacher");
                    break;
                case "10":
                    addPeopleToCourse("student", studentsInSystem);
                    break;
                case "11":
                    viewCourses();
                    removeFromCourse("student");
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
            PrintWriter out = new PrintWriter(new FileWriter("C:\\Users\\Goodman\\OneDrive\\#DATA\\3. Semester\\OOP\\Recap Assignment 2\\src\\"+file+".txt", true));
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

    private void addPeopleToCourse(String type, List objects) throws IOException {
        viewCourses();
        System.out.println("Vælg et kursus:");
        int choice = scanner.nextInt();
        Course course = coursesInSystem.get(choice-1);
        System.out.println("Vælg " + type + " til kursus. Tryk 0 for at afslutte: ");
        for (Object o : objects) {
            System.out.println(o);
        }
        choice = scanner.nextInt();
        while (choice != 0) {
            switch (type) {
                case "student":
                    course.getStudents().add(studentsInSystem.get(choice-1));
                    break;
                case "lærer":
                    course.getTeachers().add(teachersInSystem.get(choice-1));
                    break;
            }
            System.out.println(objects.get(choice-1) + " has been added");
            choice = scanner.nextInt();
        }
        scanner.nextLine();
    }

    private void removeFromCourse(String type) {
        System.out.println("Vælg et kursus:");
        int choice = scanner.nextInt();
        Course course = coursesInSystem.get(choice-1);
        switch (type) {
            case "exam":
                for (Exam e : course.getExams()) {
                    System.out.println(e);
                }
                System.out.println("Vælg eksamen du vil slette: ");
                choice = scanner.nextInt();
                System.out.println(course.getExams().get(choice-1).getName() + " er blevet slettet!");
                course.getExams().remove(choice-1);
                scanner.nextLine();
                break;
            case "student":
                for (Student s: course.getStudents()) {
                    System.out.println(s);
                }
                System.out.println("Vælg student du vil slette: ");
                choice = scanner.nextInt();
                System.out.println(course.getStudents().get(choice-1).getName() + " er blevet slettet!");
                course.getStudents().remove(choice-1);
                scanner.nextLine();
                break;
            case "teacher":
                for (Teacher t : course.getTeachers()) {
                    System.out.println(t);
                }
                System.out.println("Vælg lærer/eksaminator du vil slette: ");
                choice = scanner.nextInt();
                System.out.println(course.getTeachers().get(choice-1).getName() + " er blevet slettet!");
                course.getTeachers().remove(choice-1);
                scanner.nextLine();
                break;
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

    private void studentInitializer(List<Student> students) {
        for (int i = 0; i < 5; i++) {
            students.add(new Student(studentId.incrementAndGet(), "S" + (i+1), ""));
        }
    }

    private void teacherInitializer(List<Teacher> teachers) {
        for (int i = 0; i < 5; i++) {
            teachers.add(new Teacher(teacherId.incrementAndGet(), "L" + (i+1)));
        }
    }
}
