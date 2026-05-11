package interfaces;

public class Car implements Engine, Brake, Media {
    @Override
    public void brake() {
        System.out.println("I brake like a nomral car");
    }

    @Override
    public void start() {
        System.out.println("I start engine like a normal car");
    }

    @Override
    public void stop() {
        System.out.println("I stop engine like a normal car");
    }

    @Override
    public void acc() {
        System.out.println("I accelerate engine like a normal car ");
    }
    //class will maintain like some state information through some instance variable but interface cannot.
    //variables are static and final by default in interfaces.
    //variables in abstract it may contain final or non-final both.
    //abstract class can provide the implementation of interface but the interface cannot provide the implementation of abstract class.
    //an interface can also extend another java interface
    //a class can implement more than one interfaces , a class can inherit only a single super class or abstract class
    //
}
