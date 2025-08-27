import java.util.*;

public class Survey {
    private String[] surveys = new String[10]; 
    private int surveyCount = 0;
    private Scanner scanner;

    public Survey() {
        scanner = new Scanner(System.in);
    }

    public void createSurvey() {
        if (surveyCount >= surveys.length) {
            System.out.println("Survey limit reached.");
            return;
        }
        System.out.print("Enter survey name: ");
        String surveyName = scanner.nextLine();
        surveys[surveyCount++] = surveyName;
        System.out.println("Survey created.");
    }

    public void viewSurveys() {
        System.out.println("\nSurveys:");
        for (int i = 0; i < surveyCount; i++) {
            if (surveys[i] != null) {
                System.out.println("- " + surveys[i]);
            }
        }
    }

    public boolean surveyExists(String surveyName) {
        for (int i = 0; i < surveyCount; i++) {
            if (surveys[i] != null && surveys[i].equals(surveyName)) {
                return true;
            }
        }
        return false;
    }
}