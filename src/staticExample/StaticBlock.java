package staticExample;

public class StaticBlock {
    static int a = 3;
    static int b = 7;

    /*
    static block will only run once , when first object is created i.e when the class is loaded for the first time
    static block code executes only once during the class loading. The static blocks always execute first before the main()
    method in Java because the compiler stores them in memory at the time of class loading and before the object creation.
     */
    static {
        System.out.println("Hey I am static");
        b = a * 5;//b=15
    }
    
    public static void main(String[] args) {
        StaticBlock s = new StaticBlock();
        System.out.println(s.a + " " + s.b);
        s.b += 67;
        StaticBlock s1 = new StaticBlock();//static block will not be called
        System.out.println(s1.a + " " + s1.b);
    }
}
