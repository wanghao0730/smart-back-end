package com.ruoyi.smart.mapper;

import com.ruoyi.smart.domain.UserAddress;

import java.util.List;

/**
 * 用户地址接口
 * @author WangHao
 */
public interface UserAddressMapper {

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
     * 设置用户的默认地址
     * @param id 地址主键值
     * @return
     */
    public int updateDefaultById(Long id);


    /**
     * 修改用户的所有地址为非默认地址
     * @param userId 用户唯一openid
     * @return
     */
    public int updateNoDefaultByOpenId(String userId);
}
