package Repository;

import java.util.ArrayList;
import java.util.List;

public class UsefullStuf {
    public static String firstUpperLower(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public void sandbox() {
        for (int j = 0; j < 1_000_000; j++) {
            if (j % 1_000 == 0)
                System.out.println(j);
            String testString = "Test: String";
            String splitter = ": ";
            String[] javaSplit = testString.split(splitter);

            String[] personalSplit = personalSplit(testString, splitter);

            if (personalSplit.length != javaSplit.length) {
                System.out.println("PROBLEM");
            }
        }
    }

    public static String[] personalSplit(String input, String splitter) {
        char[] letters = input.toCharArray();
        char[] splitLetters = splitter.toCharArray();
        List<String> list = new ArrayList<>();
        int startChar = 0;
        for (int i = 0; i < letters.length - splitLetters.length; i++) {
            boolean splitting = true;
            for (int s = 0; s < splitLetters.length; s++) {
                if (letters[i + s] != splitLetters[s]) {
                    splitting = false;
                    break;
                }
            }
            if (splitting) {
                char[] word = new char[i - startChar];
                for (int c = startChar; c < i; c++) {
                    word[c - startChar] = letters[c];
                }
                startChar = i + splitLetters.length;
                list.add(new String(word));
            }
        }
        char[] word = new char[letters.length - startChar];
        for (int c = startChar; c < letters.length; c++) {
            word[c - startChar] = letters[c];
        }
        list.add(new String(word));
        return list.toArray(new String[0]);

    }
}
