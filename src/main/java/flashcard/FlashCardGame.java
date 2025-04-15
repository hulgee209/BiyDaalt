package flashcard;

import java.util.*;

public class FlashCardGame {
    public static void playFlashCards(List<FlashCard> cards, int repetitions) {
        Scanner scanner = new Scanner(System.in);
        
        // Урт хугацааны хариулах процесс
        for (FlashCard card : cards) {
            int correctCount = 0;
            while (correctCount < repetitions) {
                System.out.println("Question: " + card.getQuestion());
                String answer = scanner.nextLine();

                // Хариулт зөв эсэхийг шалгах
                if (answer.trim().equalsIgnoreCase(card.getAnswer().trim())) {
                    correctCount++;
                    System.out.println("Correct!");
                } else {
                    System.out.println("Wrong! Try again.");
                }
            }
        }
        scanner.close();
    }
}
