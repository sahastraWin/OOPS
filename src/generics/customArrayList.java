package generics;

import java.util.ArrayList;
import java.util.Arrays;

public class customArrayList {

    private int[] data;
    private static int DEFAULT_SIZE = 10;
    private int size = 0;//also working as index value.

    public customArrayList() {
        this.data = new int[DEFAULT_SIZE];
    }

    public void add(int num) {
        if (isFull()) {
            resize();
        }
        data[size++] = num;
    }

    private void resize() {
        int[] temp = new int[data.length * 2];

        //copy the current items in the new array
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
    }

    private boolean isFull() {//array is full which basically means size=data.length
        return size == data.length;
    }

    public int remove() {
        int removed = data[--size];
        return removed;
    }

    public int get(int index) {
        return data[index];
    }

    public int size() {
        return size;//size indicates how many elements you have inserted;
    }

    public void set(int index, int value) {
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
        customArrayList list = new customArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(list);
    }
}
