package scenarioBased.part1;/*
/*
 * Question 36 - Chapter 9: Inheritance
 * Design a base class Animal with member functions eat() and sleep().
 * Derive Dog, Cat, and Bird classes, each overriding a speak() method.
 * Demonstrate polymorphism in main().
 */

public class Q36_AnimalPolymorphism {
    abstract static class Animal {
        protected String name;

        Animal(String name) { this.name = name; }

        void eat()   { System.out.println(name + " is eating."); }
        void sleep() { System.out.println(name + " is sleeping."); }
        abstract void speak();
    }

    static class Dog extends Animal {
        Dog(String name) { super(name); }
        @Override public void speak() { System.out.println(name + " says: Woof!"); }
    }

    static class Cat extends Animal {
        Cat(String name) { super(name); }
        @Override public void speak() { System.out.println(name + " says: Meow!"); }
    }

    static class Bird extends Animal {
        Bird(String name) { super(name); }
        @Override public void speak() { System.out.println(name + " says: Tweet!"); }
    }

    public static void main(String[] args) {
        Animal[] animals = { new Dog("Rex"), new Cat("Whiskers"), new Bird("Tweety") };
        for (Animal a : animals) {
            a.speak();
            a.eat();
            a.sleep();
            System.out.println();
        }
    }
}
