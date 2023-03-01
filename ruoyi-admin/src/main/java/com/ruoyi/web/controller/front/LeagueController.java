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
import com.ruoyi.smart.domain.League;
import com.ruoyi.smart.service.ILeagueService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学校社团Controller
 * 
 * @author wanghao
 * @date 2023-01-03
 */
@RestController
@RequestMapping("/front/league")
public class LeagueController extends BaseController
{
    @Autowired
    private ILeagueService leagueService;

    /**
     * 查询学校社团列表
     */
    @PreAuthorize("@ss.hasPermi('front:league:list')")
    @GetMapping("/list")
    public TableDataInfo list(League league)
    {
        startPage();
        List<League> list = leagueService.selectLeagueList(league);
        return getDataTable(list);
    }

    /**
     * 导出学校社团列表
     */
    @PreAuthorize("@ss.hasPermi('front:league:export')")
    @Log(title = "学校社团", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, League league)
    {
        List<League> list = leagueService.selectLeagueList(league);
        ExcelUtil<League> util = new ExcelUtil<League>(League.class);
        util.exportExcel(response, list, "学校社团数据");
    }

    /**
     * 获取学校社团详细信息
     */
    @PreAuthorize("@ss.hasPermi('front:league:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(leagueService.selectLeagueById(id));
    }

    /**
     * 新增学校社团
     */
    @PreAuthorize("@ss.hasPermi('front:league:add')")
    @Log(title = "学校社团", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody League league)
    {
        league.setCreateBy(getUsername());
        return toAjax(leagueService.insertLeague(league));
    }

    /**
     * 修改学校社团
     */
    @PreAuthorize("@ss.hasPermi('front:league:edit')")
    @Log(title = "学校社团", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody League league)
    {
        league.setUpdateBy(getUsername());
        return toAjax(leagueService.updateLeague(league));
    }

    /**
     * 删除学校社团
     */
    @PreAuthorize("@ss.hasPermi('front:league:remove')")
    @Log(title = "学校社团", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(leagueService.deleteLeagueByIds(ids));
    }
}
