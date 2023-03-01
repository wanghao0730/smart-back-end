package com.ruoyi.smart.mapper;

import java.util.List;
import com.ruoyi.smart.domain.Store;

/**
 * 店铺信息Mapper接口
 * 
 * @author WangHao
 * @date 2022-10-24
 */
public interface StoreMapper 
{
    /**
     * 查询店铺信息
     * 
     * @param id 店铺信息主键
     * @return 店铺信息
     */
    public Store selectStoreById(Long id);

    /**
     * 查询店铺信息列表
     * 
     * @param store 店铺信息
     * @return 店铺信息集合
     */
    public List<Store> selectStoreList(Store store);

    /**
     * 新增店铺信息
     * 
     * @param store 店铺信息
     * @return 结果
     */
    public int insertStore(Store store);

    /**
     * 修改店铺信息
     * 
     * @param store 店铺信息
     * @return 结果
     */
    public int updateStore(Store store);

    /**
     * 删除店铺信息
     * 
     * @param id 店铺信息主键
     * @return 结果
     */
    public int deleteStoreById(Long id);

    /**
     * 批量删除店铺信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStoreByIds(Long[] ids);
}
