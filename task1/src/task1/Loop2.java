package task1;

import java.util.Scanner;

public class Loop2 {
    public static void main(String[] args) {
        //task1();
        //task2();
        //task3();
        task4();
    }

    private static void task1(){
        Scanner in = new Scanner(System.in);
        System.out.println("Input A and B");
        int a = in.nextInt();
        int b = in.nextInt();
        int c = 0;
        for(int i = a; i <= b; i++, c++){
            System.out.print(i+" ");
        }
        System.out.println("\nIn total: " + c);
    }

    private static void task2(){
        Scanner in = new Scanner(System.in);
        System.out.println("Input A and B");
        int a = in.nextInt();
        int b = in.nextInt();
        int c = 0;
        for(int i = a - 1; i > b; i--, c++){
            System.out.print(i+" ");
        }
        System.out.println("\nIn total: " + c);
    }

    private static void task3(){
        Scanner in = new Scanner(System.in);
        System.out.println("Input A and N");
        int a = in.nextInt();
        int n = in.nextInt();
        int x = 1;
        for(int i = 0; i < n; i++)
            x *= a;
        System.out.println(x);
    }

    private static void task4(){
        Scanner in = new Scanner(System.in);
        System.out.println("Input A and N");
        int a = in.nextInt();
        if (a == 0)
            return;
        int n = in.nextInt();
        int x = a;
        while(x < n){
            System.out.println(x);
            x *= a;
        }
    }
}
