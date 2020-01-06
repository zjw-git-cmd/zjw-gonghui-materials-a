package com.gh.service.material.service.impl;

import com.gh.db.RCPageUtils;
//import com.gh.pojo.entity.base.SysConfig;
import com.gh.pojo.entity.firm.MaterialDetailImport;
//import com.gh.pojo.entity.material.MaterialGoods;
//import com.gh.pojo.entity.material.MaterialProduct;
//import com.gh.pojo.entity.order.FirmOrderProduct;
//import com.gh.pojo.enumsource.sys.MaterialState;
import com.gh.pojo.enumsource.sys.ProductState;
//import com.gh.pojo.enumsource.sys.ProductType;
//import com.gh.pojo.vo.material.AccessoryVo;
//import com.gh.pojo.vo.material.BrandSpecVo;
//import com.gh.pojo.vo.material.QueryAccessoryVo;
//import com.gh.service.common.service.SysConfigService;
import com.gh.pojo.entity.material.MaterialProduct;
import com.gh.pojo.enumsource.sys.MaterialState;
import com.gh.service.common.utils.PageQuery;
//import com.gh.service.material.dao.MaterialGoodsDao;
//import com.gh.service.material.dao.MaterialProductDao;
import com.gh.service.material.dao.MaterialProductDao;
import com.gh.service.material.service.MaterialProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
//import java.util.function.Consumer;
//import java.util.stream.Collectors;


@Service("materialProductService")
public class MaterialProductServiceImpl implements MaterialProductService {
	private static final String OTHER="其他";
	private static final String TEMP_CITYCODE="temp_cityCode";
	@Autowired
	private MaterialProductDao materialProductDao;

	/**
	 * 更新指定辅材的品牌名称
	 * @param materialBrandId   指定品牌id
	 * @param materialBrandName  指定品牌名称
	 */
	public void updateMaterialProductBrandNameByBrandId(Long materialBrandId, String materialBrandName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("brandId", materialBrandId);
		params.put("brandName", materialBrandName);
		materialProductDao.updateMaterialByParam(params);
	}



	//更新辅材的状态
	@Override
	public void updateMaterialProductStateByBrand(Long materialBrandId, Integer brandState) {//1
		//下线   和  未上线         将其下架   0--->1  正常--->下架
		//        1                      已下线
		if (brandState.intValue() == MaterialState.OFFLINE.getKey().intValue()
				//   1                            未上线    2
				|| brandState.intValue() == MaterialState.NOTONLINE.getKey().intValue()) {
			// 如果品牌状态变更为“已下线”或“已删除”，则将“上架”状态的辅材设置为“下架”状态
			List<MaterialProduct> materialProductList = this.findMaterialProductByStateAndBrandId(
					//        0   已经上线          品牌id
					MaterialState.ONLINE.getKey(), materialBrandId);
			//将正常状态的辅材下架   0---->1
			for (MaterialProduct materialProduct : materialProductList) {
				//给定辅材     指定已下架
				this.updateMaterialProductState(materialProduct, ProductState.OFFLINE.getKey());
			}
		}
	}

	/**
	 * 更新辅材状态入口，方便统一同步修改辅材价格状态
	 * @param
	 * @param    materialState:传递进来的参数是   已经下架
	 */
	@Override
	public void updateMaterialProductState(MaterialProduct materialProduct, Integer materialState) {
		//判断辅材的状态不是已下架
		if (materialProduct.getMaterialState().intValue() != materialState.intValue()) {
			//设定下架
			materialProduct.setMaterialState(materialState);
			materialProductDao.update(materialProduct);
		}
	}
	//通过品牌id和辅材状态  查询对应的辅材数据    传递进来的是
	@Override
	public List<MaterialProduct> findMaterialProductByStateAndBrandId(Integer materialState, Long materialBrandId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("materialState", materialState);
		params.put("brandId", materialBrandId);
		return materialProductDao.queryList(params);
	}




	/**
	 * 根据品牌数据类型更新辅材数据类型
	 * @param materialBrandId   品牌id
	 */
	@Override
	public void updateMaterialProductRecordTypeByBrand(Long materialBrandId, Integer recordType) {
		//更改辅材的记录类型

	}








