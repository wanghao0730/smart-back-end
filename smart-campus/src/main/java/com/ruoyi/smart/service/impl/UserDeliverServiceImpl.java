package com.ruoyi.smart.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.smart.mapper.UserDeliverMapper;
import com.ruoyi.smart.domain.UserDeliver;
import com.ruoyi.smart.service.IUserDeliverService;

/**
 * 派送信息Service业务层处理
 * 
 * @author WangHao
 * @date 2022-11-19
 */
@Service
public class UserDeliverServiceImpl implements IUserDeliverService 
{
    @Autowired
    private UserDeliverMapper userDeliverMapper;


    @Override
    public UserDeliver selectUserDeliverByOrderId(String orderId)
    {
        return userDeliverMapper.selectUserDeliverByOrderId(orderId);
    }

    /**
     * 查询派送信息列表
     * 
     * @param userDeliver 派送信息
     * @return 派送信息
     */
    @Override
    public List<UserDeliver> selectUserDeliverList(UserDeliver userDeliver)
    {
        return userDeliverMapper.selectUserDeliverList(userDeliver);
    }

    /**
     * 新增派送信息
     * 
     * @param userDeliver 派送信息
     * @return 结果
     */
    @Override
    public int insertUserDeliver(UserDeliver userDeliver)
    {
        userDeliver.setCreateTime(DateUtils.getNowDate());
        return userDeliverMapper.insertUserDeliver(userDeliver);
    }

    /**
     * 修改派送信息
     * 
     * @param userDeliver 派送信息
     * @return 结果
     */
    @Override
    public int updateUserDeliver(UserDeliver userDeliver)
    {
        return userDeliverMapper.updateUserDeliver(userDeliver);
    }

    /**
     * 批量删除派送信息
     * 
     * @param ids 需要删除的派送信息主键
     * @return 结果
     */
    @Override
    public int deleteUserDeliverByIds(Long[] ids)
    {
        return userDeliverMapper.deleteUserDeliverByIds(ids);
    }

    /**
     * 删除派送信息信息
     * 
     * @param id 派送信息主键
     * @return 结果
     */
    @Override
    public int deleteUserDeliverById(Long id)
    {
        return userDeliverMapper.deleteUserDeliverById(id);
    }
}
