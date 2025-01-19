package Dao;


import java.util.List;

public interface UserDAO {
    // 根据用户名查询用户
    User getUserByUsername(String username);

    boolean addUser(User user);

    // 注册用户
    void registerUser(User user);

    // 更新用户信息
    void updateUser(User user);

    // 根据ID查询用户
    User getUserById(int id);
    void deleteUser(int userId);
    // 获取所有用户列表（分页）
    List<User> getAllUsers(int page, int pageSize);

    // 获取用户总数
    int getTotalUsers();

    // 模糊查询用户
    List<User> searchUsers(String keyword);
}