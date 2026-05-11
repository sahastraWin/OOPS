package introduction;

public class Main {


    public static void main(String[] args) {
        Student s1 = new Student();//declaring the reference variable to the object of type Student
//        System.out.println(s1.rno);
//        System.out.println(s1.name);
//        System.out.println(s1.marks);
        Student s2 = new Student(19, "Sahaj", 91.90f);
        Student random = new Student(s2);
        Student random2 = new Student();
//        System.out.println(random.rno);
//        System.out.println(random.name);  
//        System.out.println(random.marks);
        System.out.println(random2.name);


    }

    //create class
    //data  type for every single student
    //data  type for every single student
    static class Student {

        //data of 5 students:{roll no,name,marks}
        int rno; //primitive dataType
        String name;//objects (it will print null by default)
        float marks;//primitive dataType

        Student() {
//            this.rno = 18;
//            this.name = "Sahastrajeet Hardaha";
//            this.marks = 78.00f;
            //this is how we call a constructor from another constructor
            //internally : new Student(20,"Sahastrajeet",100.000f);
            this(20, "SahastrWin", 98.9f);
        }

        Student(int rno, String name, float marks) {
            this.rno = rno;
            this.name = name;
            this.marks = marks;
        }

        Student(Student other) {
            this.rno = other.rno;
            this.name = other.name;
            this.marks = other.marks;
        }


    }

    /*
    Constructor : It defines you what will happen when an object will be created.It is a special function
                  which allocates somme variables
    Note        : In JAVA the primitive dataTypes are not implemented as objects , objects are stored in heap memory.In JAVA
                  primitives are not objects that's why they are stored in heap memory only.In Java there is no pass by
                  reference , everything is pass by value.When you pass objects the reference's value is passed.

                  Final dataType guarantees immutability only when the instance variables are primitive dataTypes and not
                  the reference's type of objects and stuff because the instance variable of reference type has a final modifier
                  behind it the value of instance variable will never change (the reference to the object will never change)
                  it will always refer to the same object but the value of the object will change.When a non-primitive is
                  final you cannot reassign it.
     */

}
