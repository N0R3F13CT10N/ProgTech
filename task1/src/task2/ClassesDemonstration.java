package task2;

import java.util.Scanner;

public class ClassesDemonstration {
    public static void main(String[] args) {
        date_demo();
        time_demo();
        rectangle_demo();
        book_demo();
        employee_demo();
    }

    public static void date_demo(){
        Scanner in = new Scanner(System.in);
        System.out.println("Input a date(d.m.y)");
        String[] tokens = in.next().split("\\.");
        Date date = new Date(Integer.parseInt(tokens[0]),
                Integer.parseInt(tokens[1]),
                Integer.parseInt(tokens[2]));
        System.out.print(date);
        boolean q = false;
        while(!q) {
            System.out.print("\n1 - increase year by one" +
                    "\n2 - decrease date by 2" +
                    "\n3 - check date and month match" +
                    "\n4 - increase month by 1" +
                    "\nAny other key - exit\n");
            String s = in.next();
            switch (s){
                case "1":
                    date.next_year();
                    System.out.print("Modified date: " + date);
                    break;
                case "2":
                    date.pre_pre_day();
                    System.out.print("Modified date: " + date);
                    break;
                case "3":
                    String out = date.month_day_match() ? "do" : "don't";
                    System.out.print("Month and year " + out + " match");
                    break;
                case "4":
                    date.next_mont();
                    System.out.print("Modified date: " + date);
                    break;
                default:
                    q = true;
                    break;
            }
            System.out.flush();
        }
    }

    public static void time_demo(){
        Scanner in = new Scanner(System.in);
        System.out.println("Input time(h:m:s)");
        String[] tokens = in.next().split(":");
        Time time = new Time(Integer.parseInt(tokens[0]),
                Integer.parseInt(tokens[1]),
                Integer.parseInt(tokens[2]));
        System.out.print(time);
        boolean q = false;
        while(!q) {
            System.out.print("\n1 - count total seconds" +
                    "\n2 - add 5 seconds" +
                    "\nAny other key - exit\n");
            String s = in.next();
            switch (s){
                case "1":
                    System.out.print("Total seconds:" + time.total_seconds());
                    break;
                case "2":
                    time.plus_5_sec();
                    System.out.print("Modified time: " + time);
                    break;
                default:
                    q = true;
                    break;
            }
            System.out.flush();
        }
    }

    public static void rectangle_demo(){
        Scanner in = new Scanner(System.in);
        System.out.println("Input rectangle points coords");
        Rectangle rectangle = new Rectangle(in.nextDouble(), in.nextDouble(),
                in.nextDouble(), in.nextDouble());
        System.out.print("Your rectangle points: " + rectangle);
        boolean q = false;
        while(!q) {
            System.out.print("\n1 - calculate square" +
                    "\n2 - check if it's a square" +
                    "\nAny other key - exit\n");
            String s = in.next();
            switch (s){
                case "1":
                    System.out.print("Square:" + rectangle.square());
                    break;
                case "2":
                    String out = rectangle.is_square() ? "is" : "isn't";
                    System.out.print("It " + out + " a square");
                    break;
                default:
                    q = true;
                    break;
            }
            System.out.flush();
        }
    }

    public static void book_demo(){
        Scanner in = new Scanner(System.in);
        System.out.println("Input book name");
        String name = in.nextLine();
        System.out.println("Input number of pages");
        int pages = in.nextInt();
        System.out.println("Input price");
        double price = in.nextDouble();
        System.out.println("Input year of publishing");
        int year = in.nextInt();
        Book book = new Book(name, pages, price, year);
        System.out.print(book);
        boolean q = false;
        while(!q) {
            System.out.print("\n1 - calculate avg page price" +
                    "\n2 - calculate days passed since publishing" +
                    "\nAny other key - exit\n");
            String s = in.next();
            switch (s){
                case "1":
                    System.out.print("Avg page price: " + book.page_price());
                    break;
                case "2":
                    System.out.print("Days passed since publishing: " + book.days_passed());
                    break;
                default:
                    q = true;
                    break;
            }
            System.out.flush();
        }
    }

    public static void employee_demo(){
        Scanner in = new Scanner(System.in);
        System.out.println("Input name");
        String name = in.nextLine();
        System.out.println("Input salary");
        int salary = in.nextInt();
        System.out.println("Input year of employment");
        int year = in.nextInt();
        Employee employee = new Employee(name, salary, year);
        System.out.print(employee);
        boolean q = false;
        while(!q) {
            System.out.print("\n1 - calculate experience" +
                    "\n2 - calculate days passed since employment" +
                    "\nAny other key - exit\n");
            String s = in.next();
            switch (s){
                case "1":
                    System.out.print("Experience: " + employee.experience() + " years");
                    break;
                case "2":
                    System.out.print("Days passed since employment: " +
                            employee.days_since_employment());
                    break;
                default:
                    q = true;
                    break;
            }
            System.out.flush();
        }
    }
}
