package scenarioBased.part3;/*
 * Chapter 15 (STL/Collections), Exercise 3 (Starred):
 * Start with a list of int values. Use two normal (not reverse) iterators, one moving
 * forward through the list and one moving backward, in a while loop, to reverse the
 * contents of the list. You can use the swap() algorithm to save a few statements.
 * (Make sure your solution works for both even and odd numbers of items.)
 * To see how the experts do it, look at the reverse() function.
 * (In Java: ListIterator simulates C++ bidirectional iterators.)
 */

import java.util.*;

public class Ch15Ex3_ReverseList {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Reverse List Using Two Iterators ===");

        LinkedList<Integer> list = new LinkedList<>();

        System.out.println("Enter integers (non-numeric to stop):");
        int i = 0;
        while (true) {
            System.out.printf("[%d]: ", i++);
            try { list.add(Integer.parseInt(sc.nextLine().trim())); }
            catch (NumberFormatException e) { break; }
        }

        System.out.println("\nOriginal: " + list);

        // Reverse using two indices (simulates two non-reverse iterators)
        // Convert to array for easy two-pointer swap
        Integer[] arr = list.toArray(new Integer[0]);
        int left  = 0;
        int right = arr.length - 1;

        while (left < right) {
            // swap(arr[left], arr[right]) — simulates STL swap algorithm
            Integer temp = arr[left];
            arr[left]    = arr[right];
            arr[right]   = temp;
            left++;
            right--;
        }

        // Copy back to list
        list.clear();
        Collections.addAll(list, arr);

        System.out.println("Reversed: " + list);

        // Demonstrate with Collections.reverse() (the STL reverse() equivalent)
        Collections.shuffle(list); // randomize first
        System.out.println("\nShuffled: " + list);
        Collections.reverse(list);
        System.out.println("After Collections.reverse(): " + list);

        sc.close();
    }
}
