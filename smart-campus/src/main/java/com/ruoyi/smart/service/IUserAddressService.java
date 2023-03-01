package com.ruoyi.smart.service;

import com.ruoyi.smart.domain.UserAddress;

import java.util.List;

public interface IUserAddressService {
    /**
     * 根据主键id查询用户的地址
     */
    public UserAddress selectAddressById(Long id);

    /**
     * 查询用户的地址详情
     * @param userAddress
     * @return
     */
    public List<UserAddress> selectAddressList(UserAddress userAddress);


    /**
     * 新增用户地址
     * @param userAddress
     * @return
     */
    public int insertAddress(UserAddress userAddress);


    /**
     * 更新用户地址
     * @param userAddress
     * @return
     */
    public int updateAddress(UserAddress userAddress);

    /**
     * 更新用户默认地址
     * @param id 地址id
     * @param openId 用户openid
     * @return
     */
    public int updateDefault(Long id,String openId);
}
