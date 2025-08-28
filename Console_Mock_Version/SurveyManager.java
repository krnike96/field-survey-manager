import java.util.*;

public class SurveyManager {
    private Map<String, User> users;
    private Map<String, Survey> surveys;
    private List<Response> responses;
    private Scanner scanner;

    public SurveyManager() {
        users = new HashMap<>();
        surveys = new HashMap<>();
        responses = new ArrayList<>();
        scanner = new Scanner(System.in);
        // Initialize with a default admin user
        users.put("admin", new User("admin", "Admin", "admin123"));
    }

    public void start() {
        while (true) {
            System.out.println("\n=== Field Survey Data Manager ===");
            System.out.println("1. Login");
            System.out.println("2. Register (Survey Taker)");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                login();
            } else if (choice.equals("2")) {
                registerSurveyTaker();
            } else if (choice.equals("3")) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void registerSurveyTaker() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Username already exists!");
            return;
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        users.put(username, new User(username, "SurveyTaker", password));
        System.out.println("Survey Taker registered successfully!");
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user != null && user.authenticate(password)) {
            if (user.getRole().equals("Admin")) {
                adminMenu(user);
            } else {
                surveyTakerMenu(user);
            }
        } else {
            System.out.println("Invalid username or password!");
        }
    }

    private void adminMenu(User user) {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Create Survey");
            System.out.println("2. View All Users");
            System.out.println("3. View All Responses");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                createSurvey();
            } else if (choice.equals("2")) {
                viewAllUsers();
            } else if (choice.equals("3")) {
                viewAllResponses();
            } else if (choice.equals("4")) {
                break;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void createSurvey() {
        System.out.print("Enter survey title: ");
        String title = scanner.nextLine();
        if (surveys.containsKey(title)) {
            System.out.println("Survey title already exists!");
            return;
        }
        Survey survey = new Survey(title);
        while (true) {
            System.out.print("Enter question text (or 'done' to finish): ");
            String questionText = scanner.nextLine();
            if (questionText.equalsIgnoreCase("done")) {
                break;
            }
            System.out.print("Enter question type (text/number/multiple-choice): ");
            String type = scanner.nextLine().toLowerCase();
            List<String> options = null;
            if (type.equals("multiple-choice")) {
                options = new ArrayList<>();
                System.out.println("Enter options (one per line, type 'done' to finish): ");
                while (true) {
                    String option = scanner.nextLine();
                    if (option.equalsIgnoreCase("done")) {
                        break;
                    }
                    options.add(option);
                }
            }
            survey.addQuestion(new Question(questionText, type, options));
        }
        surveys.put(title, survey);
        System.out.println("Survey created successfully!");
    }

    private void viewAllUsers() {
        System.out.println("\n=== All Users ===");
        for (User user : users.values()) {
            System.out.println("Username: " + user.getName() + ", Role: " + user.getRole());
        }
    }

    private void viewAllResponses() {
        System.out.println("\n=== All Responses ===");
        System.out.println("Total Surveys Taken: " + getSurveyCount());
        if (responses.isEmpty()) {
            System.out.println("No responses available.");
            return;
        }
        for (Response response : responses) {
            System.out.println("Survey: " + response.getSurveyTitle() + ", User: " + response.getUserName());
            Survey survey = surveys.get(response.getSurveyTitle());
            int questionIndex = 0;
            for (Question question : survey.getQuestions()) {
                String answer = response.getAnswers().get(questionIndex);
                System.out.println("Q" + (questionIndex + 1) + ": " + question.getQuestionText());
                System.out.println("A: " + (answer != null ? answer : "No answer"));
                questionIndex++;
            }
            System.out.println();
        }
    }

    private int getSurveyCount() {
        return responses.size();
    }

    private int getUserSurveyCount(User user) {
        int count = 0;
        for (Response response : responses) {
            if (response.getUserName().equals(user.getName())) {
                count++;
            }
        }
        return count;
    }

    private void viewUserResponses(User user) {
        System.out.println("\n=== Your Survey Responses ===");
        System.out.println("Total Surveys Taken by You: " + getUserSurveyCount(user));
        boolean hasResponses = false;
        for (Response response : responses) {
            if (response.getUserName().equals(user.getName())) {
                hasResponses = true;
                System.out.println("Survey: " + response.getSurveyTitle());
                Survey survey = surveys.get(response.getSurveyTitle());
                int questionIndex = 0;
                for (Question question : survey.getQuestions()) {
                    String answer = response.getAnswers().get(questionIndex);
                    System.out.println("Q" + (questionIndex + 1) + ": " + question.getQuestionText());
                    System.out.println("A: " + (answer != null ? answer : "No answer"));
                    questionIndex++;
                }
                System.out.println();
            }
        }
        if (!hasResponses) {
            System.out.println("You have not submitted any survey responses.");
        }
    }

    private void surveyTakerMenu(User user) {
        while (true) {
            System.out.println("\n=== Survey Taker Menu ===");
            System.out.println("Surveys Taken by You: " + getUserSurveyCount(user));
            System.out.println("1. Take Survey");
            System.out.println("2. View Your Responses");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                takeSurvey(user);
            } else if (choice.equals("2")) {
                viewUserResponses(user);
            } else if (choice.equals("3")) {
                break;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void takeSurvey(User user) {
        if (surveys.isEmpty()) {
            System.out.println("No surveys available!");
            return;
        }
        System.out.println("\nAvailable Surveys:");
        for (String title : surveys.keySet()) {
            System.out.println("- " + title);
        }
        System.out.print("Enter survey title: ");
        String surveyTitle = scanner.nextLine();
        Survey survey = surveys.get(surveyTitle);
        if (survey == null) {
            System.out.println("Survey not found!");
            return;
        }

        Response response = new Response(surveyTitle, user.getName());
        int questionIndex = 0;
        for (Question question : survey.getQuestions()) {
            System.out.println("\nQ" + (questionIndex + 1) + ": " + question.getQuestionText());
            if (question.getType().equals("multiple-choice")) {
                System.out.println("Options: " + String.join(", ", question.getOptions()));
            }
            System.out.print("Your answer: ");
            String answer = scanner.nextLine();
            response.addAnswer(questionIndex, answer);
            questionIndex++;
        }
        responses.add(response);
        System.out.println("Survey submitted successfully!");
    }
}