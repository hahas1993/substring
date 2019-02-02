package pl.pmatwiejuk.substring;

import java.util.List;

public class KnuthMorrisPrattAlgorithm {

    public static boolean checkIfTextContainsAllParts(String textString, List<String> partsToFind) {
        char[] text = textString.toCharArray();
        int textLength = text.length;
        int textIndex = 0;

        for (String partToFind : partsToFind) {
            char[] toFind = partToFind.toCharArray();
            int toFindLength = toFind.length;

            int[] shifts = computeShifts(toFind);

            int toFindIndex = 0;
            while (textIndex < textLength) {
                while (toFindIndex > -1 && toFind[toFindIndex] != text[textIndex]) {
                    toFindIndex = shifts[toFindIndex];
                }
                toFindIndex++;
                textIndex++;
                if (toFindIndex >= toFindLength) { // part found
                    break;
                }
            }
            if (toFindIndex < toFindLength) { // part not found
                return false;
            }
        }
        return true;
    }

    private static int[] computeShifts(char[] toFind) {
        int[] shifts = new int[toFind.length];
        shifts[0] = -1;
        int toFindLastIndex = toFind.length - 1;

        int i = 0;
        int j = -1;
        while (i < toFindLastIndex) {
            while (j > -1 && toFind[i] != toFind[j]) {
                j = shifts[j];
            }
            i++;
            j++;
            if (toFind[i] == toFind[j]) {
                shifts[i] = shifts[j];
            } else {
                shifts[i] = j;
            }
        }
        return shifts;
    }
}
