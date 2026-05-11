package objectCloning;

public class Human implements Cloneable {
    int age;
    String name;
    int[] arr;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
        this.arr = new int[]{1, 2, 3, 4, 5};
    }

    //    public Object clone() throws CloneNotSupportedException {
    //        return super.clone();
    //    }

    @Override
    public Object clone() throws CloneNotSupportedException {
    /*
    This method belongs to the Object class, which is a base class of every class created in Java. This method
    helps to create a copy of the object, but if the class doesn't support a cloneable interface then
    it leads to the exception,"CloneNotSupportedException".

    if you want to clone an object for that class you have to implement cloneable.

    implements cloneable ensures that you can clone an object for that class.

    Cloneable has no abstract methods but yet we still are implementing it because it is way to tell JVM that we have to just
    perform clone on our object of type "Class Name".
    */
        Human twin = (Human) super.clone();//this is actually deep copy

        //making a deep copy
        twin.arr = new int[twin.arr.length];
        for (int i = 0; i < twin.arr.length; i++) {
            twin.arr[i] = this.arr[i];
        }
        return twin;
    }
}
