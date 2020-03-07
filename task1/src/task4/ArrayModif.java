package task4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;

public class ArrayModif {
    public static void main(String[] args) {
        ArrayList<Integer> x = new ArrayList<>();
        x.add(1);
        x.add(2);
        x.add(3);
        System.out.println(sum(x));
        System.out.println(sortRev(x));
    }

    public static <T extends Comparable<T>> ArrayList<T> sortRev(ArrayList<T> arr){
        arr.sort(Collections.reverseOrder());
        return arr;
    }

    public static double sum(ArrayList<? extends Number> arr){
        double sum = 0.0;
        for (Number x:arr) {
            sum += x.doubleValue();
        }
        return sum;
    }

    public static boolean hasValue(Map<Object, Object> map, Object val){
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            if(entry.getKey() == val || entry.getValue() == val)
                return true;
        }
        return false;
    }

    public static int find(ArrayList<Object> arr, Object x){
        for (int i = 0; i < arr.size(); i++)
            if(arr.get(i) == x) {
                return i;
            }
        return -1;
    }

    public static LinkedList<Object> pushHeadTail(LinkedList<Object> arr, Object x){
        arr.addFirst(x);
        arr.addLast(x);
        return arr;
    }
}
