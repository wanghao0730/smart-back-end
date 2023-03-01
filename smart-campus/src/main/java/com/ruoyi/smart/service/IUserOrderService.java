package com.ruoyi.smart.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.smart.domain.UserDeliver;
import com.ruoyi.smart.domain.UserOrder;
import org.springframework.security.core.userdetails.User;

/**
 * 订单管理Service接口
 * 
 * @author WangHao
 * @date 2022-10-27
 */
public interface IUserOrderService 
{
    /**
     * 查询用户的订单根据订单id查找
     * @param id
     * @return
     */

    public UserOrder selectUserOrderById(String id);


    /**
     * 查询用户的订单数据 根据订单类型
     * @return
     */
    public List<UserOrder> selectUserOrder(Map<String,Object> info);


    /**
     * 根据条件查询订单但不包含条件查询 查找详情的时候拿订单的id去查找详情接口
     * 
     * @param userOrder 订单管理
     * @return 订单管理集合
     */
    public List<UserOrder> selectUserOrderList(UserOrder userOrder);

    /**
     * 这个接口用于调用订单查询方法以及调用deliver的接口
     * @param orderId
     * @return
     */
    public UserOrder orderDetail(String orderId);
    /**
     * 联表查询订单表与用户表的关系(包含关联关系的)
     * @param userOrder
     * @return
     */
    public List<UserOrder> selectOrderDeliveryList(UserOrder userOrder);

    /**
     * 查找派单者的关联数据信息(包含关联关系)
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
     * 批量删除订单管理
     * 
     * @param ids 需要删除的订单管理主键集合
     * @return 结果
     */
    public int deleteUserOrderByIds(Long[] ids);

    /**
     * 删除订单管理信息
     * 
     * @param id 订单管理主键
     * @return 结果
     */
    public int deleteUserOrderById(Long id);

    /**
     * 接单方法
     * @param userDeliver
     * @return
     */
    public  int orderTaking(UserDeliver userDeliver);

    /**
     * 用户取消订单
     * @param orderId
     * @param remark
     * @param id
     * @return
     */
    public int userCancel(String orderId,String remark,Long id);


    /**
     * 修改用户、接单者订单状态
     */
    public int updateOrderStep(Map<String,Object> param);
}
