package com.gh.service.material.service.impl;

import com.gh.db.PageUtils;
import com.gh.db.Query;
import com.gh.pojo.entity.material.MaterialBrand;
import com.gh.pojo.enumsource.sys.MaterialState;
import com.gh.pojo.vo.material.MaterialBrandVo;
import com.gh.service.common.utils.PageQuery;
import com.gh.service.material.dao.MaterialBrandDao;
import com.gh.service.material.service.MaterialBrandService;
//import com.gh.service.material.service.MaterialProductService;
import com.gh.service.material.service.MaterialProductService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("materialBrandService")
public class MaterialBrandServiceImpl implements MaterialBrandService {

	@Autowired
	private MaterialBrandDao materialBrandDao;

	//辅材的其他服务
	@Autowired
	private MaterialProductService materialProductService;





	/**
	 * 分页查询全部信息
	 */
	public PageUtils<MaterialBrand> queryBrandList(PageQuery<MaterialBrandVo> reqVo){
		//1 设置分页,可以获取MaterialBrandVo继承的类,子类
		MaterialBrand model = reqVo.getParamModel();
		//this.paramsHandle(model);//重置时间段
		/**
		 * 设定分页数据
		 * getPage:页数   默认是第一页
		 * getLimit:页面大小   默认是10条/页
		 * PageHelper.startPage(m,n):方法会在要执行的SQL语句中,添加分页语句
		 * m:代表的是第几页
		 * n:代表的是每页显示的条数(数量)
		 */
		PageHelper.startPage(reqVo.getPage(), reqVo.getLimit());

		//1 查询品牌信息   查询全部的数据   PageHelper.startPage()会在对应的mapper中添加对应的分页SQL
		List<MaterialBrand> brandList = materialBrandDao.queryBrandList(model);

		//2 获取总数
		int total = materialBrandDao.getCountByObj(model);
		//获取上线数量
		model.setBrandState(0);
		int onLineCount=materialBrandDao.getCountByObj(model);
		//获取下线数量
		model.setBrandState(1);
		int offLineCount=materialBrandDao.getCountByObj(model);
		PageUtils<MaterialBrand> pageUtil = new PageUtils<MaterialBrand>(brandList, total, reqVo.getLimit(), reqVo.getPage());
		pageUtil.setOffLineCount(offLineCount);
		pageUtil.setOnLineCount(onLineCount);
		//3 返回封装后的结果
		return pageUtil;
	}



	//新增
	public void saveMaterialBrand(MaterialBrand materialBrand) {
		materialBrand.setBrandState(2);//未上线(初始化都是未上线)
		materialBrand.setCreateTime(new Date());
		materialBrand.setUpdateTime(materialBrand.getCreateTime());
		materialBrandDao.save(materialBrand);
	}

	/**
	 * 更新操作
	 * 5.上线状态：数据不可进行编辑、可以进行下线操作   上线--->下线     品牌表需要更改   brand_state将   0--->1  (√)
	 * 6.未上线状态：可以编辑（名称、数据类型、排序）、上线操作
	 * 7.已下线状态：可以编辑（名称、排序）、上线操作
	 * 8.上下线不影响辅材及配件库管理的上下线；根据修改时间倒叙排列
	 */
	public void updateMaterialBrand(MaterialBrandVo materialBrandVo){

		//获取品牌id
		Long materialBrandId=materialBrandVo.getId();
		//根据id,从数据库中查询相应的品牌
		MaterialBrand materialBrand = this.getMaterialBrand(materialBrandId);

		//品牌实例不是null
		if (materialBrand != null) {
			//获取前端传递进来的品牌名称
			String brandName=materialBrandVo.getBrandName();
			//获取前端传递进来的品牌状态
			Integer brandState=materialBrandVo.getBrandState();
			//获取前端传递进来的记录类型
			Integer recordType=materialBrandVo.getRecordType();
			//定义初始变量
			boolean nameChanged = false;
			boolean stateChanged = false;
			boolean recordTypeChanged=false;

			//判断brandName不是空    传递进来的品牌名称和数据库中查询出来的名称不一致--->更新品牌名称
			if (StringUtil.isNotEmpty(brandName) && !materialBrand.getBrandName().equals(brandName)) {
				nameChanged = true;
				materialBrand.setBrandName(brandName);
			}

			//更新品牌状态      传递进来的品牌状态和数据库中查询出来的状态不一致--->更新品牌状态     查询的值是0
			if (brandState != null && materialBrand.getBrandState().intValue() != brandState.intValue()) {
				stateChanged = true;
				materialBrand.setBrandState(brandState);
			}

			//更新品牌的记录类型:1,正式;2,运营;3,测试
			if (recordType != null && materialBrand.getBrandState().intValue() != recordType.intValue()) {
				recordTypeChanged = true;
				materialBrand.setRecordType(recordType);
			}

			//排序
			materialBrand.setSort(materialBrandVo.getSort());
			//操作人的id
			materialBrand.setOptUserId(materialBrandVo.getOptUserId());
			//操作人的名字
			materialBrand.setOptUserName(materialBrandVo.getOptUserName());
			//更新时间
			materialBrand.setUpdateTime(new Date());

			//品牌更新
			materialBrandDao.update(materialBrand);

			if (nameChanged) {
				// 更新辅材中的计量单位名称
				materialProductService.updateMaterialProductBrandNameByBrandId(materialBrandId, brandName);
			}

			if (stateChanged) {
				// 更新辅材状态(0正常,1下架)     品牌表需要更改   brand_state将   0-变成-->1        传递进来的数据是:1  辅材的1
				materialProductService.updateMaterialProductStateByBrand(materialBrandId, brandState);
			}
			if (recordTypeChanged) {
				// 更新辅材记录
				materialProductService.updateMaterialProductStateByBrand(materialBrandId, brandState);
			}

		}
	}

	//============以下是未使用的==================================================================================================
	public List<MaterialBrand> findAllBrand() {
		return materialBrandDao.findAll();
	}
	//根据传入的ID,查询品牌
	public MaterialBrand getMaterialBrand(Long materialBrandId) {

		return materialBrandDao.get(materialBrandId);
	}


	//================================================================
	//更新
	public void updateMaterialBrand(MaterialBrand materialBrand) {
		//传入更新时间
		materialBrand.setUpdateTime(new Date());
		materialBrandDao.update(materialBrand);
	}
    //================================================================

    //============================以下的没理解====================================
	//
	/*public void updateMaterialBrand(Long materialBrandId, Integer brandState, String brandName,
			Integer sort,Integer recordType, Long optUserId, String optUserName) {*/


	//================================================================





	public MaterialBrand findByMaterialBrandName(String brandName) {
		return materialBrandDao.findByMaterialBrandName(brandName);
	}

	public List<MaterialBrand> queryBrandList(Query query){
		return materialBrandDao.queryList(query);
	}
	public int queryTotal(Query query){
		return materialBrandDao.queryTotal(query);
	}

	public List<MaterialBrand> queryBrandList(MaterialBrand materialBrand){
		return materialBrandDao.queryBrandList(materialBrand);
	}



	
	public String countBrandState(MaterialBrand reqVo){
		//1 查询品牌信息
		Map<String, Object> countBrandState = materialBrandDao.countBrandState(reqVo);
		if(!countBrandState.isEmpty() && countBrandState.size() >0){
			return countBrandState.get("key1").toString()+","+countBrandState.get("key2").toString();
		} else {
			return "0,0";
		}
		
		
	}
}