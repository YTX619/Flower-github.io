package service;


import Dao.Flower;
import Dao.FlowerDAO;
import Dao.impl.FlowerDAOImpl;

import java.util.List;

public class FlowerService {
    private FlowerDAO flowerDAO;

    public FlowerService() {
        flowerDAO = new FlowerDAOImpl();
    }

    // add flower
    public boolean addFlower(Flower flower) {
        flowerDAO.saveFlower(flower);
        return true;
    }

    // update flower
    public void updateFlower(Flower flower) {
        flowerDAO.updateFlower(flower);
    }

    //delete flower
    public void deleteFlower(int flowerId) {
        flowerDAO.deleteFlower(flowerId);
    }

    // get flower by id
    public Flower getFlowerById(int flowerId) {
        return flowerDAO.getFlowerById(flowerId);
    }

    //get all flowers
    public List<Flower> getAllFlowers(int page, int pageSize) {
        return flowerDAO.getAllFlowers(page, pageSize);
    }

    // get total flowers
    public int getTotalFlowers() {
        return flowerDAO.getTotalFlowers();
    }

    // search flower
    public List<Flower> searchFlowers(String keyword) {
        return flowerDAO.searchFlowers(keyword);
    }
}