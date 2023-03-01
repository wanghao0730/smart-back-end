package com.ruoyi.smart.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 店铺信息对象 tb_store
 * 
 * @author WangHao
 * @date 2022-10-24
 */
public class Store extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String storeName;

    /** 描述信息 */
    @Excel(name = "描述信息")
    private String storeDesc;

    /** 位置详情 */
    @Excel(name = "位置详情")
    private String storeDetailAddress;

    /** 图片 */
    @Excel(name = "图片")
    private String storeImage;

    /** 经度 */
    @Excel(name = "经度")
    private BigDecimal longitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private BigDecimal latitude;

    /** 删除逻辑 */
    private String delFlag;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStoreName(String storeName) 
    {
        this.storeName = storeName;
    }

    public String getStoreName() 
    {
        return storeName;
    }
    public void setStoreDesc(String storeDesc) 
    {
        this.storeDesc = storeDesc;
    }

    public String getStoreDesc() 
    {
        return storeDesc;
    }
    public void setStoreDetailAddress(String storeDetailAddress) 
    {
        this.storeDetailAddress = storeDetailAddress;
    }

    public String getStoreDetailAddress() 
    {
        return storeDetailAddress;
    }
    public void setStoreImage(String storeImage) 
    {
        this.storeImage = storeImage;
    }

    public String getStoreImage() 
    {
        return storeImage;
    }
    public void setLongitude(BigDecimal longitude) 
    {
        this.longitude = longitude;
    }

    public BigDecimal getLongitude() 
    {
        return longitude;
    }
    public void setLatitude(BigDecimal latitude) 
    {
        this.latitude = latitude;
    }

    public BigDecimal getLatitude() 
    {
        return latitude;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("storeName", getStoreName())
            .append("storeDesc", getStoreDesc())
            .append("storeDetailAddress", getStoreDetailAddress())
            .append("storeImage", getStoreImage())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("delFlag", getDelFlag())
            .append("sort", getSort())
            .append("remark", getRemark())
            .toString();
    }
}
