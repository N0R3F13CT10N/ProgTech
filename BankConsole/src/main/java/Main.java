import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

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
                    while (true) {
                        System.out.println("Input login:");
                        login = in.next();
                        if (DB.getUserByLogin(login) != null) {
                            System.out.println("Login is already in use! Try again!");
                        } else {
                            break;
                        }
                    }
                    while (true) {
                        System.out.println("Input password:");
                        password = in.next();
                        System.out.println("Input password once more:");
                        if (!password.equals(in.next())) {
                            System.out.println("Passwords don't match!Try again!");
                        } else {
                            break;
                        }
                    }
                    System.out.println("Input address:");
                    address = in.next();
                    while (true) {
                        System.out.println("Input phone:");
                        phone = in.next();
                        if (DB.getUserByPhone(phone) != null) {
                            System.out.println("Phone is already in use! Try again!");
                        } else {
                            break;
                        }
                    }
                    password = pe.encode(password);
                    db.insertUser(new User(login, password, address, phone));
                    break;
                case "2":
                    User temp = null;
                    for (int i = 0; i < 3; i++) {
                        System.out.println("Input login:");
                        temp = DB.getUserByLogin(in.next());
                        if (temp != null)
                            break;
                        else {
                            System.out.println("Invalid login!");
                        }
                    }
                    if (temp != null) {
                        for (int i = 0; i < 3; i++) {
                            System.out.println("Input password:");
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
                    if (currentUser != null) {
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
        ArrayList<String> currencyTypes = new ArrayList<String>();
        currencyTypes.add("RUB");
        currencyTypes.add("EUR");
        currencyTypes.add("USD");
        Map<String, Double> convert = Map.of("RUB_TO_USD", 0.013,
                "RUB_TO_EUR", 0.012, "EUR_TO_RUB", 84.09,
                "EUR_TO_USD", 1.09, "USD_TO_RUB", 77.72,
                "USD_TO_EUR", 0.92, "RUB_TO_RUB", 1.0,
                "EUR_TO_EUR", 1.0, "USD_TO_USD", 1.0);
        while (!quit) {
            ArrayList<Account> accs = DB.getUserAccs(currentUser.getId());
            System.out.println("1 - Create account\n2 - Deposit\n3 - Transfer\n4 - Check history\nq - Log off");
            switch (in.next()) {
                case "1":
                    try {
                        System.out.println("Choose currency:\n1 - RUB\n2 - EUR\n3 - USD");
                        UUID temp;
                        do {
                            temp = UUID.randomUUID();
                        } while (DB.getAccount(temp) != null);
                        db.insertAccount(new Account(temp, currentUser.getId(),
                                new BigDecimal("0.0"), currencyTypes.get(in.nextInt() - 1)));
                        System.out.println("Your new acc UUID:" + temp);
                    } catch (Exception exception) {
                        System.out.println("Incorrect input!");
                    }
                    break;
                case "2":
                    if (accs.size() == 0) {
                        System.out.println("You have no accounts!");
                    } else {
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
                            System.out.println("Choose currency:\n1 - RUB\n2 - EUR\n3 - USD");
                            String curr = currencyTypes.get(in.nextInt() - 1) + "_TO_" + acc.getAccCode();
                            System.out.println("Input sum:");
                            BigDecimal newSum = acc.getAmount().add(new BigDecimal(in.next()).
                                    multiply(new BigDecimal(convert.get(curr).toString())));
                            db.updateAccount(acc.getUuid(), newSum);
                            System.out.println("Now you have " + newSum +
                                    " " + acc.getAccCode() + " on your account!");
                        } catch (Exception exception) {
                            System.out.println("Incorrect input!");
                        }
                    }
                    break;
                case "3":
                    if (accs.size() == 0) {
                        System.out.println("You have no accounts!");
                    } else {
                        try {
                            System.out.println("Input phone number:");
                            User to = DB.getUserByPhone(in.next());
                            if (to == null) {
                                System.out.println("No users found!");
                            }
                            else if (to == currentUser) {
                                System.out.println("You can't transfer money to yourself!");
                            }
                            else if(DB.getUserAccs(to.getId()).size() == 0){
                                System.out.println("User has no accounts!");
                            }
                            else {
                                System.out.println("Choose your account:");
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
                                Account acc_from = accs.get(ind);
                                Account acc_to = DB.getUserAccs(to.getId()).get(0);
                                String curr = acc_from.getAccCode() + "_TO_" + acc_to.getAccCode();
                                System.out.println("Input sum:");
                                BigDecimal sum = new BigDecimal(in.next()).
                                        multiply(new BigDecimal(convert.get(curr).toString()));
                                if (sum.compareTo(acc_from.getAmount()) > 0) {
                                    System.out.println("You don't have enough money!");
                                } else {
                                    BigDecimal newSumFrom = acc_from.getAmount().subtract(sum);
                                    BigDecimal newSumTo = acc_to.getAmount().add(sum);
                                    db.updateAccount(acc_from.getUuid(), newSumFrom);
                                    db.updateAccount(acc_to.getUuid(), newSumTo);
                                    System.out.println("Now you have " + newSumFrom +
                                            " " + acc_from.getAccCode() + " on your account!");
                                }
                            }
                        } catch (Exception exception) {
                            System.out.println("Incorrect input!");
                        }
                    }
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