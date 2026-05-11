package staticExample;

public class Human {
    int age;
    String name;
    int salary;
    boolean married;
    static long population; //such properties which are not really related to the object but are common to objects of all that class
    // like population (we declare those elements as static). Static variable is common to everyone.

    public Human(int age, String name, int salary, boolean married) {
        this.age = age;
        this.name = name;
        this.salary = salary;
        this.married = married;
        Human.population += 1;// we should not use this keyword in order to use static variable , we should use class name
    }

    /*
    Static Variable:
    1. When a member is declared static it can be accessed before any object of the class is created and without referencing to
    that object.
    2. Static variables aren't dependent upon objects.They belong to the class rather than objects.

    Q Why public static void main is static?
    Ans: The main method in Java is declared static so that the Java Virtual Machine (JVM) can call it directly
         without needing to create an instance (object) of the class, as the program execution starts before any
         objects exist.

    */

}
