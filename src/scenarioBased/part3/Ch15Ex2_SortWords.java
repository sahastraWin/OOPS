package scenarioBased.part3;/*
 * Chapter 15 (STL/Collections), Exercise 2 (Starred):
 * Apply the sort() algorithm to an array of words entered by the user, and display the result.
 * Use push_back() to insert the words, and the [] operator and size() to display them.
 * (In Java: ArrayList.add() = push_back(), get(i) = [], size() = size().)
 */

import java.util.*;

public class Ch15Ex2_SortWords {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Sort Words (STL sort equivalent) ===");

        // vector<string> words — using ArrayList (push_back = add)
        List<String> words = new ArrayList<>();

        System.out.println("Enter words (blank line to stop):");
        while (true) {
            System.out.printf("Word[%d]: ", words.size());
            String word = sc.nextLine().trim();
            if (word.isEmpty()) break;
            words.add(word); // push_back equivalent
        }

        if (words.isEmpty()) { System.out.println("No words entered."); return; }

        // Display before sort using [] operator (get) and size()
        System.out.print("\nBefore sort: ");
        for (int i = 0; i < words.size(); i++) // [] operator and size()
            System.out.print(words.get(i) + (i < words.size()-1 ? ", " : ""));
        System.out.println();

        // Apply sort() algorithm
        Collections.sort(words);

        // Display after sort
        System.out.print("After sort:  ");
        for (int i = 0; i < words.size(); i++)
            System.out.print(words.get(i) + (i < words.size()-1 ? ", " : ""));
        System.out.println();

        sc.close();
    }
}
