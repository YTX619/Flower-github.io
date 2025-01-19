package service;



import Dao.User;
import Dao.UserDAO;
import Dao.impl.UserDAOImpl;
import util.BCryptUtil;

import java.util.List;

public class UserService {
    private UserDAO userDAO=new UserDAOImpl();



    // login
    public User login(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        if (user!= null && BCryptUtil.checkPassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    // register
    public boolean register(User user) {
        if (userDAO.getUserByUsername(user.getUsername()) == null) {
            user.setPassword(BCryptUtil.hashPassword(user.getPassword()));
            userDAO.registerUser(user);
            return true;
        }
        return false;
    }

    public boolean addUser(User user) {

        user.setPassword(BCryptUtil.hashPassword(user.getPassword()));
        return userDAO.addUser(user);
    }

    // update user
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    // get user by id
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    // get all users
    public List<User> getAllUsers(int page, int pageSize) {
        return userDAO.getAllUsers(page, pageSize);
    }

    // get users
    public int getTotalUsers() {
        return userDAO.getTotalUsers();
    }

    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }
    // search user
    public List<User> searchUsers(String keyword) {
        return userDAO.searchUsers(keyword);
    }
}