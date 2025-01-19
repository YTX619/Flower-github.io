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

    // 添加地址
    public void addAddress(Address address) {
        addressDAO.saveAddress(address);
    }

    // 更新地址
    public void updateAddress(Address address) {
        addressDAO.updateAddress(address);
    }

    // 删除地址
    public void deleteAddress(int addressId) {
        addressDAO.deleteAddress(addressId);
    }

    // 根据用户ID获取地址列表
    public List<Address> getAddressesByUserId(int userId) {
        return addressDAO.getAddressesByUserId(userId);
    }

    // 根据地址ID获取地址
    public Address getAddressById(int addressId) {
        return addressDAO.getAddressById(addressId);
    }
}