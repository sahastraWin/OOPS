package generics.comparing;

public class Student  {
    int rollNo;
    float marks;

    public Student(int rollNo, float marks) {
        this.rollNo = rollNo;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return rollNo + " ";
    }

//    @Override
//    public int compareTo(Student o) {
//        int diff = (int) (this.marks - o.marks);
//        //  if diff==0   : both are equal
//        //  if diff < 0  : means o is bigger or else o is smaller
//        return diff;
//    }
}

