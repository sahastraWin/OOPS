package interfaces;

public class Main {
    public static void main(String[] args) {
//        Engine car = new Car();
//        car.acc();
//        car.stop();
//        car.start();
        //you should nots use interfaces in performance critical code
        //which particular method to run is determined at run-time

//        Media myCarMedia = new Car();
//        myCarMedia.stop();

        Nicecar car = new Nicecar();

        car.start();
        car.startMusic();
        car.upgradeEngine();
        car.start();//upgraded engine will start
    }
}
