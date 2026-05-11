package modularity;

public class Modularity {

    /*
    Module 1: EngineModule
    Handles all operations related to the car's engine.
    */
    static class EngineModule {
        private boolean isEngineOn;

        public EngineModule() {
            isEngineOn = false;
        }

        public void startEngine() {
            isEngineOn = true;
            System.out.println("[Engine Module] Engine started successfully!");
        }

        public void stopEngine() {
            isEngineOn = false;
            System.out.println("[Engine Module] Engine stopped successfully!");
        }

        public boolean engineStatus() {
            return isEngineOn;
        }
    }

    /*
    Module 2: GearModule
    Handles gear shifting operations.
    */
    static class GearModule {
        private int currentGear;

        public GearModule() {
            currentGear = 0;
        }

        public void shiftGear(int gear) {
            currentGear = gear;
            System.out.println("[Gear Module] Shifted to gear " + currentGear);
        }

        public int getCurrentGear() {
            return currentGear;
        }
    }

    /*
    Module 3: SpeedModule
    Handles acceleration and braking.
    */
    static class SpeedModule {
        private int currentSpeed;

        public SpeedModule() {
            currentSpeed = 0;
        }

        public void accelerate() {
            currentSpeed += 20;
            System.out.println("[Speed Module] Accelerated to " + currentSpeed + " km/h");
        }

        public void brake() {
            currentSpeed -= 20;
            if (currentSpeed < 0) currentSpeed = 0;
            System.out.println("[Speed Module] Braked to " + currentSpeed + " km/h");
        }

        public int getCurrentSpeed() {
            return currentSpeed;
        }
    }

    /*
    Main CarSystem:
    Uses EngineModule, GearModule, and SpeedModule together.
    Demonstrates modularity where each module has its own responsibility.
    */
    static class CarSystem {
        private EngineModule engine;
        private GearModule gear;
        private SpeedModule speed;

        public CarSystem() {
            engine = new EngineModule();
            gear = new GearModule();
            speed = new SpeedModule();
        }

        public void startCar() {
            engine.startEngine();
        }

        public void shiftCarGear(int g) {
            if (!engine.engineStatus()) {
                System.out.println("[Car System] Cannot shift gear. Engine is OFF.");
                return;
            }
            gear.shiftGear(g);
        }

        public void accelerateCar() {
            if (!engine.engineStatus()) {
                System.out.println("[Car System] Cannot accelerate. Engine is OFF.");
                return;
            }
            speed.accelerate();
        }

        public void brakeCar() {
            speed.brake();
        }

        public void stopCar() {
            engine.stopEngine();
        }
    }

    // Main Method
    public static void main(String[] args) {
        CarSystem myCar = new CarSystem();

        myCar.startCar();
        myCar.shiftCarGear(1);
        myCar.accelerateCar();
        myCar.shiftCarGear(2);
        myCar.accelerateCar();
        myCar.brakeCar();
        myCar.stopCar();
    }
}
