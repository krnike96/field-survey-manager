import java.util.*;

public class Response {
    private String[] responses = new String[100];
    private int responseCount = 0;
    private Scanner scanner;

    public Response() {
        scanner = new Scanner(System.in);
    }

    public void addResponse(String username, String surveyName) {
        if (responseCount >= responses.length) {
            System.out.println("Response limit reached.");
            return;
        }
        System.out.print("Enter your response: ");
        String answer = scanner.nextLine();
        responses[responseCount++] = surveyName + ":" + username + ":" + answer;
        System.out.println("Response submitted.");
    }

    public void viewResponses(String surveyName) {
        System.out.println("\nResponses for " + surveyName + ":");
        for (int i = 0; i < responseCount; i++) {
            if (responses[i] != null && responses[i].startsWith(surveyName + ":")) {
                String[] parts = responses[i].split(":");
                System.out.println("User: " + parts[1] + ", Answer: " + parts[2]);
            }
        }
    }
}