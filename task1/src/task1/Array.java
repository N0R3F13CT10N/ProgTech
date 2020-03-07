package task1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Array {
    public static void main(String[] args) {
        //task1();
        //task2();
        //task3();
        //task4();
        //task5();
        //task6();
        task7();
    }

    private static void task1() {
        Scanner in = new Scanner(System.in);
        System.out.println("Input N");
        List<Integer> arr = new ArrayList<>();
        int n = in.nextInt();
        System.out.println("Input N numbers");
        for (int i = 0; i < n; i++) {
            arr.add(in.nextInt());
        }
        System.out.println("Even:");
        for (Integer x : arr)
            if (x % 2 == 0)
                System.out.print(x + " ");
        System.out.println("\nOdd:");
        for (Integer x : arr)
            if (x % 2 != 0)
                System.out.print(x + " ");
    }

    private static void task2() {
        Scanner in = new Scanner(System.in);
        System.out.println("Input N");
        List<Integer> arr = new ArrayList<>();
        int n = in.nextInt();
        System.out.println("Input N numbers");
        for (int i = 0; i < n; i++) {
            arr.add(in.nextInt());
        }
        for (Integer x : arr)
            if (x % 3 == 0)
                System.out.print(x + " ");
    }

    private static void task3(){
        Scanner in = new Scanner(System.in);
        System.out.println("Input N");
        List<Integer> arr = new ArrayList<>();
        int n = in.nextInt();
        System.out.println("Input N numbers");
        for (int i = 0; i < n; i++) {
            arr.add(in.nextInt());
        }
        for (Integer x : arr)
            if (x % 5 == 0)
                System.out.print(x + " ");
    }

    private static void task4(){
        Scanner in = new Scanner(System.in);
        System.out.println("Input N");
        List<BigInteger> arr = new ArrayList<>();
        int n = in.nextInt();
        System.out.println("Input N numbers");
        BigInteger gcd = BigInteger.valueOf(in.nextInt());
        arr.add(gcd);
        int lcm = gcd.intValue();
        for (int i = 1; i < n; i++) {
            BigInteger x = BigInteger.valueOf(in.nextInt());
            gcd = gcd.gcd(x);
            lcm = x.intValue() * lcm / gcd.intValue();
            arr.add(x);
        }
        System.out.print("GCD: " + gcd);
        System.out.print("\nLCM: " + lcm);
    }

    private static boolean is_prime(int x){
        for (int i = 2; i <= Math.sqrt(x); i++)
            if (x % i == 0)
                return false;
        return true;
    }

    private static void task5(){
        Scanner in = new Scanner(System.in);
        System.out.println("Input N");
        List<Integer> arr = new ArrayList<>();
        int n = in.nextInt();
        System.out.println("Input N numbers");
        for (int i = 0; i < n; i++) {
            arr.add(in.nextInt());
        }
        System.out.println("Prime numbers");
        for (int x:arr) {
            if(is_prime(x))
                System.out.print(x + " ");
        }
    }

    private static boolean is_lucky(int x){
        String n = Integer.toString(x);
        int sum1 = 0, sum2 = 0;
        int i;
        for(i = 0; i < n.length() / 2; i++)
            sum1 += Integer.parseInt(n.substring(i, i+1));
        if(n.length() % 2 != 0)
            i++;
        for(; i < n.length(); i++)
            sum2 += Integer.parseInt(n.substring(i, i+1));
        return sum1 == sum2;
    }

    private static void task6(){
        Scanner in = new Scanner(System.in);
        System.out.println("Input N");
        List<Integer> arr = new ArrayList<>();
        int n = in.nextInt();
        System.out.println("Input N numbers");
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            arr.add(x);
        }
        System.out.println("Lucky numbers:");
        for (int x:arr) {
            if(is_lucky(x))
                System.out.print(x + " ");
        }
    }

    private static void task7(){
        try {
            String[] numbers = {"Ноль", "Один", "Два", "Три", "Четыре", "Пять",
                    "Шесть", "Семь", "Восемь", "Девять"};
            Scanner in = new Scanner(System.in);
            System.out.println("Input a digit");
            System.out.print(numbers[in.nextInt()]);
        }
        catch (Exception e){
            System.out.print("Not a digit");
            e.printStackTrace();
        }
    }
}
