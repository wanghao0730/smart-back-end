package com.ruoyi.smart.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 订单管理对象 tb_user_order
 * 
 * @author WangHao
 * @date 2022-10-27
 */
@ApiModel
public class UserOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 订单类型 */
    @Excel(name = "订单类型")
    private Long type;

    /** 用户openid(发起订单用户) */
    @Excel(name = "用户openid(发起订单用户)")
    private String userId;

    //微信用户名称
    @Excel(name = "微信昵称")
    private String nickname;

    //用户头像
    @Excel(name = "用户头像")
    private String avatar;
    /** 订单id */
    @Excel(name = "订单id")
    private String orderId;

    /** 收货人昵称 */
    @Excel(name = "收货人昵称")
    private String receiveName;

    /** 收货人手机 */
    @Excel(name = "收货人手机")
    private String receivePhone;

    /** 收货地区 */
    @Excel(name = "收货地区")
    private String receiveProvince;

    /** 收货详情地址 */
    @Excel(name = "收货详情地址")
    private String receiveAddress;

    /** 取货地址详情 */
    @Excel(name = "取货地址详情")
    private String deliveryAddress;

    /** 代买订单费用 */
    @Excel(name = "代买订单费用")
    private BigDecimal orderAmount;

    /** 跑腿小费 */
//    @Pattern(regexp = "^((([1-9]{1}//d{0,9}))|([0]{1}))((//.(//d){2}))?$")
    @Excel(name = "跑腿小费")
    private BigDecimal tipFee;

    /** 1线下支付 2线上支付 */
    @Excel(name = "1线下支付 2线上支付")
    private Long payMode;

    /** 货物内容 */
    @Excel(name = "货物内容")
    private String orderDetailName;

    /** 订单备注信息 */
    @Excel(name = "订单备注信息")
    private String orderRemark;

    /** 完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date finishedTime;

    /** 预约时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    @Excel(name = "预约时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date appointmentTime;

    /** 货物信息 */
    @Excel(name = "货物信息")
    private String orderDetailCode;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private Long orderStep;

    /** 取消原因 */
    @Excel(name = "取消原因")
    private String cancelRemark;

    /** 取消时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "取消时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cancelTime;

    //订单关联接单信息
    @Excel(name = "接单用户")
    private UserDeliver userDeliver;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setOrderId(String orderId) 
    {
        this.orderId = orderId;
    }

    public String getOrderId() 
    {
        return orderId;
    }
    public void setReceiveName(String receiveName) 
    {
        this.receiveName = receiveName;
    }

    public String getReceiveName() 
    {
        return receiveName;
    }
    public void setReceivePhone(String receivePhone) 
    {
        this.receivePhone = receivePhone;
    }

    public String getReceivePhone() 
    {
        return receivePhone;
    }
    public void setReceiveProvince(String receiveProvince) 
    {
        this.receiveProvince = receiveProvince;
    }

    public String getReceiveProvince() 
    {
        return receiveProvince;
    }
    public void setReceiveAddress(String receiveAddress) 
    {
        this.receiveAddress = receiveAddress;
    }

    public String getReceiveAddress() 
    {
        return receiveAddress;
    }
    public void setDeliveryAddress(String deliveryAddress) 
    {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryAddress() 
    {
        return deliveryAddress;
    }
    public void setOrderAmount(BigDecimal orderAmount) 
    {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getOrderAmount() 
    {
        return orderAmount;
    }
    public void setTipFee(BigDecimal tipFee) 
    {
        this.tipFee = tipFee;
    }

    public BigDecimal getTipFee() 
    {
        return tipFee;
    }
    public void setPayMode(Long payMode) 
    {
        this.payMode = payMode;
    }

    public Long getPayMode() 
    {
        return payMode;
    }
    public void setOrderDetailName(String orderDetailName) 
    {
        this.orderDetailName = orderDetailName;
    }

    public String getOrderDetailName() 
    {
        return orderDetailName;
    }
    public void setOrderRemark(String orderRemark) 
    {
        this.orderRemark = orderRemark;
    }

    public String getOrderRemark() 
    {
        return orderRemark;
    }
    public void setFinishedTime(Date finishedTime) 
    {
        this.finishedTime = finishedTime;
    }

    public Date getFinishedTime() 
    {
        return finishedTime;
    }
    public void setAppointmentTime(Date appointmentTime) 
    {
        this.appointmentTime = appointmentTime;
    }

    public Date getAppointmentTime() 
    {
        return appointmentTime;
    }
    public void setOrderDetailCode(String orderDetailCode) 
    {
        this.orderDetailCode = orderDetailCode;
    }

    public String getOrderDetailCode() 
    {
        return orderDetailCode;
    }
    public void setOrderStep(Long orderStep) 
    {
        this.orderStep = orderStep;
    }

    public Long getOrderStep() 
    {
        return orderStep;
    }
    public void setCancelRemark(String cancelRemark) 
    {
        this.cancelRemark = cancelRemark;
    }

    public String getCancelRemark() 
    {
        return cancelRemark;
    }
    public void setCancelTime(Date cancelTime) 
    {
        this.cancelTime = cancelTime;
    }

    public Date getCancelTime() 
    {
        return cancelTime;
    }

    public UserDeliver getUserDeliver() {
        return userDeliver;
    }

    public void setUserDeliver(UserDeliver userDeliver) {
        this.userDeliver = userDeliver;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    //setnull方法用于将一些隐私数据置空 这些数据接单后才被显示
    public void setNull() {
        this.userId = null;
        this.receiveName = null;
        this.receivePhone = null;
        this.orderDetailCode = null;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("userId", getUserId())
            .append("orderId", getOrderId())
            .append("receiveName", getReceiveName())
            .append("receivePhone", getReceivePhone())
            .append("receiveProvince", getReceiveProvince())
            .append("receiveAddress", getReceiveAddress())
            .append("deliveryAddress", getDeliveryAddress())
            .append("orderAmount", getOrderAmount())
            .append("tipFee", getTipFee())
            .append("payMode", getPayMode())
            .append("orderDetailName", getOrderDetailName())
            .append("orderRemark", getOrderRemark())
            .append("finishedTime", getFinishedTime())
            .append("appointmentTime", getAppointmentTime())
            .append("orderDetailCode", getOrderDetailCode())
            .append("orderStep", getOrderStep())
            .append("cancelRemark", getCancelRemark())
            .append("cancelTime", getCancelTime())
            .append("nickname",getNickname())
            .append("avatar",getAvatar())
            .append("userDeliver",getUserDeliver())
            .toString();
    }
}
