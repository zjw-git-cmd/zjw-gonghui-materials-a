package com.gh.service.material.service;

import com.gh.db.PageUtils;
import com.gh.db.Query;
import com.gh.pojo.entity.material.MaterialBrand;
import com.gh.pojo.vo.material.MaterialBrandVo;
import com.gh.service.common.utils.PageQuery;

import java.util.List;
import java.util.Map;

/**
 *
 * <b>辅材品牌服务接口</b><br/>
 *
 * @author Seal
 * @time 2018年4月8日 下午3:41:30
 */
public interface MaterialBrandService {

    //分页查询
    PageUtils<MaterialBrand> queryBrandList(PageQuery<MaterialBrandVo> reqVo);


    /**
     * 新增品牌
     * @param materialBrand
     */
    void saveMaterialBrand(MaterialBrand materialBrand);

    /**
     * 更新品牌
     * @param materialBrandVo
     */
    void updateMaterialBrand(MaterialBrandVo materialBrandVo);


    //=========================以下是未使用的方法=======================================

    /**
     * 根据ID获取品牌
     * @param materialBrandId
     * @return MaterialBrand
     */
    MaterialBrand getMaterialBrand(Long materialBrandId);








    /**
     * 更新品牌
     * @param materialBrand
     */
    void updateMaterialBrand(MaterialBrand materialBrand);





    /**
     * 更新品牌
     * @param materialBrandId
     * @param brandState
     * @param brandName
     * @param sort
     * @param optUserId
     * @param optUserName
     */
    /*void updateMaterialBrand(Long materialBrandId, Integer brandState, String brandName,
                             Integer sort,Integer recordType, Long optUserId, String optUserName);*/

    /**
     * 获取所有品牌
     * @return List<MaterialBrand>
     */
    List<MaterialBrand> findAllBrand();

    /**
     * 根据名称获取辅材品牌
     * @param brandName
     * @return MaterialBrand
     */
    MaterialBrand findByMaterialBrandName(String brandName);


    List<MaterialBrand> queryBrandList(Query query);

    List<MaterialBrand> queryBrandList(MaterialBrand materialBrand);







    int queryTotal(Query query);

    String countBrandState(MaterialBrand reqVo);

}
