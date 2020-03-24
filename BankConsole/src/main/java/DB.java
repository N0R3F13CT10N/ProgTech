import java.sql.*;
import java.util.ArrayList;


public class DB {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;


    public static void Init() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:bank.db");
        System.out.println("Base connected!");
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'User' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'login' text, " +
                "'password' text, 'address' text, 'phone' text);");
        statmt.execute("CREATE TABLE if not exists 'Account' ('uuid' text," +
                "'client_id' INTEGER," +
                "'amount' DECIMAL DEFAULT 0.0, 'accCode' text," +
                "FOREIGN KEY(client_id) REFERENCES User(id));");
        statmt.execute("CREATE TABLE if not exists 'Operation' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "'date' DATE, " +
                "'accCode' text, 'accFrom' INTEGER," +
                "'accTo' INTEGER, 'sum' DECIMAL, 'sumBefore' DECIMAL, 'sumAfter' DECIMAL);");
        System.out.println("Tables created!");
    }

    public static void CloseDB() throws SQLException
    {
        conn.close();
        statmt.close();
        //resSet.close();
        System.out.println("Connection closed!");
    }

    public void insertUser(User user) throws SQLException {
        String sql = "INSERT INTO user(login,password, address, phone) VALUES(?,?,?,?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getLogin());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3, user.getAddress());
        pstmt.setString(4, user.getPhone());
        pstmt.executeUpdate();
    }

    public static ArrayList<User> getAllUsers() throws ClassNotFoundException, SQLException {
        Statement stm;
        stm = conn.createStatement();
        ResultSet rst = stm.executeQuery("Select * From User");
        ArrayList<User> userList = new ArrayList<User>();
        while (rst.next()) {
            User user = new User(rst.getInt("id"),
                    rst.getString("login"),
                    rst.getString("password"),
                    rst.getString("address"),
                    rst.getString("phone"));
            userList.add(user);
        }
        return userList;
    }

    public static User getUser(String login) throws ClassNotFoundException, SQLException {
        PreparedStatement statement = conn.prepareStatement("select * from user where login = ?");
        statement.setString(1, login);
        ResultSet rst = statement.executeQuery();
        if(rst.next()) {
            return new User(rst.getInt("id"),
                    rst.getString("login"),
                    rst.getString("password"),
                    rst.getString("address"),
                    rst.getString("phone"));
        }
        else {
            return null;
        }
    }
}