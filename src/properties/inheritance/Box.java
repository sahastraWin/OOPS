package properties.inheritance;

public class Box {
    double l;
    double h;
    double w;

    //default constructor
    Box() {
        this.l = -1;
        this.h = -1;
        this.w = -1;
    }

    //parameterized constructor
    Box(double side) {
        //super(); object class
        this.l = side;
        this.h = side;
        this.w = side;
    }

    Box(double l, double h, double w) {
        this.l = l;
        this.h = h;
        this.w = w;
    }

    //copy constructor
    Box(Box old) {
        this.l = old.l;
        this.h = old.h;
        this.w = old.w;
    }

    public void info() {
        System.out.println("Running info in Box class");
    }
}

