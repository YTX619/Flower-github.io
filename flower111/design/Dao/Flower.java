package Dao;

public class Flower {
    private int flower_id;
    private String flower_name;
    private double flower_price;
    private String flower_content;
    private int stock;


    public Flower() {}

    public Flower(int flower_id, String flower_name, double flower_price, String flower_content, int stock) {
        this.flower_id = flower_id;
        this.flower_name = flower_name;
        this.flower_price = flower_price;
        this.flower_content = flower_content;
        this.stock = stock;
    }


    public int getFlower_id() {
        return flower_id;
    }

    public void setFlower_id(int flower_id) {
        this.flower_id = flower_id;
    }

    public String getFlower_name() {
        return flower_name;
    }

    public void setFlower_name(String flower_name) {
        this.flower_name = flower_name;
    }

    public double getFlower_price() {
        return flower_price;
    }

    public void setFlower_price(double flower_price) {
        this.flower_price = flower_price;
    }

    public String getFlower_content() {
        return flower_content;
    }

    public void setFlower_content(String flower_content) {
        this.flower_content = flower_content;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}