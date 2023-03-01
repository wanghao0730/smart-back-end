package com.ruoyi.smart.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学校社团对象 tb_league_info
 * 
 * @author wanghao
 * @date 2023-01-03
 */
public class League extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 字典类型 */
    @Excel(name = "字典类型")
    private String type;

    /** 协会名称 */
    @Excel(name = "协会名称")
    private String linkman;

    /** 协会负责人 */
    @Excel(name = "协会负责人")
    private String president;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String concat;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 社团描述 */
    @Excel(name = "社团描述")
    private String description;

    /** 封面图片 */
    @Excel(name = "封面图片")
    private String cover;

    /** 多张图片 */
    @Excel(name = "多张图片")
    private String detail;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setLinkman(String linkman) 
    {
        this.linkman = linkman;
    }

    public String getLinkman() 
    {
        return linkman;
    }
    public void setPresident(String president) 
    {
        this.president = president;
    }

    public String getPresident() 
    {
        return president;
    }
    public void setConcat(String concat) 
    {
        this.concat = concat;
    }

    public String getConcat() 
    {
        return concat;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setCover(String cover) 
    {
        this.cover = cover;
    }

    public String getCover() 
    {
        return cover;
    }
    public void setDetail(String detail) 
    {
        this.detail = detail;
    }

    public String getDetail() 
    {
        return detail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("linkman", getLinkman())
            .append("president", getPresident())
            .append("concat", getConcat())
            .append("email", getEmail())
            .append("description", getDescription())
            .append("cover", getCover())
            .append("detail", getDetail())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
