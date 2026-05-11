package properties.inheritance;

public class Main2 {
    public static void main(String[] args) {
//        Box box1 = new Box();
//        Box box2 =new Box(box1);
//        System.out.println(box1.l + " " + box1.h + " " + box1.w);

//        BoxWeight box3 = new BoxWeight();
//        BoxWeight box4 = new BoxWeight(1,2,3,4);
//        System.out.println(box3.h + " " + box3.weight);

        Box box5 = new BoxWeight(2, 3, 4, 6);
        System.out.println(box5.w);
        /*
          Box box5 = new BoxWeight(2,3,4,6);
          System.out.println(box5.weight);//error
          It is important to understand that it is the type of reference variable and no the type of the object that determines the
          what members can be accessed.

          BoxWeight box6 = new Box(1,2,3);//error
          System.out.println("box6");//error
         */
    }
}
