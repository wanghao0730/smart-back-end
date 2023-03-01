package com.ruoyi.web.controller.front;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.smart.domain.UserOrder;
import com.ruoyi.smart.service.IUserOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单管理Controller
 * 
 * @author WangHao
 * @date 2022-10-27
 */
@RestController
@RequestMapping("/front/order")
public class UserOrderController extends BaseController
{
    @Autowired
    private IUserOrderService userOrderService;

    /**
     * 查询订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('front:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserOrder userOrder)
    {
        startPage();
        List<UserOrder> list = userOrderService.selectUserOrderList(userOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('front:order:export')")
    @Log(title = "订单管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserOrder userOrder)
    {
        List<UserOrder> list = userOrderService.selectUserOrderList(userOrder);
        ExcelUtil<UserOrder> util = new ExcelUtil<UserOrder>(UserOrder.class);
        util.exportExcel(response, list, "订单管理数据");
    }

    /**
     * 获取订单管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('front:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        UserOrder userOrderDetail = userOrderService.orderDetail(id); //包含关联关系
        return AjaxResult.success(userOrderDetail);
    }

    /**
     * 新增订单管理
     */
    @PreAuthorize("@ss.hasPermi('front:order:add')")
    @Log(title = "订单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserOrder userOrder)
    {
        return toAjax(userOrderService.insertUserOrder(userOrder));
    }

    /**
     * 修改订单管理
     */
    @PreAuthorize("@ss.hasPermi('front:order:edit')")
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserOrder userOrder)
    {
        return toAjax(userOrderService.updateUserOrder(userOrder));
    }

    /**
     * 删除订单管理
     */
    @PreAuthorize("@ss.hasPermi('front:order:remove')")
    @Log(title = "订单管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userOrderService.deleteUserOrderByIds(ids));
    }
}
