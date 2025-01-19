package service;



import Dao.User;
import Dao.UserDAO;
import Dao.impl.UserDAOImpl;
import util.BCryptUtil;

import java.util.List;

public class UserService {
    private UserDAO userDAO=new UserDAOImpl();



    // 用户登录验证
    public User login(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        if (user!= null && BCryptUtil.checkPassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    // 用户注册
    public boolean register(User user) {
        if (userDAO.getUserByUsername(user.getUsername()) == null) {
            user.setPassword(BCryptUtil.hashPassword(user.getPassword()));
            userDAO.registerUser(user);
            return true;
        }
        return false;
    }

    public boolean addUser(User user) {
        // 可以在这里先对用户信息进行一些必要的验证，比如用户名是否符合规范等，这里简单示例省略验证逻辑
        user.setPassword(BCryptUtil.hashPassword(user.getPassword()));
        return userDAO.addUser(user);
    }

    // 更新用户信息
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    // 根据ID获取用户
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    // 获取所有用户列表（分页）
    public List<User> getAllUsers(int page, int pageSize) {
        System.out.println("获取用户列表");
        return userDAO.getAllUsers(page, pageSize);
    }

    // 获取用户总数
    public int getTotalUsers() {
        return userDAO.getTotalUsers();
    }

    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }
    // 模糊查询用户
    public List<User> searchUsers(String keyword) {
        return userDAO.searchUsers(keyword);
    }
}