package com.ruoyi.smart.service.impl;

import java.util.List;
import java.util.Map;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.smart.domain.UserDeliver;
import com.ruoyi.smart.mapper.UserDeliverMapper;
import com.ruoyi.smart.service.IUserDeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.smart.mapper.UserOrderMapper;
import com.ruoyi.smart.domain.UserOrder;
import com.ruoyi.smart.service.IUserOrderService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单管理Service业务层处理
 * 
 * @author WangHao
 * @date 2022-10-27
 */
@Service
public class UserOrderServiceImpl implements IUserOrderService 
{
    @Autowired
    private UserOrderMapper userOrderMapper;

    @Autowired
    private IUserDeliverService userDeliverMapper; //接单mapper

    /**
     * 查询用户的订单根据订单id查找
     * @param id
     * @return
     */
    @Override
    public UserOrder selectUserOrderById(String id)
    {
        return userOrderMapper.selectUserOrderById(id);
    }
    /**
     * 查询用户的订单数据 根据订单类型

     * @return
     */
    @Override
    public List<UserOrder> selectUserOrder(Map<String,Object> info) {
        return userOrderMapper.selectUserOrder(info);
    }
    /**
     * 查询订单管理列表
     * 
     * @param userOrder 订单管理
     * @return 订单管理
     */
    @Override
    public List<UserOrder> selectUserOrderList(UserOrder userOrder)
    {
        return userOrderMapper.selectUserOrderList(userOrder);
    }

    /**
     * 订单进行拼接查询
     * @param orderId
     * @return
     */
    @Override
    public UserOrder orderDetail(String orderId) {
        UserOrder userOrder = selectUserOrderById(orderId);
        if (StringUtils.isNull(userOrder.getId())) {
            throw new ServiceException("当前订单不存在");
        }
        UserDeliver userDeliver = userDeliverMapper.selectUserDeliverByOrderId(orderId);
        userOrder.setUserDeliver(userDeliver);
        return userOrder;
    }

    /**
     * 联表查询订单表与用户表的关系
     * @param userOrder
     * @return
     */
    @Override
    public List<UserOrder> selectOrderDeliveryList(UserOrder userOrder) {
        return userOrderMapper.selectOrderDeliveryList(userOrder);
    }

    /**
     * 查找派单者的关联数据信息
     */
    @Override
    public List<UserOrder> selectDeliveryOrderList(Map<String, Object> info) {
        return userOrderMapper.selectDeliveryOrderList(info);
    }

    /**
     * 新增订单管理
     * 
     * @param userOrder 订单管理
     * @return 结果
     */
    @Override
    public int insertUserOrder(UserOrder userOrder)
    {
        //生成用户订单id
        String orderId = IdUtil.fastSimpleUUID();
        userOrder.setOrderId(orderId);
        userOrder.setCreateTime(DateUtils.getNowDate());
        //订单状态设置为1待接单
        userOrder.setOrderStep(1L);
        return userOrderMapper.insertUserOrder(userOrder);
    }
    /**
     * 修改订单管理
     * 
     * @param userOrder 订单管理
     * @return 结果
     */
    @Override
    public int updateUserOrder(UserOrder userOrder)
    {
        return userOrderMapper.updateUserOrder(userOrder);
    }

    /**
     * 批量删除订单管理
     * 
     * @param ids 需要删除的订单管理主键
     * @return 结果
     */
    @Override
    public int deleteUserOrderByIds(Long[] ids)
    {
        return userOrderMapper.deleteUserOrderByIds(ids);
    }

    /**
     * 删除订单管理信息
     * 
     * @param id 订单管理主键
     * @return 结果
     */
    @Override
    public int deleteUserOrderById(Long id)
    {
        return userOrderMapper.deleteUserOrderById(id);
    }


