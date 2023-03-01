package com.ruoyi.smart.service;

import java.util.List;

import com.ruoyi.smart.domain.WxUser;

/**
 * 微信用户Service接口
 * 
 * @author wanghao
 * @date 2022-10-16
 */
public interface IWxUserService 
{
    /**
     * 查询微信用户
     * 
     * @param id 微信用户主键
     * @return 微信用户
     */
    public WxUser selectWxUserById(Long id);

    /**
     * 查询用户根据用户的openId进行查询
     * @param openId
     * @return
     */
    public WxUser selectWxUserByOpenId(String openId);

    /**
     * 查询微信用户列表
     * 
     * @param wxUser 微信用户
     * @return 微信用户集合
     */
    public List<WxUser> selectWxUserList(WxUser wxUser);

    /**
     * 新增微信用户
     * 
     * @param wxUser 微信用户
     * @return 结果
     */
    public int insertWxUser(WxUser wxUser);

    /**
     * 修改微信用户
     * 
     * @param wxUser 微信用户
     * @return 结果
     */
    public int updateWxUser(WxUser wxUser);

    /**
     * 批量删除微信用户
     * 
     * @param ids 需要删除的微信用户主键集合
     * @return 结果
     */
    public int deleteWxUserByIds(Long[] ids);

    /**
     * 删除微信用户信息
     * 
     * @param id 微信用户主键
     * @return 结果
     */
    public int deleteWxUserById(Long id);



}
