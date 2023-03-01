package com.ruoyi.smart.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 派送信息对象 tb_user_deliver
 * 
 * @author WangHao
 * @date 2022-10-27
 */
public class UserDeliver extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户订单id */
    @Excel(name = "用户订单id")
    private String orderId;

    //微信用户名称
    @Excel(name = "微信昵称")
    private String nickname;

    //用户头像
    @Excel(name = "用户头像")
    private String avatar;
    /** 派送用户openid */
    @Excel(name = "派送用户openid")
    private String distributorId;

    /** 接单用户名 */
    @Excel(name = "接单用户名")
    private String distributorName;

    /** 接单手机号码 */
    @Excel(name = "接单手机号码")
    private String distributorPhone;

    /** 取消原因 */
    @Excel(name = "取消原因")
    private String cancelRemark;

    /** 取消时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "取消时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cancelTime;

    /** 派送状态 */
    @Excel(name = "派送状态")
    private Long orderStep;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderId(String orderId) 
    {
        this.orderId = orderId;
    }

    public String getOrderId() 
    {
        return orderId;
    }
    public void setDistributorId(String distributorId) 
    {
        this.distributorId = distributorId;
    }

    public String getDistributorId() 
    {
        return distributorId;
    }
    public void setDistributorName(String distributorName) 
    {
        this.distributorName = distributorName;
    }

    public String getDistributorName() 
    {
        return distributorName;
    }
    public void setDistributorPhone(String distributorPhone) 
    {
        this.distributorPhone = distributorPhone;
    }

    public String getDistributorPhone() 
    {
        return distributorPhone;
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
    public void setOrderStep(Long orderStep) 
    {
        this.orderStep = orderStep;
    }

    public Long getOrderStep() 
    {
        return orderStep;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("distributorId", getDistributorId())
            .append("distributorName", getDistributorName())
            .append("distributorPhone", getDistributorPhone())
            .append("cancelRemark", getCancelRemark())
            .append("cancelTime", getCancelTime())
            .append("orderStep", getOrderStep())
            .append("createTime", getCreateTime())
            .append("nickname",getNickname())
            .append("avatar",getAvatar())
            .toString();
    }
}
