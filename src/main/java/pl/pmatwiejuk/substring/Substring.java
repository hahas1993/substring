package pl.pmatwiejuk.substring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Substring {

    private static final char ASTERISK = '*';
    private static final char BACKSLASH = '\\';
    private static final String QUIT = "quit";

    public static void main(String... args) throws IOException {
        if (args.length == 2) {
            displayResult(test(args[0], args[1]));
            System.out.println("Press enter to exit...");
            System.in.read();
            return;
        }

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("----------------------------------------------------");
            System.out.println("Program requires two String parameters.\nTEXT and a PATTERN to find in the text.");
            System.out.println("Input TEXT (or quit to exit) and then press enter:");
            String text = sc.nextLine();
            if (text.equals(QUIT)) {
                return;
            }
            System.out.println("Input PATTERN (or quit to exit) and then press enter:");
            String pattern = sc.nextLine();
            if (pattern.equals(QUIT)) {
                return;
            }
            displayResult(test(text, pattern));
        }
    }

    public static boolean test(String text, String patternToFind) {
        if (patternToFind.isEmpty()) {
            return true;
        }

        List<String> partsToFind = splitPatternByAsterisks(patternToFind);
        return KnuthMorrisPrattAlgorithm.checkIfTextContainsAllParts(text, partsToFind);
    }

    private static void displayResult(boolean result) {
        if (result) {
            System.out.println("Pattern FOUND in text.");
        } else {
            System.out.println("Pattern NOT FOUND in text.");
        }
    }

    private static List<String> splitPatternByAsterisks(String pattern) {
        List<String> patternSplitted = new ArrayList<>();
        StringBuilder currentPart = new StringBuilder();
        for (int i = 0; i < pattern.length(); i++) {
            char character = pattern.charAt(i);
            if (character == ASTERISK) {
                if (i > 0 && pattern.charAt(i - 1) == BACKSLASH) { // asterisk is standard character, backslash skipped
                    currentPart.deleteCharAt(currentPart.length() - 1); // remove backslash
                    currentPart.append(character);
                } else if (currentPart.length() > 0) { // asterisk is special character
                    patternSplitted.add(currentPart.toString());
                    currentPart = new StringBuilder();
                }
            } else {
                currentPart.append(character);
            }
        }
        if (currentPart.length() > 0) {
            patternSplitted.add(currentPart.toString());
        }

        return patternSplitted;
    }

}
