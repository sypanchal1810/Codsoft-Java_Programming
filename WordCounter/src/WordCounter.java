import java.io.*;
import java.util.*;

public class WordCounter {

    // Getting the text input
    private static String getInputTextFromUser() {
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter the text or provide a file path (.txt file): ");
        String input = scn.nextLine();

        if (isFileInput(input)) {
            return readTextFromFile(input);
        } else {
            return input;
        }
    }

    // Handling the file input
    private static boolean isFileInput(String input) {
        return input.endsWith(".txt");
    }

    private static String readTextFromFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return sb.toString();
    }

    // Counting the words in the text
    private static int countWords(String text) {
        String[] words = text.split("\\s+|\\p{Punct}");
        int counter = 0;
        for (String word : words) {
            if (!word.isEmpty()) {
                counter++;
            }
        }
        return counter;
    }

    // Counting the unique words in the text
    private static int countUniqueWords(String text) {
        String[] words = text.split("\\s+|\\p{Punct}");
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
        uniqueWords.removeIf(String::isEmpty);
        return uniqueWords.size();
    }

    // Counting each word frequency in the text
    private static Map<String, Integer> getWordFrequency(String text) {
        String[] words = text.split("\\s+|\\p{Punct}");
        Map<String, Integer> wordFrequency = new HashMap<>(); // word(key), occurrences(value)
        for (String word : words) {
            if (!word.isEmpty()) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }
        return wordFrequency;
    }

    public static void main(String[] args) {
        String text = getInputTextFromUser();
        int wordCount = countWords(text);
        int uniqueWordCount = countUniqueWords(text);
        Map<String, Integer> wordFrequency = getWordFrequency(text);

        System.out.println("Total word count: " + wordCount);
        System.out.println("Unique word count: " + uniqueWordCount);
        System.out.println("Word frequency:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}