import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {
    private static DB db = new DB();
    private static BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
    private static Scanner in = new Scanner(System.in);
    private static User currentUser;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DB.Init();
        boolean quit = false;
        while (!quit) {
            System.out.println("1 - Sign up\n2 - Sign in\nq - quit");
            switch (in.next()) {
                case "1":
                    String login, password, address, phone;
                    while (true){
                        System.out.println("Input login:");
                        login = in.next();
                        if (DB.getUser(login) != null){
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
                    if(currentUser != null){
                        secondMenu();
                    }
                    break;
                case "q":
                    quit = true;
                    DB.CloseDB();
                    break;
            }
        }
    }

    public static void secondMenu() throws SQLException, ClassNotFoundException {
        boolean quit = false;
        while (!quit) {
            System.out.println("1 - Create account\n2 - Deposit\n3 - Transfer\n4 - Check history\nq - quit");
            switch (in.next()) {
                case "1":
                    ArrayList<String> currency = new ArrayList<String>();
                    currency.add("RUB");
                    currency.add("EUR");
                    currency.add("USD");
                    System.out.println("Choose currency:\n1 - RUB\n2 - EUR\n3 - USD");
                    UUID temp;
                    do {
                        temp = UUID.randomUUID();
                    }while(DB.getAccount(temp) != null);
                    db.insertAccount(new Account(temp, currentUser.getId(),
                            new BigDecimal("0.0"), currency.get(in.nextInt() - 1)));
                    System.out.println("Your new acc UUID:" + temp);
                    break;
                case "2":
                    ArrayList<Account> accs = DB.getUserAccs(currentUser.getId());
                    if (accs.size() == 0){
                        System.out.println("You have no accounts!");
                    }
                    else {
                        try {
                            System.out.println("Choose account:");
                            for (int i = 1; i <= accs.size(); i++) {
                                Account x = accs.get(i - 1);
                                System.out.println(i + ": " + x.getUuid() + " "
                                        + x.getAmount() + " " + x.getAccCode());
                            }
                            int ind;
                            while (true) {
                                ind = in.nextInt() - 1;
                                if (ind < accs.size())
                                    break;
                                else {
                                    System.out.println("Wrong index!");
                                }
                            }
                            Account acc = accs.get(ind);
                            System.out.println("Input sum:");
                            BigDecimal newSum = acc.getAmount().add(new BigDecimal(in.next()));
                            db.updateAccount(acc.getUuid(), newSum);
                            System.out.println("Now you have " + newSum +
                                    " " + acc.getAccCode() + " on your account!");
                        }
                        catch (Exception exception){
                            System.out.println("Incorrect input!");
                        }
                    }
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "q":
                    quit = true;
                    currentUser = null;
                    break;
            }
        }
    }
}

