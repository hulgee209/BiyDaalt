package flashcard;

public class FlashCard {
    private String question;
    private String answer;

    public FlashCard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void invert() {
        String temp = question;
        question = answer;
        answer = temp;
    }
}
