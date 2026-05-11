package generics.comparing;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Student kunal = new Student(12, 109.89f);
        Student rahul = new Student(532, 129.87f);
        Student suman = new Student(53, 69.87f);
        Student sachin = new Student(12, 919.827f);
        Student raj = new Student(51, 139.87f);

//        if (kunal.compareTo(rahul) < 0) {
//            System.out.println("Rahul has more marks");
//        } else {
//            System.out.println("kunal has more marks");
//        }

        Student list[] = {kunal, rahul, suman, sachin, raj};
        System.out.println(Arrays.toString(list));
        Arrays.sort(list, (o1, o2) -> {
            return (int) (o1.rollNo - o2.rollNo);//will sort in ascending order
            // return -(int) (o1.rollNo - o2.rollNo); //will sort in ascending order
        });
        System.out.println(Arrays.toString(list));
    }
}
