package exceptionHandling;

public class exceptionsHandling {
    exceptionsHandling ec = new exceptionsHandling();

    public static void main(String[] args) {
        int a = 6;
        int b = 0;
        try {
            String name = "Jeet";
            if (name.equals("Jeet")) {
                throw new myOwnExecption("My own exception");
            }
        } catch (myOwnExecption e) {
            System.out.println(e.getMessage());
        }//more strict exceptions should be added first ,if you put exception first the below calls will never execute
        catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Normal Exception");
        } finally {
            System.out.println("this will always execute");
        }//there can be only one finally block

    }

    static int divide(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("please do not divide by zero");
        }
        return a / b;
    }
}

//difference between throws and throw   :
//throw means we are throwing an exception whereas throws is use for declaring exception
