package Dao;

import java.util.List;

public interface AddressDAO {

    void saveAddress(Address address);


    void updateAddress(Address address);


    void deleteAddress(int addressId);


    List<Address> getAddressesByUserId(int userId);


    Address getAddressById(int addressId);
}