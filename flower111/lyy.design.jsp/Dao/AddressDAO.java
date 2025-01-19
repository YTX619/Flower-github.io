package Dao;

import java.util.List;

public interface AddressDAO {
    // 保存地址
    void saveAddress(Address address);

    // 更新地址
    void updateAddress(Address address);

    // 删除地址
    void deleteAddress(int addressId);

    // 根据用户ID获取地址列表
    List<Address> getAddressesByUserId(int userId);

    // 根据地址ID获取地址
    Address getAddressById(int addressId);
}