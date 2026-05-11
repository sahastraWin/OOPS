package scenarioBased.part1;/*
/*
 * Question 38 - Chapter 10: Pointers
 * Write a program that dynamically creates a linked list of integer values.
 * The user enters integers until they type -1; the program stores them
 * in the list and then prints them in reverse order.
 */

import java.util.*;

public class Q38_LinkedListReverse {
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; this.next = null; }
    }

    static class LinkedList {
        private Node head = null;

        void addToBack(int val) {
            Node newNode = new Node(val);
            if (head == null) { head = newNode; return; }
            Node cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = newNode;
        }

        void printReverse(Node node) {
            if (node == null) return;
            printReverse(node.next);
            System.out.print(node.data + " ");
        }

        void displayReverse() {
            System.out.print("Reversed: ");
            printReverse(head);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList list = new LinkedList();
        System.out.println("Enter integers (-1 to stop):");
        while (true) {
            int val = sc.nextInt();
            if (val == -1) break;
            list.addToBack(val);
        }
        list.displayReverse();
        sc.close();
    }
}
