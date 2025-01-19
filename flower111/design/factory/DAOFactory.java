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

    public static FlowerDAO getFlowerDAOInstance() {
        return new FlowerDAOImpl();
    }


    public static OrderDAO getOrderDAOInstance() {
        return new OrderDAOImpl();
    }


    public static UserDAO getUserDAOInstance() {
        return new UserDAOImpl();
    }


    public static AddressDAO getAddressDAOInstance() {
        return new AddressDAOImpl();
    }
}