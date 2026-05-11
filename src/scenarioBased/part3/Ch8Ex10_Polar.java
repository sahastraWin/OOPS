package scenarioBased.part3;/*
 * Chapter 8, Exercise 10:
 * For math buffs only: Create a class Polar that represents the points on the plain as
 * polar coordinates (radius and angle). Create an overloaded + operator for addition of
 * two Polar coordinates. "Adding" two points on the plain can be accomplished by adding
 * their X coordinates and then adding their Y coordinates. This gives the X and Y coordinates
 * of the "answer." Thus you'll need to convert two sets of polar coordinates to rectangular
 * coordinates, add them, then convert the resulting rectangular representation back to polar.
 */

public class Ch8Ex10_Polar {

    static class Polar {
        private double radius;
        private double angle; // in radians

        public Polar() { radius = 0; angle = 0; }
        public Polar(double r, double a) { radius = r; angle = a; }

        // Convert polar to rectangular
        public double getX() { return radius * Math.cos(angle); }
        public double getY() { return radius * Math.sin(angle); }

        // Create Polar from rectangular coordinates
        public static Polar fromRect(double x, double y) {
            double r = Math.sqrt(x * x + y * y);
            double a = Math.atan2(y, x);
            return new Polar(r, a);
        }

        // Overloaded + operator: add two polar points via rectangular
        public Polar add(Polar p2) {
            double x = this.getX() + p2.getX();
            double y = this.getY() + p2.getY();
            return fromRect(x, y);
        }

        public void display() {
            System.out.printf("Polar(r=%.4f, angle=%.4f rad / %.2f deg)%n",
                radius, angle, Math.toDegrees(angle));
        }
    }

    public static void main(String[] args) {
        Polar p1 = new Polar(5.0, Math.toRadians(30));
        Polar p2 = new Polar(3.0, Math.toRadians(60));

        System.out.print("p1 = "); p1.display();
        System.out.print("p2 = "); p2.display();

        Polar sum = p1.add(p2);
        System.out.print("p1 + p2 = "); sum.display();
        System.out.printf("Rectangular: (%.4f, %.4f)%n", sum.getX(), sum.getY());
    }
}
