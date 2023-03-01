package com.ruoyi.smart.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "UserLoginParam",description = "用户登录参数")
public class UserLoginParam {

    @NotNull(message = "用户登录临时凭证")
    @ApiModelProperty(value = "登录code",required = true)
    private String code;

    @ApiModelProperty(value = "用户教务系统学号")
    private String username;

    @ApiModelProperty(value = "用户教务系统密码")
    private String password;

    @NotNull(message = "用户昵称")
    @ApiModelProperty(value = "用户昵称",required = true)
    private String nickName;

    @ApiModelProperty(value = "用户性别")
    private Integer gender;

    @ApiModelProperty(value = "用户头像")
    private String avatarUrl;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "区域")
    private String district;
}
