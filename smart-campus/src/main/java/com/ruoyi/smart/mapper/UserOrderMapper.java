package com.ruoyi.smart.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.smart.domain.UserOrder;

/**
 * 订单管理Mapper接口
 * 
 * @author WangHao
 * @date 2022-10-27
 */
public interface UserOrderMapper 
{
    /**
     * 查询订单管理
     * 
     * @param id 用
     * @return 订单管理
     */
    public UserOrder selectUserOrderById(String id);

    /**
     * 查询用户的订单数据 根据订单类型
     * @return
     */
    public List<UserOrder> selectUserOrder(Map<String,Object> info);

    /**
     * 查询订单列表这个只包含了订单的信息 不包含派送信息
     * 
     * @param userOrder 订单管理
     * @return 订单管理集合
     */
    public List<UserOrder> selectUserOrderList(UserOrder userOrder);


    /**
     * 联表查询订单表与用户表的关系
     * @param userOrder
     * @return
     */
    public List<UserOrder> selectOrderDeliveryList(UserOrder userOrder);


    /**
     * 查找派单者的关联数据信息
     */
    public List<UserOrder> selectDeliveryOrderList(Map<String,Object> info);
    /**
     * 新增订单管理
     * 
     * @param userOrder 订单管理
     * @return 结果
     */
    public int insertUserOrder(UserOrder userOrder);

    /**
     * 修改订单管理
     * 
     * @param userOrder 订单管理
     * @return 结果
     */
    public int updateUserOrder(UserOrder userOrder);

    /**
     * 删除订单管理
     * 
     * @param id 订单管理主键
     * @return 结果
     */
    public int deleteUserOrderById(Long id);

    /**
     * 批量删除订单管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserOrderByIds(Long[] ids);
}
