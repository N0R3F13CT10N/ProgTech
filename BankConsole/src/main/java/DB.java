import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;


public class DB {
    public static Connection conn;
    public static Statement statmt;


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
                "'date' text, " +
                "'accCode' text, 'accFrom' text," +
                "'accTo' text, 'sum' DECIMAL, 'sumBefore' DECIMAL, 'sumAfter' DECIMAL);");
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
        String sql = "INSERT INTO user(login, password, address, phone) VALUES(?,?,?,?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getLogin());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3, user.getAddress());
        pstmt.setString(4, user.getPhone());
        pstmt.executeUpdate();
    }

    public static ArrayList<Account> getUserAccs(int id) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("select * from account where client_id = ?");
        statement.setInt(1, id);
        ResultSet rst = statement.executeQuery();
        ArrayList<Account> accs = new ArrayList<>();
        while(rst.next()) {
           accs.add(new Account(UUID.fromString(rst.getString("uuid")),
                   rst.getInt("client_id"),
                   rst.getBigDecimal("amount"),
                   rst.getString("accCode")));
        }
        return accs;
    }

    public static User getUserByLogin(String login) throws SQLException {
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

    public static User getUserByPhone(String phone) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("select * from user where phone = ?");
        statement.setString(1, phone);
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

    public void insertAccount(Account account) throws SQLException {
        String sql = "INSERT INTO account(uuid, client_id, amount, accCode) VALUES(?,?,?,?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, account.getUuid().toString());
        pstmt.setInt(2, account.getClientId());
        pstmt.setBigDecimal(3, account.getAmount());
        pstmt.setString(4, account.getAccCode());
        pstmt.executeUpdate();
    }

    public static Account getAccount(UUID uuid) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("select * from account where uuid = ?");
        statement.setString(1, uuid.toString());
        ResultSet rst = statement.executeQuery();
        if(rst.next()) {
            return new Account(UUID.fromString(rst.getString("uuid")),
                    rst.getInt("client_id"),
                    rst.getBigDecimal("amount"),
                    rst.getString("accCode"));
        }
        else {
            return null;
        }
    }

    public void updateAccount(UUID uuid, BigDecimal sum) throws SQLException {
        String sql = "UPDATE account SET amount = ? WHERE uuid = ? ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setBigDecimal(1, sum);
        pstmt.setString(2, uuid.toString());
        pstmt.executeUpdate();
    }

    public void insertOp(Operation op) throws SQLException {
        String sql = "INSERT INTO operation(date, accCode, accFrom, accTo, sum, sumBefore, sumAfter) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, op.getDate().toString());
        pstmt.setString(2, op.getAccCode());
        if(op.getAccFrom() == null){
            pstmt.setNull(3, Types.VARCHAR);
        }
        else{
            pstmt.setString(3, op.getAccFrom().toString());
        }
        pstmt.setString(4, op.getAccTo().toString());
        pstmt.setBigDecimal(5, op.getSum());
        pstmt.setBigDecimal(6, op.getSumBefore());
        pstmt.setBigDecimal(7, op.getSumAfter());
        pstmt.executeUpdate();
    }


    public ArrayList<Operation> getOps(int id) throws SQLException, ParseException {
        PreparedStatement statement = conn.prepareStatement("select " +
                " Operation.id, Operation.date, Operation.accCode," +
                " Operation.accFrom, Operation.accTo, Operation.sum from operation" +
                " inner join Account ON Account.client_id = ?" +
                " where operation.accFrom = account.uuid OR operation.accTo= account.uuid" +
                " limit 3");
        statement.setInt(1, id);
        ResultSet rst = statement.executeQuery();
        ArrayList<Operation> ops = new ArrayList<>();
        while(rst.next()) {
            String temp = rst.getString("accFrom");
            UUID from;
            if(temp == null)
                from = null;
            else{
                from = UUID.fromString(temp);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
            ops.add(new Operation(rst.getInt("id"),
                    simpleDateFormat.parse(rst.getString("date")),
                    rst.getString("accCode"),
                    from,
                    UUID.fromString(rst.getString("accTo")),
                    new BigDecimal(rst.getString("sum")), null, null));
        }
        return ops;
    }
}