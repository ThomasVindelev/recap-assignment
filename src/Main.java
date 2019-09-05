import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hej - velkommen til kursussystemet");
        Scanner scanner = new Scanner(System.in);
        String option = "";
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
}
