package flashcard;

import java.util.*;

public class FlashCardOrganizer {
    public static void sortCards(List<FlashCard> cards, String order) {
        switch (order) {
            case "worst-first":
                // TODO: Add real logic later
                break;
            case "recent-mistakes-first":
                // TODO: Add real logic later
                break;
            case "random":
            default:
                Collections.shuffle(cards);
                break;
        }
    }
}
