package Dao;


import java.util.List;

public interface UserDAO {

    User getUserByUsername(String username);

    boolean addUser(User user);


    void registerUser(User user);


    void updateUser(User user);


    User getUserById(int id);
    void deleteUser(int userId);

    List<User> getAllUsers(int page, int pageSize);


    int getTotalUsers();

    List<User> searchUsers(String keyword);
}