    /**
     * 修改用户订单、接单者订单状态
     * @param param
     * @return
     */
    @Override
    public int updateOrderStep(Map<String, Object> param) {
        String type = (String) param.get("type"); //发起用户类型 1发起者 2派送者
        String orderId = (String)param.get("orderId"); // 订单id
        String  cancelRemark = (String) param.get("cancelRemark"); //订单取消所需备注
        Long id = (Long) param.get("id"); //派送单的主键id
        Long orderStep = (Long)param.get("orderStep"); //订单所需修改进度

        /**
         * 从map中提取的id值为派送表的id 因为在订单表中用户的订单order_id是唯一的 但在派送表不能只通过order_id进行区分修改状态(因为包含了取消的订单记录具有相同的值)所以id值接受派送单中的id值
         */
        //1是用户修改状态 2是接单者修改状态

        if ("1".equals(type)) {
            //如果用户的修改进度类型为取消 调用另外封装的取消方法判断 其他的封装对象正常修改
            if (orderStep == 0) {
                //这一步会判断用户是否具备取消订单条件
               return userCancel(orderId,cancelRemark,id);
            }
            //用户订单被接单后状态变为2待派送(有人接单) 3表示接单者已经取货 用户可以选择已完成表示订单完成
            else if(orderStep == 4) {
                //构造更改的对象
                UserOrder userOrder = new UserOrder();
                userOrder.setFinishedTime(DateUtils.getNowDate());
                userOrder.setOrderStep(4L);
                userOrder.setOrderId(orderId);
                userOrder.setUpdateTime(DateUtils.getNowDate());
                //调用更改方法
                return  updateUserOrder(userOrder);
            }
        }
        else if ("2".equals(type)) { //接单者状态改变
            //TODO 分析接单者取消订单
            if (orderStep == 0) { //修改订单表的状态变为1待接单 接单者信息的step状态变为0 以及添加取消原因
                UserOrder cancelObj = new UserOrder();
                cancelObj.setOrderId(orderId);
                cancelObj.setOrderStep(1L); //可以重新被接单
                int i = updateUserOrder(cancelObj);
                //TODO 设置派送订单为取消数据
                //这里改成了直接删除记录可能换成记录到操作记录表
                UserDeliver cancelDv = new UserDeliver();
                cancelDv.setId(id);
//                cancelDv.setOrderId(orderId);
//                cancelDv.setOrderStep(0L);
//                cancelDv.setCancelRemark(cancelRemark);
//                cancelDv.setCancelTime(DateUtils.getNowDate());
                int i1 = userDeliverMapper.deleteUserDeliverById(id);
                return i1;
            }
            //用户修改订单为已取货
            if (orderStep == 3) { //修改状态为已取货
                //修改用户订单状态和派送者状态
                //订单表和派送表不同的是订单表包含了唯一的订单order_id 派送表有派送用户取消的订单 所以不能只通过order_id区分 派送者修改状态加上主键id进行条件判断
                UserOrder uThree = new UserOrder();
                uThree.setOrderStep(3L);
                uThree.setUpdateTime(DateUtils.getNowDate());
                uThree.setOrderId(orderId);
                //更改状态
                int i = updateUserOrder(uThree);
                //更改派送状态
                UserDeliver userDeliver = new UserDeliver();
                userDeliver.setId(id); //修改派送表的时候要传递派送表主键id
                userDeliver.setOrderId(orderId);
                userDeliver.setOrderStep(3L);
                userDeliver.setUpdateTime(DateUtils.getNowDate());
                int uRes = userDeliverMapper.updateUserDeliver(userDeliver);
                return uRes;
            }
            else if (orderStep == 4) {
                //更改派送状态
                UserDeliver userDeliver = new UserDeliver();
                userDeliver.setId(id); //根据主键id和订单id判断
                userDeliver.setOrderId(orderId);
                userDeliver.setOrderStep(4L);
                userDeliver.setUpdateTime(DateUtils.getNowDate());
                int uRes = userDeliverMapper.updateUserDeliver(userDeliver);
                return uRes;
            }
        }
        return 0;
    }

    /**
     * 用户接单功能业务
     * @param userDeliver 用户接单对象
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public synchronized int orderTaking(UserDeliver userDeliver) {
        //订单id先查询当前订单是否已经被用户接单 这里在方法上加了synchronized锁
        UserOrder userOrder = selectUserOrderById(userDeliver.getOrderId());
        if (userOrder.getOrderStep() > 1) { //大于1表示当前订单以及被他人接单了
            throw new ServiceException("订单被抢走啦!请查看其它订单");
        }
        //判断发起订单用户与接单者是否同一个人
        if (userOrder.getUserId().equals(userDeliver.getDistributorId())) {
            throw new ServiceException("您不可以接自己的订单哟！");
        }
        /**
         * 1.根据用户传递的订单id从UserDeliver中取，根据id修改订单的状态
         */
        //构造订单对象传递给更新订单的方法
        UserOrder order = new UserOrder();
        order.setOrderId(userDeliver.getOrderId());
        order.setOrderStep(2L); //表示等待配送
        //更新订单
        int i = updateUserOrder(order);
        /**
         * 2. 新增接单数据
         */
        //设置接单时间
        userDeliver.setCreateTime(DateUtils.getNowDate());
        int deliverRes = userDeliverMapper.insertUserDeliver(userDeliver);
        return deliverRes;
    }

    /**
     *  用户取消订单
     * @param orderId
     * @param remark
     * @param id 派送订单主键id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int userCancel(String orderId,String remark,Long id) {
        //先判断当前用户的订单货物是否已经被接单者提取
        UserOrder orderStep = userOrderMapper.selectUserOrderById(orderId);
        if (orderStep.getOrderStep() >= 3) { //查看状态是否符合取消
            throw new ServiceException("派送用户已取货,无法取消订单");
        }
        /**
         * 修改订单状态 以及修改接单着状态内容
         */
        UserOrder userOrder = new UserOrder();
        userOrder.setOrderId(orderId);
        userOrder.setOrderStep(0L);
        userOrder.setCancelRemark(remark);
        userOrder.setCancelTime(DateUtils.getNowDate());
        //调用修改方法
        int i = userOrderMapper.updateUserOrder(userOrder);
        //如果当前订单没有被其他用户接受 也就是在等待接单的话直接返回(未接单)
        if (orderStep.getOrderStep() == 1) {
            return i;
        }
        //修改接单者表 写明取消原因
        UserDeliver userDeliver = new UserDeliver();
        userDeliver.setId(id); //派送表的主键id
        userDeliver.setOrderId(orderId);
        userDeliver.setOrderStep(0L);
        userDeliver.setCancelRemark("用户取消订单-" + remark);
        userDeliver.setCancelTime(DateUtils.getNowDate());
        int updateRes = userDeliverMapper.updateUserDeliver(userDeliver);
        return updateRes;
    }

}