	//===============以下是未使用的========================================================================================================================

/*
	@Autowired
	private MaterialGoodsDao materialGoodsDao;

	@Autowired
	private SysConfigService sysConfigService;

	@Override
	public MaterialProduct getMaterialProduct(Long materialProductId) {
		return materialProductDao.get(materialProductId);
	}

	@Override
	public Long saveMaterialProduct(MaterialProduct materialProduct) {
		materialProduct.setCreateTime(new Date());
		materialProduct.setUpdateTime(materialProduct.getCreateTime());
		materialProductDao.save(materialProduct);
		return materialProduct.getId();
	}*/

	/*@Override
	public void updateMaterialProduct(MaterialProduct materialProduct) {
		if (materialProduct != null && materialProduct.getId() != null) {
			materialProduct.setUpdateTime(new Date());
			materialProductDao.update(materialProduct);
		}
	}

	@Override
	public List<MaterialProduct> queryMaterialProductByGoodsIds(List<Long> ids, Integer goodsType){
		return materialProductDao.queryMaterialProductByGoodsIds(ids,goodsType);
	}

	@Override
	public List<MaterialProduct> queryMaterialProduct(MaterialProduct materialProduct) {
		return materialProductDao.queryMaterialProduct(materialProduct);
	}

	@Override
	public RCPageUtils<MaterialProduct> queryMaterialProductPageData(PageQuery<MaterialProduct> reqVo) {
		//1 设置分页
		MaterialProduct materialProduct = reqVo.getParamModel();
		this.paramsHandle(materialProduct);//重置时间段
		PageHelper.startPage(reqVo.getPage(), reqVo.getLimit());
		String side = reqVo.getSidx();
		String order = reqVo.getOrder();
		StringBuffer orderBy = new StringBuffer();
		orderBy.append(com.gh.util.StringUtil.isNullOrEmpty(side) ? "mp.update_time desc":side+" "+order);
		PageHelper.orderBy(orderBy.toString());
		//2.获取查询的辅材
		List<MaterialProduct> materialProductList = materialProductDao.queryMaterialProduct(materialProduct);
		//3. 获取总数
		List<Map<String, Long>> countByObj = materialProductDao.getCountByObj(materialProduct);
		Map<String, Long>  countMap = new HashMap<>();
		Consumer<Map<String, Long>> mapConsumer = t -> countMap.putAll(t);
		countByObj.stream().forEach(mapConsumer);
		//4.封装分页信息
		RCPageUtils<MaterialProduct> pageUtil = new RCPageUtils(materialProductList, countMap, countMap.get("count"), reqVo.getLimit(), reqVo.getPage());
		//5 返回封装后的结果
		return pageUtil;
	}

	*//**
	 * 根据 辅材名称、辅材昵称、辅材类型、 品牌、 规格、供应商 查询辅材 用于排重
	 * @param materialProduct 查询对象
	 * @return 符合条件的辅材
	 *//*
	@Override
	public List<MaterialProduct> exactQueryMaterialProduct(MaterialProduct materialProduct){
		return materialProductDao.exactQueryMaterialProduct(materialProduct);
	}

	*//**
	 * 更新辅材状态
	 * @param materialState 辅材状态
	 * @param id 辅材id
	 * @return 更新个数
	 *//*
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<Long> updateProductState(Integer materialState,Long id,Long optUserId,String optUserName,Integer productType){
		List<Long> goodsIds = new ArrayList<>();
		// 更新辅材状态
		materialProductDao.updateProductState(materialState,id,optUserId,optUserName);
		List<MaterialGoods> materialGoodsList = new ArrayList<>();
		if(ProductState.OFFLINE.getKey().intValue() == materialState.intValue()){
			// 更新辅材价格状态
			materialGoodsDao.updateGoodsStateByMaterialId(materialState,null,id,optUserId,optUserName);
			List<Long> materialIdsList = new ArrayList<>();
			materialIdsList.add(id);
			if(productType == ProductType.PJ.getKey()){
				//如果是配件类型则级联下架配件包以及配件包价格
				materialProductDao.updateMountingsState(materialState,id,optUserId,optUserName);
				materialGoodsList = materialGoodsDao.queryMaterialGoodsByPartsOrPartsPrice(materialIdsList, null);
			}else if(productType == ProductType.FC.getKey()){
				materialGoodsList = materialGoodsDao.queryMaterialGoodsByMaterialIds(materialIdsList,null,null);
			}
		}
		if(!CollectionUtils.isEmpty(materialGoodsList)){
			goodsIds = materialGoodsList.stream().map(MaterialGoods::getId).distinct().collect(Collectors.toList());
		}
		return goodsIds;
	}*/

