package com.gh.service.material.dao;

import com.gh.pojo.entity.material.MaterialBrand;
import com.gh.service.common.dao.BaseDao;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * <b>辅材品牌</b><br/>
 *
 * @author Seal
 * @time 2018年4月8日 下午3:19:05
 */
@Mapper
public interface MaterialBrandDao extends BaseDao<MaterialBrand> {


	//条件查询
	List<MaterialBrand> queryBrandList(MaterialBrand materialBrand);

	//各种统计
	int getCountByObj(MaterialBrand materialBrand);


	//===========================以下是没有使用的方法===========================================

	/**
	 * 获取所有品牌
	 * @return List<MaterialBrand>
	 */
    List<MaterialBrand> findAll();
    
    /**
     * 获取品牌 用于排重
     * @param brandName
     * @return MaterialBrand
     */
	MaterialBrand findByMaterialBrandName(String brandName);

	/**
	 * 根据条件获取符合条件的品牌信息
	 * @param materialBrand 品牌查询对象
	 * @return 符合条件的品牌
	 */








	Map<String,Object> countBrandState(MaterialBrand materialBrand);

}
