package objectCloning;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {

        Human jeet = new Human("Sahastrajeet Hardaha", 19);
        Human twin = (Human) jeet.clone(); // objectCloning(shallowCloning)

        System.out.println(twin.name + " " + twin.age);
        System.out.println(Arrays.toString(twin.arr));

        twin.arr[0] = 100;
        System.out.println(Arrays.toString(twin.arr));//cloned array
        System.out.println(Arrays.toString(jeet.arr));

        /*
        The problem here is we are using new keyword and because of that it is taking a lot of processing time.
        OBJECT CLONING : We are creating an exact copy of an object.Clone is a method in the object class.In Java there
        is a package called java.lang package where there is an interface called CLONEABLE,we must implement that by
        the class whose clone we want to create.It will copy all the values from one object to another object.

        Throws:The throws keyword in Java is used in method declarations to specify that the method can throw one or more exceptions.
        It provides a way for methods to signal that they may encounter exceptional conditions that they are not equipped to handle.

        ADVANTAGES:
        Efficiency        :Cloning can be a faster way to create copies of objects, as it avoids the need to recreate all the data from
                           scratch.
        State Preservation:Cloning preserves the state of the original object, which is useful when you need an exact replica.
        */
    }
}
