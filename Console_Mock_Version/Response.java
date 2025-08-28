import java.util.*;

public class Response {
    private String surveyTitle;
    private String userName;
    private Map<Integer, String> answers; // question index -> answer

    public Response(String surveyTitle, String userName) {
        this.surveyTitle = surveyTitle;
        this.userName = userName;
        this.answers = new HashMap<>();
    }

    public void addAnswer(int questionIndex, String answer) {
        answers.put(questionIndex, answer);
    }

    public String getSurveyTitle() {
        return surveyTitle;
    }

    public String getUserName() {
        return userName;
    }

    public Map<Integer, String> getAnswers() {
        return answers;
    }
}