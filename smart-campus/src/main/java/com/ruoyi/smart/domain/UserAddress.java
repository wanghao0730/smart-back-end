package com.ruoyi.smart.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户地址实体
 */
public class UserAddress extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 地址主键 */
    private Long id;

    /** 用户openid */
    private String userId;

    /** 收货人姓名 */
    private String receiveName;

    /** 收货人手机号码 */
    private String receivePhone;

    /** 收货人地区 */
    private String receiveProvinceCityArea;

    /** 收货人地址 */
    private String receiveAddress;

    /** 收货人门牌号 */
    private String doorNumber;

    /** 用户地址 1不是默认 2默认 */
    private Integer isDefault;

    /** 逻辑删除 1正常 2删除 */
    private Integer delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getReceiveProvinceCityArea() {
        return receiveProvinceCityArea;
    }

    public void setReceiveProvinceCityArea(String receiveProvinceCityArea) {
        this.receiveProvinceCityArea = receiveProvinceCityArea;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
