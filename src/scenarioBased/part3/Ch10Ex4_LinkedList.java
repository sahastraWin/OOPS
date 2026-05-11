package scenarioBased.part3;
/*
 * Chapter 10, Exercise 4 (Starred):
 * Add a destructor to the LINKLIST program. It should delete all the links when a linklist
 * object is destroyed. It can do this by following along the chain, deleting each link as
 * it goes. You can test the destructor by having it display a message each time it deletes
 * a link; it should delete the same number of links that were added to the list.
 * (A destructor is called automatically by the system for any existing objects when the program exits.)
 * In Java, we simulate this with a destroy() method (since Java has GC, not destructors).
 */

public class Ch10Ex4_LinkedList {

    static class Link {
        int data;
        Link next;

        public Link(int d) { data = d; next = null; }
    }

    static class LinkList {
        private Link first;
        private int linkCount;

        public LinkList() { first = null; linkCount = 0; }

        // Add item to front of list
        public void addItem(int d) {
            Link newLink = new Link(d);
            newLink.next = first;
            first = newLink;
            linkCount++;
        }

        public void displayList() {
            System.out.print("List: ");
            Link current = first;
            while (current != null) {
                System.out.print(current.data + " -> ");
                current = current.next;
            }
            System.out.println("null");
        }

        // Simulates C++ destructor behavior
        public void destroy() {
            System.out.println("\nDestroying list...");
            Link current = first;
            int deleted = 0;
            while (current != null) {
                Link temp = current;
                current = current.next;
                System.out.println("  Deleting link with data: " + temp.data);
                temp = null; // Help GC
                deleted++;
            }
            first = null;
            System.out.printf("Deleted %d links (added %d).%n", deleted, linkCount);
        }
    }

    public static void main(String[] args) {
        LinkList list = new LinkList();

        System.out.println("=== Linked List with Destructor ===");
        list.addItem(25);
        list.addItem(36);
        list.addItem(49);
        list.addItem(64);

        list.displayList();

        // Simulate destructor call
        list.destroy();
        list.displayList(); // Should show empty list
    }
}
