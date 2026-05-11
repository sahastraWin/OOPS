package properties.polymorphism;

public class Main3 {
    public static void main(String[] args) {
        shapes s = new shapes();
        circle c = new circle();
        square s1 = new square();
        triangle t = new triangle();
        s.area();
        s1.area();
        c.area();
        t.area();
        /*
         how overriding happens?
         the type of method called in overriding called depends upon what the type of object is
         what it is able to access is defined by the reference and which one it is able to access
         is defined by the object.
         overriding is done when the reference variable if of type superClass and which method will be called depends on object
         that the reference variable is pointing to.
         Base B1 = new Child();//upCasting
         the object type which one to run and the reference type defines which one to access.

         why Dynamic polymorphism is known as dynamic?
         java determines which method to call via the method dynamic method dispatch
         dynamic method dispatch : it is a mechanism by which a call to an overriding method is resolved at run-time rather than compile time

         when an overriden method is called through a superclass reference variable , java determines which version
         of that method to call based on the type of the object at the time this call happens, hence this determination will be made at run-time

         */
    }
}
