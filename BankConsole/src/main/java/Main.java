import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        DB db = new DB();
        DB.Init();
        Scanner in = new Scanner(System.in);
        boolean quit = false;
        User currentUser = null;
        while (!quit) {
            System.out.println("1 - Sign up\n2 - Sign in\nq - quit");
            switch (in.next()) {
                case "1":
                    String login, password, address, phone;
                    while (true){
                        System.out.println("Input login:");
                        login = in.next();
                        if (!loginCheck(DB.getAllUsers(), login)){
                            System.out.println("Login is already in use! Try again!");
                        }
                        else{
                            break;
                        }
                    }
                    while (true){
                        System.out.println("Input password:");
                        password = in.next();
                        System.out.println("Input password once more:");
                        if(!password.equals(in.next())){
                            System.out.println("Passwords don't match!Try again!");
                        }
                        else {
                            break;
                        }
                    }
                    System.out.println("Input address:");
                    address = in.next();
                    System.out.println("Input phone:");
                    phone = in.next();
                    password = pe.encode(password);
                    db.insertUser(new User(login, password, address, phone));
                    break;
                case "2":
                    User temp = null;
                    for(int i = 0; i < 3; i++){
                        System.out.println("Input login:");
                        temp = DB.getUser(in.next());
                        if(temp != null)
                            break;
                        else {
                            System.out.println("Invalid login!");
                        }
                    }
                    if(temp != null) {
                        System.out.println("Input password:");
                        for (int i = 0; i < 3; i++) {
                            pe = new BCryptPasswordEncoder();
                            if (pe.matches(in.next(), temp.getPassword())) {
                                System.out.println("Welcome!");
                                currentUser = temp;
                                break;
                            } else {
                                System.out.println("Invalid password!");
                            }
                        }
                    }
                    break;
                case "q":
                    quit = true;
                    DB.CloseDB();
                    break;
            }
        }
    }


    public static boolean loginCheck(ArrayList<User> users, String login){
        for (User x: users) {
            if (x.getLogin().equals(login))
                return false;
        }
        return true;
    }
}

