package com.ruoyi.smart.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.service.WxLoginUser;
import com.ruoyi.smart.domain.UserDeliver;
import com.ruoyi.smart.domain.UserOrder;
import com.ruoyi.smart.param.OrderStepChangeParam;
import com.ruoyi.smart.service.IUserOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(tags = "用户订单接口")
@RestController
@RequestMapping("/order")
public class WxUserOrderController extends BaseController {

    @Autowired
    private IUserOrderService orderService;

    /**
     * 获取微信用户缓存信息 线程安全的springsecurity上下文中获取欧
     *
     */
    public WxLoginUser getWxLoginUser() { return (WxLoginUser) SecurityUtils.getAuthentication().getPrincipal();}

    /**
     * 订单查询
     * @param userOrder
     * @return
     */
    @ApiOperation("订单列表")
    @GetMapping("/list")
    public TableDataInfo list(UserOrder userOrder) {
        startPage();
        List<UserOrder> userOrders = orderService.selectUserOrderList(userOrder);
        //处理隐私数据
        for (UserOrder order : userOrders) {
            order.setNull();
        }
        return getDataTable(userOrders);
    }

    /**
     *  用户订单列表显示用户和派送者的关联信息
     * @return
     */
    @ApiOperation("订单列表")
    @GetMapping("/userOrder")
    public TableDataInfo orderList(UserOrder userOrder) {
        userOrder.setUserId(getWxLoginUser().getOpenid()); //根据用户的openid查询指定用户数据
        startPage();
        List<UserOrder> userOrders = orderService.selectUserOrderList(userOrder);
        return getDataTable(userOrders);
    }

    /**
     * 查看订单详情
     * @param orderId
     * @return
     */
    @ApiOperation("订单详情")
    @GetMapping("/userOrder/{orderId}")
    public AjaxResult orderDetail(@NotNull(message = "订单ID") @PathVariable("orderId") String orderId) {
        //在service里面完成订单的拼接
        UserOrder userOrder = orderService.orderDetail(orderId);
        userOrder.setNull(); //将隐私数据置空
        return AjaxResult.success(userOrder);
    }

    /**
     * 接单用户数据详情
     * @param info
     * @return
     */
    @ApiOperation("派单用户详情")
    @GetMapping("/deliverOrder")
    public TableDataInfo deliverOrder(@RequestParam Map<String,Object> info) {
        info.put("userId", getWxLoginUser().getOpenid());
        startPage();
        List<UserOrder> userOrders = orderService.selectDeliveryOrderList(info);
        return getDataTable(userOrders);
    }

    /**
     * 新增用户订单数据
     * @param userOrder
     * @return
     */
    @ApiOperation("新增订单")
    @PostMapping("/add")
    public AjaxResult addOrder(@RequestBody UserOrder userOrder) {
        WxLoginUser wxLoginUser = getWxLoginUser();
        userOrder.setUserId(wxLoginUser.getOpenid());
        return toAjax(orderService.insertUserOrder(userOrder));
    }

    /**
     * 用户接单控制层
     * @param userDeliver 接单对象数据
     * @return
     */
    @ApiOperation("用户接单")
    @PostMapping("/taking")
    public AjaxResult orderTaking(@RequestBody UserDeliver userDeliver) {
        //接单者id从security获取
        userDeliver.setDistributorId(getWxLoginUser().getOpenid());
        //调用接单的方法
        int i = orderService.orderTaking(userDeliver);
        return toAjax(i);
    }

    /**
     * 订单状态修改
     * @param param
     * @return
     */
    @ApiOperation("订单状态变化")
    @PostMapping("/updateStep")
    public AjaxResult updateStep(@Validated @RequestBody OrderStepChangeParam param) {
        Map<String,Object> info = new HashMap<>();
        info.put("id",param.getId());
        info.put("type",param.getType());
        info.put("orderId",param.getOrderId());
        info.put("orderStep",param.getOrderStep());
        info.put("cancelRemark",param.getCancelRemark());
        return toAjax(orderService.updateOrderStep(info));
    }
}
