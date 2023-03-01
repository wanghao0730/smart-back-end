package com.ruoyi.smart.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "订单状态变化")
public class OrderStepChangeParam {

    @NotNull(message = "操作用户类型")
    @ApiModelProperty(name = "type",value = "usertype",example = "1用户2接单者")
    private String type;

    @NotNull(message = "派送单主键")
    private Long id;

    @NotNull(message = "订单编号")
    private String orderId;

    @NotNull(message = "订单状态")
    private Long orderStep;

    //备注信息
    private String cancelRemark;
}
