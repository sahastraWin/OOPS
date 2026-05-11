package staticExample;

public class Main1 {
    public static void main(String[] args) {
        Human jeet = new Human(19, "jeet", 120000, false);
        Human tejam = new Human(10, "tejam", 2022000, true);
        System.out.println(Human.population);//will print 1  if population is not declared static
        System.out.println(Human.population);//will print 1 if population is not declared static
        //greeting(); we cannot use a non-static method inside a static one. A static method can only access static data
        //helloJeet();
    }

    static void helloJeet() {
        //methods which are non-static belongs to object and static variables or methods do not depend on objects
        System.out.println("Namaste jeet");
        //greeting(); will give error because you will need instances the function you are using depend on instances
        //you cannot use a non-static method /member without explicitly mentioning an object reference to it.

        //in order to use non-static methods inside a static context you have tp create an instance
        //main obj = new main();
        //obj.greeting();
    }

    void greeting() {//non-static methods belongs to instances
        //helloJeet(); non-static method can have static methods
        System.out.println("Hello");
    }
}