	/**
	 * 批量更新辅材状态
	 * @param materialState 辅材状态
	 * @param ids 辅材id
	 * @return 更新个数
	 */
	/*@Override
	@Transactional(rollbackFor = Exception.class)
	public List<Long> batchUpdateProductState(Integer materialState,List<Long> ids,Long optUserId,String optUserName,Integer productType){
		List<Long> goodsIds = new ArrayList<>();
		// 更新辅材状态
		materialProductDao.batchUpdateProductState(materialState,ids,optUserId,optUserName);
		List<MaterialGoods> materialGoodsList = new ArrayList<>();
		if(ProductState.OFFLINE.getKey().intValue() == materialState.intValue()){
			// 更新辅材价格状态
			materialGoodsDao.batchUpdateGoodsStateByMaterialId(materialState,null,ids,optUserId,optUserName);
			if(productType == ProductType.PJ.getKey()){
				//如果是配件类型则级联下架配件包以及配件包价格
				materialProductDao.batchUpdateMountingsState(materialState,ids,optUserId,optUserName);
				materialGoodsList = materialGoodsDao.queryMaterialGoodsByPartsOrPartsPrice(ids, null);
			}else if(productType == ProductType.FC.getKey()){
				materialGoodsList = materialGoodsDao.queryMaterialGoodsByMaterialIds(ids,null,null);
			}
		}
		if(!CollectionUtils.isEmpty(materialGoodsList)){
			goodsIds = materialGoodsList.stream().map(MaterialGoods::getId).distinct().collect(Collectors.toList());
		}
		return goodsIds;
	}

	@Override
	public void updateMaterialProductStateByUnit(Long measureUnitId, Integer unitState) {
		if (unitState.intValue() == MaterialState.OFFLINE.getKey().intValue()
				|| unitState.intValue() == MaterialState.REMOVE.getKey().intValue()) {
			// 如果计量单位状态变更为“已下线”或“已删除”，则将“上架”状态的辅材设置为“下架”状态
			List<MaterialProduct> materialProductList = this.findMaterialProductByStateAndUnitId(
					MaterialState.NORMAL.getKey(), measureUnitId);
			for (MaterialProduct materialProduct : materialProductList) {
				this.updateMaterialProductState(materialProduct, ProductState.OFFLINE.getKey());
			}
		}
	}

	@Override
	public void updateMaterialProductStateBySpec(Long materialSpecId, Integer specState) {
		if (specState.intValue() == MaterialState.OFFLINE.getKey().intValue()
				|| specState.intValue() == MaterialState.REMOVE.getKey().intValue()) {
			// 如果规格状态变更为“已下线”或“已删除”，则将“上架”状态的辅材设置为“下架”状态
			List<MaterialProduct> materialProductList = this.findMaterialProductByStateAndSpecId(
					MaterialState.NORMAL.getKey(), materialSpecId);
			for (MaterialProduct materialProduct : materialProductList) {
				this.updateMaterialProductState(materialProduct, ProductState.OFFLINE.getKey());
			}
		}
	}
	*/











	/*
	@Override
	public void updateMaterialProductStateBySupplier(Long materialSupplierId, Integer supplierState) {
		if (supplierState.intValue() == MaterialState.OFFLINE.getKey().intValue()
				|| supplierState.intValue() == MaterialState.REMOVE.getKey().intValue()) {
			// 如果供应商状态变更为“已下线”或“已删除”，则将“上架”状态的辅材设置为“下架”状态
			List<MaterialProduct> materialProductList = this.findMaterialProductByStateAndSupplierId(
					MaterialState.NORMAL.getKey(), materialSupplierId);
			for (MaterialProduct materialProduct : materialProductList) {
				this.updateMaterialProductState(materialProduct, ProductState.OFFLINE.getKey());
			}
		}
	}*/






/*
	@Override
	public List<MaterialProduct> findMaterialProductByStateAndUnitId(Integer materialState, Long measureUnitId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("materialState", materialState);
		params.put("unitId", measureUnitId);
		return materialProductDao.queryList(params);
	}*/

