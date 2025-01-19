package service;



import Dao.Address;
import Dao.AddressDAO;
import Dao.impl.AddressDAOImpl;

import java.util.List;

public class AddressService {
    private AddressDAO addressDAO;

    public AddressService() {
        addressDAO = new AddressDAOImpl();
    }


    public void addAddress(Address address) {
        addressDAO.saveAddress(address);
    }


    public void updateAddress(Address address) {
        addressDAO.updateAddress(address);
    }


    public void deleteAddress(int addressId) {
        addressDAO.deleteAddress(addressId);
    }

    public List<Address> getAddressesByUserId(int userId) {
        return addressDAO.getAddressesByUserId(userId);
    }


    public Address getAddressById(int addressId) {
        return addressDAO.getAddressById(addressId);
    }
}