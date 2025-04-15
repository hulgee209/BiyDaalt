package flashcard;

import java.util.*;

public class FlashCardApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Хэрвээ аргумент байхгүй бол шууд хэрэглэгчээс асууна
        if (args.length == 0 || Arrays.asList(args).contains("--help")) {
            System.out.println("📄 Flashcard application starting...");
            System.out.print("Enter cards file name (e.g. cards.txt): ");
            String cardsFile = scanner.nextLine();

            System.out.print("Order (random / worst-first / recent-mistakes-first): ");
            String order = scanner.nextLine().trim();
            if (order.isEmpty()) order = "random";

            System.out.print("Repetitions (number of correct answers needed): ");
            int repetitions = 1;
            try {
                repetitions = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Invalid number. Using 1 repetition by default.");
            }

            System.out.print("Invert cards? (yes/no): ");
            boolean invertCards = scanner.nextLine().trim().equalsIgnoreCase("yes");

            runApp(cardsFile, order, repetitions, invertCards);
        } else {
            // Аргументуудаар дамжуулсан тохиолдолд хуучин логик
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

            runApp(cardsFile, order, repetitions, invertCards);
        }

        scanner.close();
    }

    private static void runApp(String cardsFile, String order, int repetitions, boolean invertCards) {
        List<FlashCard> flashCards = FlashCardLoader.loadCards(cardsFile);
        if (invertCards) {
            for (FlashCard card : flashCards) {
                card.invert();
            }
        }
        FlashCardOrganizer.sortCards(flashCards, order);
        FlashCardGame.playFlashCards(flashCards, repetitions);
    }
}
