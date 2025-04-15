package flashcard;

import java.io.*;
import java.util.*;

public class FlashCardLoader {
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
