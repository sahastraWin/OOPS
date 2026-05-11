package scenarioBased.part3;
/*
 * Chapter 5, Exercise 1
 * Write a function called circarea() that finds the area of a circle.
 * It should take an argument of type float and return an argument of the same type.
 * Write a main() function that gets a radius value from the user, calls circarea(),
 * and displays the result.
 */

import java.util.Scanner;

public class Ch5_Ex1_CircleArea {

    static float circarea(float radius) {
        return (float) (Math.PI * radius * radius);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter radius: ");
        float r = scanner.nextFloat();
        System.out.printf("Area of circle = %.4f%n", circarea(r));
        scanner.close();
    }
}
