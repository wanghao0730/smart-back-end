package com.ruoyi.smart.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.framework.web.service.WxLoginUser;
import com.ruoyi.smart.domain.UserAddress;
import com.ruoyi.smart.service.IUserAddressService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "用户地址接口")
@RestController
@RequestMapping("/address")
public class WxUserAddressController extends BaseController {

    @Autowired
    private IUserAddressService userAddressService;

    /**
     * 获取微信用户缓存信息 线程安全的springsecurity上下文中获取欧
     *
     */
    public WxLoginUser getWxLoginUser() { return (WxLoginUser) SecurityUtils.getAuthentication().getPrincipal();}

    @GetMapping("/list")
    public AjaxResult addressList(UserAddress userAddress) {
        //从线程中获取当前用户
        WxLoginUser wxLoginUser = getWxLoginUser();
        userAddress.setUserId(wxLoginUser.getOpenid());
        List<UserAddress> addressList = userAddressService.selectAddressList(userAddress);
        return AjaxResult.success("请求成功",addressList);
    }

    @PostMapping("/add")
    public AjaxResult addAddress(@RequestBody UserAddress userAddress){
        //springsecurity中获取
        userAddress.setUserId(getWxLoginUser().getOpenid());
        //新增地址
        return  toAjax(userAddressService.insertAddress(userAddress));
    }

    @PostMapping("/update")
    public AjaxResult updateAddress(@RequestBody UserAddress userAddress) {
        userAddress.setUserId(getWxLoginUser().getOpenid());
        return  toAjax(userAddressService.updateAddress(userAddress));
    }

    @PutMapping("/setDefault/{id}")
    public AjaxResult updateDefault(@PathVariable("id") Long addressId) {
        return toAjax(userAddressService.updateDefault(addressId, getWxLoginUser().getOpenid()));
    }
}
