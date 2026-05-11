package scenarioBased.part2;
/*
 * Question 1: Write a java program for a cricket player.
 * The program should accept the details from user (max 10):
 * Player code, name, runs, innings-played and number of times not out.
 * The program should contain following menu:
 * - Enter details of players.
 * - Display average runs of a single player.
 * - Display average runs of all players.
 * (Use array of objects & function overloading)
 */

import java.util.Scanner;

class CricketPlayer {
    int playerCode;
    String name;
    int runs;
    int inningsPlayed;
    int notOut;

    // Calculate average runs
    double calculateAverage() {
        int dismissals = inningsPlayed - notOut;
        if (dismissals == 0) return runs; // Not out in all innings
        return (double) runs / dismissals;
    }

    void display() {
        System.out.println("Player Code: " + playerCode);
        System.out.println("Name: " + name);
        System.out.println("Runs: " + runs);
        System.out.println("Innings Played: " + inningsPlayed);
        System.out.println("Not Outs: " + notOut);
        System.out.printf("Average: %.2f%n", calculateAverage());
        System.out.println("---------------------------");
    }
}

public class Q1_CricketPlayer {
    static CricketPlayer[] players = new CricketPlayer[10];
    static int count = 0;
    static Scanner sc = new Scanner(System.in);

    // Function overloading: display average of a single player by index
    static void displayAverage(int index) {
        if (index < 0 || index >= count) {
            System.out.println("Invalid player index.");
            return;
        }
        CricketPlayer p = players[index];
        System.out.println("Player: " + p.name + " | Average Runs: " + String.format("%.2f", p.calculateAverage()));
    }

    // Function overloading: display average of all players
    static void displayAverage() {
        if (count == 0) {
            System.out.println("No players entered.");
            return;
        }
        System.out.println("\n--- Average Runs of All Players ---");
        for (int i = 0; i < count; i++) {
            System.out.printf("%-20s -> Average: %.2f%n", players[i].name, players[i].calculateAverage());
        }
    }

    static void enterDetails() {
        if (count >= 10) {
            System.out.println("Maximum 10 players allowed.");
            return;
        }
        CricketPlayer p = new CricketPlayer();
        System.out.print("Enter Player Code: ");
        p.playerCode = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        p.name = sc.nextLine();
        System.out.print("Enter Runs: ");
        p.runs = sc.nextInt();
        System.out.print("Enter Innings Played: ");
        p.inningsPlayed = sc.nextInt();
        System.out.print("Enter Times Not Out: ");
        p.notOut = sc.nextInt();
        players[count++] = p;
        System.out.println("Player added successfully.");
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== Cricket Player Menu =====");
            System.out.println("1. Enter details of players");
            System.out.println("2. Display average runs of a single player");
            System.out.println("3. Display average runs of all players");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    enterDetails();
                    break;
                case 2:
                    if (count == 0) { System.out.println("No players entered."); break; }
                    System.out.print("Enter player index (0 to " + (count - 1) + "): ");
                    int idx = sc.nextInt();
                    displayAverage(idx); // Calls overloaded method with int param
                    break;
                case 3:
                    displayAverage(); // Calls overloaded method with no param
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }
}
