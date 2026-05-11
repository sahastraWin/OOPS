package interfaces.extend2Interface;

public interface A {

    // static methods in interfaces should always have a body
    static void greeting()
    {
        System.out.println("Hey I am static");
    }
    default void fun() {
        System.out.println("I am having fun in ");
    }
    //The primary motivation of using this default method was the means by which interfaces could be expanded without breaking the existing code.
}
