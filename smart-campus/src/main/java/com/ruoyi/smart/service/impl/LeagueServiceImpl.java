package com.ruoyi.smart.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.smart.mapper.LeagueMapper;
import com.ruoyi.smart.domain.League;
import com.ruoyi.smart.service.ILeagueService;

/**
 * 学校社团Service业务层处理
 * 
 * @author wanghao
 * @date 2023-01-03
 */
@Service
public class LeagueServiceImpl implements ILeagueService 
{
    @Autowired
    private LeagueMapper leagueMapper;

    /**
     * 查询学校社团
     * 
     * @param id 学校社团主键
     * @return 学校社团
     */
    @Override
    public League selectLeagueById(Long id)
    {
        return leagueMapper.selectLeagueById(id);
    }

    /**
     * 查询学校社团列表
     * 
     * @param league 学校社团
     * @return 学校社团
     */
    @Override
    public List<League> selectLeagueList(League league)
    {
        return leagueMapper.selectLeagueList(league);
    }

    /**
     * 新增学校社团
     * 
     * @param league 学校社团
     * @return 结果
     */
    @Override
    public int insertLeague(League league)
    {
        league.setCreateTime(DateUtils.getNowDate());
        return leagueMapper.insertLeague(league);
    }

    /**
     * 修改学校社团
     * 
     * @param league 学校社团
     * @return 结果
     */
    @Override
    public int updateLeague(League league)
    {
        league.setUpdateTime(DateUtils.getNowDate());
        return leagueMapper.updateLeague(league);
    }

    /**
     * 批量删除学校社团
     * 
     * @param ids 需要删除的学校社团主键
     * @return 结果
     */
    @Override
    public int deleteLeagueByIds(Long[] ids)
    {
        return leagueMapper.deleteLeagueByIds(ids);
    }

    /**
     * 删除学校社团信息
     * 
     * @param id 学校社团主键
     * @return 结果
     */
    @Override
    public int deleteLeagueById(Long id)
    {
        return leagueMapper.deleteLeagueById(id);
    }
}
