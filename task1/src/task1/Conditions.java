package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Conditions {
    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
    }

    private static void task1() {
        Scanner in = new Scanner(System.in);
        System.out.println("Input 2 numbers");
        int num1 = in.nextInt();
        int num2 = in.nextInt();
        String out = num1 * num1 + num2 * num2 > (num1 + num2) * (num1 + num2) ?
                "Sum of squares" : "Square of sum";
        System.out.println(out);
    }

    private static void task2() {
        Scanner in = new Scanner(System.in);
        System.out.println("Input salary and experience");
        int salary = in.nextInt();
        int experience = in.nextInt();
        double bounty = 0;
        if(experience >= 2 && experience <= 4)
            bounty = salary * 0.05;
        if(experience >= 5 && experience <= 9)
            bounty = salary * 0.1;
        System.out.println("Your bounty is " + bounty);
    }

    private static void task3() {
        Scanner in = new Scanner(System.in);
        System.out.println("Input point A coords");
        int x1 = in.nextInt();
        int y1 = in.nextInt();
        System.out.println("Input point B coords");
        int x2 = in.nextInt();
        int y2 = in.nextInt();
        String out = x1 * x1 + y1 * y1 > x2 * x2 + y2 * y2 ? "A" : "B";
        System.out.println(out + " is further from (0,0)");
    }

    private static void task4() {
        Scanner in = new Scanner(System.in);
        System.out.println("Input point a, b and c");
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        String out = (a * a + b * b == c * c) | (b * b + c * c == a * a) |
                (c * c + a * a == b * b) ? "is" : "isn't";
        System.out.println("Triangle " + out + " right");
    }

    private static void task5() {
        List<Integer> nums = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Input 3 numbers");
        for(int i = 0; i < 3; i++) {
            int temp = in.nextInt();
            if(temp > 0)
                temp *= temp;
            nums.add(temp);
        }
        System.out.println("Modified numbers:");
        System.out.println(nums);
    }

    private static void task6() {
        Scanner in = new Scanner(System.in);
        String[] months = {"January", "February", "March", "April", "May", "June", "July",
                            "August", "September", "October", "November", "December"};
        System.out.println("Input a number from 1 to 12");
        System.out.print(months[in.nextInt()-1]);
    }
}
