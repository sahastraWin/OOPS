package properties.inheritance;

public class BoxWeight extends Box {
    double weight;

    public BoxWeight() {
        this.weight = -1;
    }

    public BoxWeight(double weight) {
        this.weight = weight;
    }

    BoxWeight(BoxWeight other) {
        super(other);
        weight = other.weight;
    }

    public BoxWeight(double l, double h, double w, double weight) {
        super(l, h, w);//calls the parent class constructor
        //use to initialise values present in parent class constructor
        //although subclass includes all the members of the parent class it still won't be able to fetch/access the members which are declared as private
        // in super or parent class
        this.weight = weight;
        //super(l, h, w); error if this is initialized later on
        //if super keyword in not used in subClass's constructor then default constructor will be called
    }
}
