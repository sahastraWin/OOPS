package abstractClass;

public abstract class Parent {

    int age;

    public Parent(int age) {
        this.age = age;
    }

    //abstract static methods be created but static methods can be created in abstract classes.
    //you cannot create abstract static constructors in this.
    //if one of the methods is abstract then the class needs to be abstract in this.

    abstract void career();
    abstract void partner();
}
