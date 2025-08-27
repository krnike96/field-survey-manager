import java.util.*;

public class SurveyManager {
    private Scanner scanner;
    private User user;
    private Survey survey;
    private Response response;

    public SurveyManager() {
        scanner = new Scanner(System.in);
        user = new User();
        survey = new Survey();
        response = new Response();
    }

    public void start() {
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose option: ");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                login();
            } else if (choice.equals("2")) {
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    private void login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        boolean[] isAdmin = new boolean[1]; 
        if (user.validateUser(username, password, isAdmin)) {
            if (isAdmin[0]) {
                adminMenu();
            } else {
                surveyTakerMenu(username);
            }
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private void adminMenu() {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Create Survey");
            System.out.println("2. View Surveys");
            System.out.println("3. View Responses");
            System.out.println("4. Create User");
            System.out.println("5. Logout");
            System.out.print("Choose option: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                survey.createSurvey();
            } else if (choice.equals("2")) {
                survey.viewSurveys();
            } else if (choice.equals("3")) {
                System.out.print("Enter survey name to view responses: ");
                String surveyName = scanner.nextLine();
                response.viewResponses(surveyName);
            } else if (choice.equals("4")) {
                user.createUser();
            } else if (choice.equals("5")) {
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    private void surveyTakerMenu(String username) {
        while (true) {
            System.out.println("\nSurvey Taker Menu:");
            System.out.println("1. Take Survey");
            System.out.println("2. Logout");
            System.out.print("Choose option: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                survey.viewSurveys();
                System.out.print("Enter survey name to take: ");
                String surveyName = scanner.nextLine();
                if (survey.surveyExists(surveyName)) {
                    response.addResponse(username, surveyName);
                } else {
                    System.out.println("Survey not found.");
                }
            } else if (choice.equals("2")) {
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    public static void main(String[] args) {
        SurveyManager app = new SurveyManager();
        app.start();
    }
}