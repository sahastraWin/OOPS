package properties.polymorphism;

//by default in java every class extends object class
public class Object {
    int num;

    public Object(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Object{" +
                "num=" + num +
                '}';
    }

    public static void main(String[] args) {
        Object obj = new Object(45);
        System.out.println(obj);
    }
}