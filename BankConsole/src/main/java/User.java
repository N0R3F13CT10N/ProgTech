public class User {
    private Integer id;
    private String login;
    private String password;
    private String address;
    private String phone;

    public User(String login, String password, String address, String phone) {
        this.login = login;
        this.password = password;
        this.address = address;
        this.phone = phone;
    }

    public User(Integer id, String login, String password, String address, String phone) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.address = address;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