	/*@Override
	public List<MaterialProduct> findMaterialProductByStateAndSpecId(Integer materialState, Long materialSpecId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("materialState", materialState);
		params.put("specId", materialSpecId);
		return materialProductDao.queryList(params);
	}
*/





/*
	@Override
	public List<MaterialProduct> findMaterialProductByStateAndSupplierId(Integer materialState, Long materialSupplierId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("materialState", materialState);
		params.put("supplierId", materialSupplierId);
		return materialProductDao.queryList(params);
	}*/

	/*@Override
	public void updateMaterialProductUnitNameByUnitId(Long measureUnitId, String measureUnitName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitId", measureUnitId);
		params.put("unitName", measureUnitName);
		materialProductDao.updateMaterialByParam(params);
	}
*/
	/*@Override
	public void updateMaterialProductSpecNameBySpecId(Long materialSpecId, String materialSpecName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("specId", materialSpecId);
		params.put("specName", materialSpecName);
		materialProductDao.updateMaterialByParam(params);
	}*/





	/*@Override
	public void updateMaterialProductSupplierNameBySupplierId(Long materialSupplierId, String materialSupplierName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("supplierId", materialSupplierId);
		params.put("supplierName", materialSupplierName);
		materialProductDao.updateMaterialByParam(params);
	}

	@Override
	public int queryTotal(Map<String, Object> params) {
		return materialProductDao.queryTotal(params);
	}

	@Override
	public List<MaterialProduct> queryByMaterialNameAndBrandIdAndCityCode(String materialName, Long brandId, String cityCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("materialName", materialName);
		params.put("brandId", brandId);
		params.put("cityCode", cityCode);
		return materialProductDao.queryList(params);
	}*/


	/*@Override
	public MaterialProduct findMaterialProduct(MaterialDetailImport materialDetailImport) {
		return materialProductDao.findMaterialProduct(materialDetailImport);
	}

	@Override
	public void insertMaterialProduct(MaterialProduct materialProduct) {
		materialProductDao.insertMaterialProduct(materialProduct);
	}

	@Override
	public List<MaterialProduct> queryMaterialProductByMaterialNickNames(String materialNickNames, Integer materialState, String cityCode) {
		List<SysConfig> list = sysConfigService.findByCode(materialNickNames);
		SysConfig sysConfig = list.get(0);
		return materialProductDao.queryMaterialProductByMaterialNickNames(sysConfig.getValue(),materialState,cityCode);
	}*/

	/**
	 * 特殊参数处理,时间段
	 * @param materialProduct
	 */
	/*private void paramsHandle(MaterialProduct materialProduct){
		if(materialProduct!=null){
			if(!StringUtils.isEmpty(materialProduct.getStartDate())){
				materialProduct.setStartDate(materialProduct.getStartDate()+" 00:00:00");
			}
			if(!StringUtils.isEmpty(materialProduct.getEndDate())){
				materialProduct.setEndDate(materialProduct.getEndDate()+" 23:59:59");
			}
		}
	}*/

	/*@Override
	public List<MaterialProduct> queryMaterialProductByIdsAndCityCode(List<Long> ids, Integer materialState, String cityCode) {
		return materialProductDao.queryMaterialProductByIdsAndCityCode(ids, materialState, cityCode);
	}*/

