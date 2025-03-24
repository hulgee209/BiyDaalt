import java.util.*;
import java.io.*;

// FlashCard.java
class FlashCard {
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

// FlashCardLoader.java
class FlashCardLoader {
    public static List<FlashCard> loadCards(String filePath) {
        List<FlashCard> cards = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    cards.add(new FlashCard(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return cards;
    }
}

// FlashCardOrganizer.java
class FlashCardOrganizer {
    public static void sortCards(List<FlashCard> cards, String order) {
        switch (order) {
            case "worst-first":
                // TODO: Буруу хариулсан картуудыг эхэнд нь байрлуулах алгоритм
                break;
            case "recent-mistakes-first":
                // TODO: Сүүлийн алдаатай картуудыг эхэнд нь байрлуулах алгоритм
                break;
            case "random":
            default:
                Collections.shuffle(cards);
                break;
        }
    }
}

// FlashCardGame.java
class FlashCardGame {
    public static void playFlashCards(List<FlashCard> cards, int repetitions) {
        Scanner scanner = new Scanner(System.in);
        for (FlashCard card : cards) {
            int correctCount = 0;
            while (correctCount < repetitions) {
                System.out.println("Question: " + card.getQuestion());
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase(card.getAnswer())) {
                    correctCount++;
                    System.out.println("Correct!");
                } else {
                    System.out.println("Incorrect. Try again.");
                }
            }
        }
        scanner.close();
    }
}

// FlashCardApp.java
public class FlashCardApp {
    public static void main(String[] args) {
        if (args.length == 0 || Arrays.asList(args).contains("--help")) {
            printHelp();
            return;
        }

        String cardsFile = args[0];
        String order = "random";
        int repetitions = 1;
        boolean invertCards = false;

        for (int i = 1; i < args.length; i++) {
            switch (args[i]) {
                case "--order":
                    if (i + 1 < args.length) order = args[++i];
                    break;
                case "--repetitions":
                    if (i + 1 < args.length) repetitions = Integer.parseInt(args[++i]);
                    break;
                case "--invertCards":
                    invertCards = true;
                    break;
            }
        }

        List<FlashCard> flashCards = FlashCardLoader.loadCards(cardsFile);
        if (invertCards) {
            for (FlashCard card : flashCards) {
                card.invert();
            }
        }

        // Картуудын дарааллыг `order` тохиргооны дагуу өөрчилнө
        FlashCardOrganizer.sortCards(flashCards, order);

        // Карт тоглуулах
        FlashCardGame.playFlashCards(flashCards, repetitions);
    }

    private static void printHelp() {
        System.out.println("Usage: flashcard <cards-file> [options]");
        System.out.println("Options:");
        System.out.println("  --help                 Show this help message");
        System.out.println("  --order <order>        Sorting order: random, worst-first, recent-mistakes-first");
        System.out.println("  --repetitions <num>    Number of correct answers required per card");
        System.out.println("  --invertCards          Swap question and answer");
    }
}