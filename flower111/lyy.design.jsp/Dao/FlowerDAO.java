package Dao;

import java.util.List;

public interface FlowerDAO {
    // 保存鲜花
    void saveFlower(Flower flower);

    boolean addFlower(Flower flower);
    // 更新鲜花
    boolean updateFlower(Flower flower);

    // 删除鲜花
    void deleteFlower(int flowerId);

    // 根据ID查询鲜花
    Flower getFlowerById(int flowerId);

    // 获取所有鲜花列表（分页）
    List<Flower> getAllFlowers(int page, int pageSize);

    // 获取鲜花总数
    int getTotalFlowers();

    // 模糊查询鲜花
    List<Flower> searchFlowers(String keyword);
}