	/**
	 * 查询配件条件：品牌-规格，其他-规格，其他-其他
	 * 第二个：要改成：品牌-规格，其他-""
	 */
	/*@Override
	public List<FirmOrderProduct> getAccessory(QueryAccessoryVo vo) {
		Map<String,Object> params = new HashMap<>();
		params.put("materialType", vo.getMaterialType());
		Set<BrandSpecVo> listVo = vo.getListVo();
		StringBuilder sb = new StringBuilder();
		String cityCode="1111110000";
		
		for(BrandSpecVo bs:listVo){
			if(bs.getBrandName().equals(TEMP_CITYCODE)){
				cityCode = bs.getSpecName();
			}
			
			sb.append("(p.brand_name='");
			sb.append(bs.getBrandName());
			sb.append("' and p.spec_name='");
			sb.append(bs.getSpecName());
			sb.append("') or ");//品牌-规格
//			sb.append("(p.brand_name='");
//			sb.append(OTHER);
//			sb.append("' and p.spec_name='");
//			sb.append(bs.getSpecName());
//			sb.append("') or ");//其他-规格
		}
		boolean isOld = false;
		for(BrandSpecVo bs:listVo){
			if(bs.getBrandName()!=null && bs.getBrandName().equals("old_order")){
				isOld = true;
				break;
			}
		}
		if(isOld){
			sb.append("(p.brand_name='");
			sb.append(OTHER);
			sb.append("') or ");//其他-其他
		} else {
			sb.append("(p.brand_name='");
			sb.append("1"+OTHER);
			sb.append("') or ");//其他-其他
		}
		
		String str = sb.toString();
		str = str.substring(0, str.length()-4);
		params.put("paramList", str);
		params.put("cityCode", cityCode);
		
		
		List<AccessoryVo> accessory = materialProductDao.getAccessory(params);
		List<FirmOrderProduct> proList = new ArrayList<>();
		if(accessory!=null && accessory.size() > 0){
			for(int i = 0 ;i < accessory.size(); i++){
				FirmOrderProduct fo = new FirmOrderProduct();
				AccessoryVo accessoryVo = accessory.get(i);
				fo.setWorkPosition(accessoryVo.getWorkPosition());
				fo.setUnitCostPrice(accessoryVo.getUnitCostPrice());
				fo.setUnitSalesPrice(accessoryVo.getUnitSalesPrice());
				fo.setMaterialType(accessoryVo.getMaterialType());
				fo.setBrandName(accessoryVo.getBrandName());
				fo.setSpecName(accessoryVo.getSpecName());
				fo.setMaterialName(accessoryVo.getMaterialName());
				fo.setMaterialNickName(accessoryVo.getMaterialNickName());
				fo.setSupplierId(accessoryVo.getSupplierId());
				fo.setSupplierName(accessoryVo.getSupplierName());
				fo.setMaterialId(accessoryVo.getId());
				fo.setMaterialPriceId(accessoryVo.getGoodsId());
				fo.setPackagingSpec(accessoryVo.getPackagingSpec());
				fo.setPackingUnit(accessoryVo.getPackingUnit());
				fo.setUnitName(accessoryVo.getUnitName());
				fo.setCutFlag(accessoryVo.getCutFlag());
				fo.setCutMethod(accessoryVo.getCutMethod());
				proList.add(fo);
			}
			return proList;
		} else {
			return null;
		}
	}*/

	/*@Override
	public List<AccessoryVo> queryCutMethodByMaterialIds(List<Long> materialIds) {
		return materialProductDao.queryCutMethodByMaterialIds(materialIds);
	}*/

	/*@Override
	public List<FirmOrderProduct> getAccessoryGJ(QueryAccessoryVo vo) {
		Map<String,Object> params = new HashMap<>();
		params.put("materialType", vo.getMaterialType());
		Set<BrandSpecVo> listVo = vo.getListVo();
		String cityCode="1111110000";
		if(!listVo.isEmpty()) {
			for (BrandSpecVo bs : listVo) {
				if (bs.getBrandName().equals(TEMP_CITYCODE)) {
					cityCode = bs.getSpecName();
				}
			}
		}
		params.put("cityCode", cityCode);
		List<AccessoryVo> accessory = materialProductDao.getAccessory(params);
		List<FirmOrderProduct> proList = new ArrayList<>();
		if(accessory!=null && accessory.size() > 0){
			for(int i = 0 ;i < accessory.size(); i++){
				FirmOrderProduct fo = new FirmOrderProduct();

				fo.setWorkPosition(accessory.get(i).getWorkPosition());
				fo.setUnitCostPrice(accessory.get(i).getUnitCostPrice());
				fo.setUnitSalesPrice(accessory.get(i).getUnitSalesPrice());
				fo.setMaterialType(accessory.get(i).getMaterialType());
				fo.setBrandName(accessory.get(i).getBrandName());
				fo.setSpecName(accessory.get(i).getSpecName());
				fo.setMaterialName(accessory.get(i).getMaterialName());
				fo.setMaterialNickName(accessory.get(i).getMaterialNickName());
				fo.setSupplierId(accessory.get(i).getSupplierId());
				fo.setSupplierName(accessory.get(i).getSupplierName());
				fo.setMaterialId(accessory.get(i).getId());
				fo.setMaterialPriceId(accessory.get(i).getGoodsId());
				fo.setPackagingSpec(accessory.get(i).getPackagingSpec());
				fo.setPackingUnit(accessory.get(i).getPackingUnit());
				fo.setUnitName(accessory.get(i).getUnitName());
				fo.setCutFlag("false");
				fo.setCutMethod("");
				proList.add(fo);
			}
			return proList;
		} else {
			return null;
		}
	}*/

