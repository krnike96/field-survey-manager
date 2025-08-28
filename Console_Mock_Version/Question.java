import java.util.*;

public class Question {
    private String questionText;
    private String type; // text, number, multiple-choice
    private List<String> options; // for multiple-choice

    public Question(String questionText, String type, List<String> options) {
        this.questionText = questionText;
        this.type = type;
        this.options = (options != null) ? new ArrayList<>(options) : null;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getType() {
        return type;
    }

    public List<String> getOptions() {
        return options;
    }
}