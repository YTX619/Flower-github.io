package factory;


import Dao.AddressDAO;
import Dao.FlowerDAO;
import Dao.OrderDAO;
import Dao.UserDAO;
import Dao.impl.AddressDAOImpl;
import Dao.impl.FlowerDAOImpl;
import Dao.impl.OrderDAOImpl;
import Dao.impl.UserDAOImpl;

public class DAOFactory {
    // 获取FlowerDAO实例
    public static FlowerDAO getFlowerDAOInstance() {
        return new FlowerDAOImpl();
    }

    // 获取OrderDAO实例
    public static OrderDAO getOrderDAOInstance() {
        return new OrderDAOImpl();
    }

    // 获取UserDAO实例
    public static UserDAO getUserDAOInstance() {
        return new UserDAOImpl();
    }

    // 获取AddressDAO实例
    public static AddressDAO getAddressDAOInstance() {
        return new AddressDAOImpl();
    }
}