	/*public List<FirmOrderProduct> getAccessorySearch(QueryAccessoryVo vo) {
		Map<String,Object> params = new HashMap<>();
		Set<BrandSpecVo> listVo = vo.getListVo();
		StringBuilder sb = new StringBuilder();
		String cityCode="1111110000";
		for(BrandSpecVo bs:listVo){
			if(bs.getBrandName().equals(TEMP_CITYCODE)){
				cityCode = bs.getSpecName();
			}
		}
		for(BrandSpecVo bs:listVo){
			if(bs.getBrandName().equals(TEMP_CITYCODE)){
				cityCode = bs.getSpecName();
			}

			sb.append("(p.brand_name='");
			sb.append(bs.getBrandName());
			sb.append("' and p.spec_name='");
			sb.append(bs.getSpecName());
			sb.append("') or ");//品牌-规格
//			sb.append("(p.brand_name='");
//			sb.append(OTHER);
//			sb.append("' and p.spec_name='");
//			sb.append(bs.getSpecName());
//			sb.append("') or ");//其他-规格
		}
		boolean isOld = false;
		for(BrandSpecVo bs:listVo){
			if(bs.getBrandName()!=null && bs.getBrandName().equals("old_order")){
				isOld = true;
				break;
			}
		}
		if(isOld){
			sb.append("(p.brand_name='");
			sb.append(OTHER);
			sb.append("') or ");//其他-其他
		} else {
			sb.append("(p.brand_name='");
			sb.append("1"+OTHER);
			sb.append("') or ");//其他-其他
		}

		String str = sb.toString();
		str = str.substring(0, str.length()-4);
		params.put("paramList", str);
		params.put("cityCode", cityCode);
		params.put("queryParam",vo.getQueryParam());
		params.put("numbers", vo.getNumbers());
		params.put("limit", vo.getLimit());
		List<AccessoryVo> accessory = materialProductDao.getAccessorySearch(params);
		List<FirmOrderProduct> proList = new ArrayList<>();
		if(accessory!=null && accessory.size() > 0){
			for(int i = 0 ;i < accessory.size(); i++){
				FirmOrderProduct fo = new FirmOrderProduct();

				fo.setWorkPosition(accessory.get(i).getWorkPosition());
				fo.setUnitCostPrice(accessory.get(i).getUnitCostPrice());
				fo.setUnitSalesPrice(accessory.get(i).getUnitSalesPrice());
				fo.setMaterialType(accessory.get(i).getMaterialType());
				fo.setBrandName(accessory.get(i).getBrandName());
				fo.setSpecName(accessory.get(i).getSpecName());
				fo.setMaterialName(accessory.get(i).getMaterialName());
				fo.setMaterialNickName(accessory.get(i).getMaterialNickName());
				fo.setSupplierId(accessory.get(i).getSupplierId());
				fo.setSupplierName(accessory.get(i).getSupplierName());
				fo.setMaterialId(accessory.get(i).getId());
				fo.setMaterialPriceId(accessory.get(i).getGoodsId());
				fo.setPackagingSpec(accessory.get(i).getPackagingSpec());
				fo.setPackingUnit(accessory.get(i).getPackingUnit());
				fo.setUnitName(accessory.get(i).getUnitName());
				fo.setCutFlag(accessory.get(i).getCutFlag());
				fo.setCutMethod(accessory.get(i).getCutMethod());
				proList.add(fo);
			}
			return proList;
		} else {
			return null;
		}
	}*/
}
