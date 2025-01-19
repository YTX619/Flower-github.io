package Dao;

public class Address {
    private int address_id;
    private int user_id;
    private String address;
    private String tel;
    private String mobile;
    private String notice;

    public Address() {}

    public Address(int address_id, int user_id, String address, String tel, String mobile, String notice) {
        this.address_id = address_id;
        this.user_id = user_id;
        this.address = address;
        this.tel = tel;
        this.mobile = mobile;
        this.notice = notice;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}