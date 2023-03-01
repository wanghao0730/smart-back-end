package com.ruoyi.smart.controller;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.smart.domain.League;
import com.ruoyi.smart.service.ILeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 学校社团Controller
 * 
 * @author wanghao
 * @date 2023-01-03
 */
@RestController
@RequestMapping("/league")
@Anonymous
public class WxLeagueController extends BaseController
{
    @Autowired
    private ILeagueService leagueService;

    /**
     * 查询学校社团列表
     */
    @GetMapping("/list")
    public TableDataInfo list(League league)
    {
        startPage();
        List<League> list = leagueService.selectLeagueList(league);
        return getDataTable(list);
    }


    /**
     * 获取学校社团详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(leagueService.selectLeagueById(id));
    }

}
