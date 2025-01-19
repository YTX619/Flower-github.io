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

    // 添加鲜花
    public boolean addFlower(Flower flower) {
        flowerDAO.saveFlower(flower);
        return true;
    }

    // 更新鲜花信息
    public void updateFlower(Flower flower) {
        flowerDAO.updateFlower(flower);
    }

    // 删除鲜花
    public void deleteFlower(int flowerId) {
        flowerDAO.deleteFlower(flowerId);
    }

    // 根据ID获取鲜花
    public Flower getFlowerById(int flowerId) {
        return flowerDAO.getFlowerById(flowerId);
    }

    // 获取所有鲜花列表（分页）
    public List<Flower> getAllFlowers(int page, int pageSize) {
        return flowerDAO.getAllFlowers(page, pageSize);
    }

    // 获取鲜花总数
    public int getTotalFlowers() {
        return flowerDAO.getTotalFlowers();
    }

    // 模糊查询鲜花
    public List<Flower> searchFlowers(String keyword) {
        return flowerDAO.searchFlowers(keyword);
    }
}