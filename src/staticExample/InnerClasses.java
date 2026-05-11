package staticExample;

/*
outside or top classes can be static From the above points, we can say Java's creators had not allowed an outer class to
be static because there is no need to make it static. Allowing to make the outer class static will only increase complications,
ambiguity, and duplicity.
 */

/*
class Test {
    static String name;

    public Test(String name) {
        Test.name = name;
   }
 */


public class InnerClasses {
    static class Test {
        String name;

        public Test(String name) {
            this.name = name;
        }

    }

    public static void main(String[] args) {
        /*
        error will be resolved if the test class is either declared static or the test class defined outside
        so that it is not dependent on any other class
         */
        Test t1 = new Test("Sahastrajeet");
        Test t2 = new Test("Honey");
        System.out.println(t1.name);
        System.out.println(t2.name);
        /*
        if Test class is declared static then the o/p is
        Sahastrajeet
        Honey

        if Test class is declared outside then the o/p is
        Honey
        Honey
        will be printed
        Key Points:
        name is declared as a static variable, which means it is shared among all instances of the Test class. There is only one copy of name in memory,
        regardless of how many objects of the Test class are created.

        When you call the constructor public Test(String name) for each object (t1 and t2), the line Test.name = name; assigns the value of the parameter
        name to the static variable name.

        Execution Flow:
        First, t1 is created with the parameter "Sahastrajeet". This assigns Test.name = "Sahastrajeet".
        Next, t2 is created with the parameter "Honey". This overwrites the static variable Test.name to "Honey".

         Outputs:
         System.out.println(t1.name);: Since name is static and shared, this will print "Honey", which is the last value assigned to name.
         System.out.println(t2.name);: This will also print "Honey", for the same reason.
         */

    }
}
