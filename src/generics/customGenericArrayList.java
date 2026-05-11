package generics;

import java.util.ArrayList;
import java.util.Arrays;

public class customGenericArrayList<T> {

    private Object[] data;
    private static int DEFAULT_SIZE = 10;
    private int size = 0;//also working as index value.

    /*
    public customGenericArrayList() {
        this.data = new T[DEFAULT_SIZE];
        //type parameter T cannot be instantiated directly like this.
        //you cannot create an instance of generic type.
        //generics incur no runtime overhead.
        //at runtime bytecode gets executed but in bytecode gets executed and if you don't the value of T how can you do that.
        //
    }
     */

    public customGenericArrayList() {
        this.data = new Object[DEFAULT_SIZE];
    }

    public void add(T num) {
        if (isFull()) {
            resize();
        }
        data[size++] = num;
    }

    private void resize() {
        Object[] temp = new Object[data.length * 2];

        //copy the current items in the new array
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data=temp;
    }

    private boolean isFull() {//array is full which basically means size=data.length
        return size == data.length;
    }

    public T remove() {
        T removed = (T)data[--size];//here in the smaller type or more restricted type you are trying to add a bigger type
        return removed;
    }

    public T get(int index) {
        return (T)data[index];
    }

    public int size() {
        return size;//size indicates how many elements you have inserted;
    }

    public void set(int index, T value) {
        data[index] = value;
    }

    @Override
    public String toString() {
        return "customArrayList{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }

    public static void main(String[] args) {
        //ArrayList List = new ArrayList();
        //in array list when we keep on adding things to it , at a particular when it gets full then after it gets doubled.
        //doubling happens exponentially and it takes constant time on an average.
        customGenericArrayList<Integer> list = new customGenericArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(list);
    }
}
