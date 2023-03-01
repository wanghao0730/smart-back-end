package com.ruoyi.smart.service;

import java.util.List;
import com.ruoyi.smart.domain.UserDeliver;

/**
 * 派送信息Service接口
 * 
 * @author WangHao
 * @date 2022-11-19
 */
public interface IUserDeliverService 
{
    /**
     * 根据订单id查询接单信息
     * @param id
     * @return
     */
    public UserDeliver selectUserDeliverByOrderId(String orderId);

    /**
     * 查询派送信息列表
     * 
     * @param userDeliver 派送信息
     * @return 派送信息集合
     */
    public List<UserDeliver> selectUserDeliverList(UserDeliver userDeliver);

    /**
     * 新增派送信息
     * 
     * @param userDeliver 派送信息
     * @return 结果
     */
    public int insertUserDeliver(UserDeliver userDeliver);

    /**
     * 修改派送信息
     * 
     * @param userDeliver 派送信息
     * @return 结果
     */
    public int updateUserDeliver(UserDeliver userDeliver);

    /**
     * 批量删除派送信息
     * 
     * @param ids 需要删除的派送信息主键集合
     * @return 结果
     */
    public int deleteUserDeliverByIds(Long[] ids);

    /**
     * 删除派送信息信息
     * 
     * @param id 派送信息主键
     * @return 结果
     */
    public int deleteUserDeliverById(Long id);
}
