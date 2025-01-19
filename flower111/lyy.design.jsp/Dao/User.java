package Dao;


public class User {
    private int user_id;
    private String username;
    private String password;
    private String realname;
    private String sex;
    private String email;
    private String user_flag;

    // 构造方法
    public User() {}

    public User(int user_id, String username, String password, String realname, String sex, String email, String user_flag) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.sex = sex;
        this.email = email;
        this.user_flag = user_flag;
    }

    // Getter和Setter方法
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_flag() {
        return user_flag;
    }

    public void setUser_flag(String user_flag) {
        this.user_flag = user_flag;
    }
}