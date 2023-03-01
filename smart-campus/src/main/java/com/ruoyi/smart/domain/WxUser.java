package com.ruoyi.smart.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 微信用户对象 tb_user
 * 
 * @author wanghao
 * @date 2022-10-16
 */
public class WxUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户主键 */
    private Long id;

    /** 微信用户openid */
    @Excel(name = "微信用户openid")
    private String openId;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String nickname;

    /** 用户名 */
    @Excel(name = "用户名")
    private String name;

    /** 0男 1女 */
    @Excel(name = "0男 1女")
    private String sex;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 区域 */
    @Excel(name = "区域")
    private String district;

    /** 微信用户头像 */
    @Excel(name = "微信用户头像")
    private String photo;

    /** 用户手机 */
    @Excel(name = "用户手机")
    private String phone;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String email;

    /** 0表示正常 1表示禁用(发言等) */
    @Excel(name = "0表示正常 1表示禁用(发言等)")
    private String isDisable;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOpenId(String openId) 
    {
        this.openId = openId;
    }

    public String getOpenId() 
    {
        return openId;
    }
    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setDistrict(String district) 
    {
        this.district = district;
    }

    public String getDistrict() 
    {
        return district;
    }
    public void setPhoto(String photo) 
    {
        this.photo = photo;
    }

    public String getPhoto() 
    {
        return photo;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setIsDisable(String isDisable) 
    {
        this.isDisable = isDisable;
    }

    public String getIsDisable() 
    {
        return isDisable;
    }

    //清除id和openid返回数据不显示
    public void setNull() {
        this.openId = null;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("openId", getOpenId())
            .append("nickname", getNickname())
            .append("name", getName())
            .append("sex", getSex())
            .append("province", getProvince())
            .append("city", getCity())
            .append("district", getDistrict())
            .append("photo", getPhoto())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("isDisable", getIsDisable())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
