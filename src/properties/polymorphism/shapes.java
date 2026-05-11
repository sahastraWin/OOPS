package properties.polymorphism;

public class shapes {
    void area() {
        System.out.println("I am in shapes");
    }
    /*
    FINAL KEYWORD:

    final void area() {
        System.out.println("I am in shapes");
    }
    you cannot override a method that is final , a method that is declared as final can somehow accelerate the performance
    it can avoid the extra overhead which was taking during when the method wasn't declared as final, since the method is declared as final ,
    java knows that it has to jump/avoid all the final methods at the time of overriding and directly calling the non-final methods.

     final is also prevents inheritance
    */
}
