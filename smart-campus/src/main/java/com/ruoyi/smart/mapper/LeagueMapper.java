package com.ruoyi.smart.mapper;

import java.util.List;
import com.ruoyi.smart.domain.League;

/**
 * 学校社团Mapper接口
 * 
 * @author wanghao
 * @date 2023-01-03
 */
public interface LeagueMapper 
{
    /**
     * 查询学校社团
     * 
     * @param id 学校社团主键
     * @return 学校社团
     */
    public League selectLeagueById(Long id);

    /**
     * 查询学校社团列表
     * 
     * @param league 学校社团
     * @return 学校社团集合
     */
    public List<League> selectLeagueList(League league);

    /**
     * 新增学校社团
     * 
     * @param league 学校社团
     * @return 结果
     */
    public int insertLeague(League league);

    /**
     * 修改学校社团
     * 
     * @param league 学校社团
     * @return 结果
     */
    public int updateLeague(League league);

    /**
     * 删除学校社团
     * 
     * @param id 学校社团主键
     * @return 结果
     */
    public int deleteLeagueById(Long id);

    /**
     * 批量删除学校社团
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLeagueByIds(Long[] ids);
}
