package com.ruoyi.smart.controller;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.service.WxLoginUser;
import com.ruoyi.smart.domain.Store;
import com.ruoyi.smart.service.IStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Api(tags = "小程序店铺接口")
@RestController
@RequestMapping("/store")
public class WxStoreController extends BaseController {

    @Autowired
    private IStoreService storeService;

    @GetMapping("/list")
    @ApiOperation("店铺列表")
    @Anonymous //放行接口
    public TableDataInfo storeList(Store store) {
        //自动封装分页
        startPage();
        //根据这个store对象进行各种条件查询
        List<Store> stores = storeService.selectStoreList(store);
        return getDataTable(stores);
    }
}
