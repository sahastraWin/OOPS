package interfaces.extend2Interface;

public class Main implements A, B {
    @Override
    public void greet() {

    }

    public static void main(String[] args) {
        Main o = new Main();
           A.greeting();//calling by Interface name
    }
}
