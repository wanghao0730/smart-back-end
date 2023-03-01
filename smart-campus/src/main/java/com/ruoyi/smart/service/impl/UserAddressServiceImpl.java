package com.ruoyi.smart.service.impl;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.smart.domain.UserAddress;
import com.ruoyi.smart.mapper.UserAddressMapper;
import com.ruoyi.smart.service.IUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户地址业务层
 */
@Service
public class UserAddressServiceImpl implements IUserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;


    @Override
    public UserAddress selectAddressById(Long id) {
        return userAddressMapper.selectAddressById(id);
    }

    //查询用户地址
    @Override
    public List<UserAddress> selectAddressList(UserAddress userAddress) {
        return userAddressMapper.selectAddressList(userAddress);
    }

    //新增用户地址
    @Override
    public int insertAddress(UserAddress userAddress) {
        userAddress.setCreateTime(DateUtils.getNowDate());
        return userAddressMapper.insertAddress(userAddress);
    }

    //更新用户地址
    @Override
    public int updateAddress(UserAddress userAddress) {
        userAddress.setUpdateTime(DateUtils.getNowDate());
        return userAddressMapper.updateAddress(userAddress);
    }

    /**
     *  更新用户地址
     * @param id 地址id
     * @param openId 用户openid
     * @return
     */
    @Override
    public int updateDefault(Long id,String openId) {
        //查询用户地址是否存在
        UserAddress selectAddress = selectAddressById(id);
        if (StringUtils.isNull(selectAddress)) {//抛出业务异常
            throw new ServiceException("用户地址不存在");
        }
        //修改当前用户地址为非默认
        int i = userAddressMapper.updateNoDefaultByOpenId(openId);
        //修改当前数据为默认
        int uRes = userAddressMapper.updateDefaultById(id);
        return uRes;
    }
}
