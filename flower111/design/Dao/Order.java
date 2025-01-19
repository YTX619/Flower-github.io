package Dao;

public class Order {
    private int order_id;
    private int user_id;
    private int flower_id;
    private int order_num;
    private String order_notice;
    private String order_status;


    public Order() {}

    public Order(int order_id, int user_id, int flower_id, int order_num, String order_notice, String order_status) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.flower_id = flower_id;
        this.order_num = order_num;
        this.order_notice = order_notice;
        this.order_status = order_status;
    }


    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getFlower_id() {
        return flower_id;
    }

    public void setFlower_id(int flower_id) {
        this.flower_id = flower_id;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    public String getOrder_notice() {
        return order_notice;
    }

    public void setOrder_notice(String order_notice) {
        this.order_notice = order_notice;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}