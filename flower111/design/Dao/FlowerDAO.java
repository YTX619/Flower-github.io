package Dao;

import java.util.List;

public interface FlowerDAO {

    void saveFlower(Flower flower);

    boolean addFlower(Flower flower);

    boolean updateFlower(Flower flower);


    void deleteFlower(int flowerId);


    Flower getFlowerById(int flowerId);


    List<Flower> getAllFlowers(int page, int pageSize);


    int getTotalFlowers();


    List<Flower> searchFlowers(String keyword);
}