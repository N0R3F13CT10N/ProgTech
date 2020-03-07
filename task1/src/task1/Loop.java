package task1;

import java.util.Scanner;

public class Loop {
    public static void main(String[] args) {
        //task1();
        //task2();
        //task3();
        task4();
    }


    private static int Digit_Sum(int m) {
        int n, sum = 0;
        while (m > 0) {
            n = m % 10;
            sum = sum + n;
            m = m / 10;
        }
        return sum;
    }

    private static void task1(){
        int a = 3;
        Scanner in = new Scanner(System.in);
        System.out.println("Input N");
        int n = in.nextInt();
        while(a <= n){
            if (a % 5 != 0 & Digit_Sum(a) % 5 != 0)
                System.out.println(a);
            a += 3;

        }
    }

    private static void task2(){
        Scanner in = new Scanner(System.in);
        System.out.println("Input N");
        int n = in.nextInt();
        for(int i = 1; i < n; i ++)
            if (i % 5 == 0)
                System.out.println(i);
    }

    private static void task3() {
        Scanner in = new Scanner(System.in);
        System.out.println("Input a number");
        String out = Math.log(in.nextInt()) / Math.log(2) % 1 == 0 ? "is" : "isn't";
        System.out.println("Number " + out + " power of 2");
    }

    private static void task4() {
        Scanner in = new Scanner(System.in);
        System.out.println("Input a number");
        int a = 1, b = 1, c = 1, n = in.nextInt();
        System.out.print("1");
        while(c <= n){
            System.out.print(" " + c);
            a = b;
            b = c;
            c = a + b;
        }
    }
}
