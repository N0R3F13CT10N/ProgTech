package task4;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PhoneDict {
    public static void main(String[] args) {
        Map<String, Integer> dict = new HashMap<>();
        boolean q = false;
        while(!q) {
            System.out.print("---------------------------\n");
            System.out.print("1 - Find record\n2 - Add record\n3 - Modify record" +
                    "\n4 - Delete record\n5 - Show all records\n");
            Scanner in = new Scanner(System.in);
            String x = in.next();
            switch (x){
                case "1":
                    System.out.print("---------------------------\n");
                    System.out.print("1 - Find by name\n2 - Find by number\n");
                    String y = in.next();
                    switch (y) {
                        case "1": {
                            System.out.print("Input name\n");
                            Integer res = dict.get(in.next());
                            if (res != null)
                                System.out.print("Number is:" + res + "\n");
                            else
                                System.out.print("Record not found!\n");
                            break;
                        }
                        case "2":
                            System.out.print("Input number\n");
                            String res = getKey(dict, in.nextInt());
                            if (res != null)
                                System.out.print("Name is:" + res + "\n");
                            else
                                System.out.print("Record not found!\n");
                            break;

                        default:
                            break;
                    }
                    break;
                case "2":
                    System.out.print("---------------------------\n");
                    System.out.print("Input name\n");
                    String name = in.next();
                    if(dict.get(name) != null){
                        System.out.print("Name already exists!\n");
                        break;
                    }
                    System.out.print("Input number\n");
                    int number = in.nextInt();
                    if(getKey(dict, number) != null){
                        System.out.print("Number already exists\n!");
                        break;
                    }
                    dict.put(name, number);
                    break;
                case "3":
                    System.out.print("---------------------------\n");
                    System.out.print("1 - Find by name\n2 - Find by number\n");
                    y = in.next();
                    switch (y) {
                        case "1": {
                            System.out.print("Input name\n");
                            String oldName = in.next();
                            Integer res = dict.get(oldName);
                            if (res != null) {
                                System.out.print("Input new name\n");
                                String newName = in.next();
                                dict.remove(oldName);
                                dict.put(newName, res);
                            }
                            else
                                System.out.print("Record not found!\n");
                            break;
                        }
                        case "2":
                            System.out.print("Input number\n");
                            int oldNum = in.nextInt();
                            String res = getKey(dict, oldNum);
                            if (res != null) {
                                System.out.print("Input new number\n");
                                int newNum = in.nextInt();
                                dict.remove(res);
                                dict.put(res, newNum);
                            }
                            else
                                System.out.print("Record not found!\n");
                            break;
                        default:
                            break;
                    }
                    break;
                case "4":
                    System.out.print("---------------------------\n");
                    System.out.print("1 - Find by name\n2 - Find by number\n");
                    y = in.next();
                    switch (y) {
                        case "1": {
                            System.out.print("Input name\n");
                            name = in.next();
                            Integer res = dict.get(name);
                            if (res != null) {
                                dict.remove(name);
                                System.out.print("Record removed!\n");
                            }
                            else
                                System.out.print("Record not found!\n");
                            break;
                        }
                        case "2":
                            System.out.print("Input number\n");
                            int num = in.nextInt();
                            String res = getKey(dict, num);
                            if (res != null) {
                                dict.remove(res);
                                System.out.print("Record removed!\n");
                            }
                            else
                                System.out.print("Record not found!\n");
                            break;
                        default:
                            break;
                    }
                    break;
                case "5":
                    System.out.print("---------------------------\n");
                    System.out.print("All records:\n");
                    for (Map.Entry<String, Integer> entry : dict.entrySet()) {
                        System.out.print(entry.getKey() + " : " + entry.getValue() + "\n");
                    }
                    break;
                default:
                    q = true;
                    break;
            }
        }
    }

    public static String getKey(Map<String, Integer> map, Integer x){
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if(entry.getValue().equals(x))
                return entry.getKey();
        }
        return null;
    }
}
