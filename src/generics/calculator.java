package generics;

public class calculator {
    public static void main(String[] args) {
        //body of abstract method
        Operation sum/*abstract methody type object*/ = (a, b) -> a + b;
        Operation difference/*abstract methody type object*/ = (a, b) -> a - b;
        Operation product/*abstract methody type object*/ = (a, b) -> a * b;

        calculator cal = new calculator();
        System.out.println(cal.operate(5, 6, sum));
        System.out.println(cal.operate(5, 6, difference));
        System.out.println(cal.operate(5, 6, product));
    }

    private int operate(int a, int b, Operation op) {
        return op.operation(a, b);
    }
}

interface Operation {
    int operation(int a, int b);//abstract method
}
