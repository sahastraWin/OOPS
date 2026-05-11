package scenarioBased.part1;/*
/*
 * Question 66 - Classic Scenario: LINKED LIST
 * Create a class LinkedList of integers.
 * Include functions to:
 *   - addFront(): add at front
 *   - addBack(): add at back
 *   - delete(): delete a node by value
 *   - search(): search for a value
 *   - display(): display all values
 */

public class Q66_LinkedList {
    static class Node {
        int data; Node next;
        Node(int data) { this.data = data; }
    }

    static class LinkedList {
        private Node head = null;

        void addFront(int val) {
            Node n = new Node(val); n.next = head; head = n;
        }

        void addBack(int val) {
            Node n = new Node(val);
            if (head == null) { head = n; return; }
            Node cur = head; while (cur.next != null) cur = cur.next; cur.next = n;
        }

        boolean delete(int val) {
            if (head == null) return false;
            if (head.data == val) { head = head.next; return true; }
            Node prev = head;
            while (prev.next != null) {
                if (prev.next.data == val) { prev.next = prev.next.next; return true; }
                prev = prev.next;
            }
            return false;
        }

        boolean search(int val) {
            Node cur = head;
            while (cur != null) { if (cur.data == val) return true; cur = cur.next; }
            return false;
        }

        void display() {
            System.out.print("List: ");
            Node cur = head;
            while (cur != null) { System.out.print(cur.data + (cur.next != null ? " -> " : "")); cur = cur.next; }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addBack(10); list.addBack(20); list.addBack(30);
        list.addFront(5); list.addFront(1);
        list.display();

        System.out.println("Search 20: " + list.search(20));
        System.out.println("Search 99: " + list.search(99));

        list.delete(20); list.display();
        list.delete(1);  list.display();
        list.delete(30); list.display();
    }
}
