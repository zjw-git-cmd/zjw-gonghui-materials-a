package com.gh.service.material.service;

import com.gh.db.RCPageUtils;
import com.gh.pojo.entity.firm.MaterialDetailImport;
import com.gh.pojo.entity.material.MaterialProduct;
//import com.gh.pojo.entity.order.FirmOrderProduct;
//import com.gh.pojo.vo.material.AccessoryVo;
//import com.gh.pojo.vo.material.QueryAccessoryVo;
import com.gh.service.common.utils.PageQuery;

import java.util.List;
import java.util.Map;


/**
 * 
 * <b>辅材接口</b><br/>
 *
 * @author Seal
 * @time 2018年4月9日 下午3:30:14
 */
public interface MaterialProductService {


	/**
	 * 更新指定辅材的品牌名称
	 * @param materialBrandId   指定品牌id
	 * @param materialBrandName  指定品牌名称
	 */
	void updateMaterialProductBrandNameByBrandId(Long materialBrandId, String materialBrandName);


	/**
	 * 根据品牌状态更新辅材状态
	 * @param materialBrandId   品牌id
	 * @param brandState    品牌状态
	 */
	void updateMaterialProductStateByBrand(Long materialBrandId, Integer brandState);




	/**
	 * 根据品牌数据类型更新辅材数据类型
	 * @param materialBrandId   品牌id
	 * @param brandState    品牌状态
	 */
	void updateMaterialProductRecordTypeByBrand(Long materialBrandId, Integer recordType);


	/**
	 * 更新辅材状态入口，方便统一同步修改辅材价格状态
	 * @param materialProduct
	 * @param materialState
	 */
	void updateMaterialProductState(MaterialProduct materialProduct, Integer materialState);








	//===============================以下是暂时未使用的=========================================



	/**
	 * 根据ID获取辅材
	 * @param materialProductId
	 * @return MaterialProduct
	 */
	//MaterialProduct getMaterialProduct(Long materialProductId);

	/**
	 * 新增辅材
	 * @param materialProduct
	 */
	//Long saveMaterialProduct(MaterialProduct materialProduct);

	/**
	 * 更新辅材
	 * @param materialProduct
	 */
	//void updateMaterialProduct(MaterialProduct materialProduct);

	/**
	 * 更加辅材价格Ids查询符合条件的辅材
	 * @param ids 辅材ids
	 * @return 返回符合条件的辅材
	 */
	//List<MaterialProduct> queryMaterialProductByGoodsIds(List<Long> ids, Integer goodsType);

	/**
	 * 根据辅材Ids查询辅材信息
	 * @param ids 辅材Ids
	 * @param materialState 辅材状态
	 * @return 符合条件的辅材
	 */
	//List<MaterialProduct> queryMaterialProductByIdsAndCityCode(List<Long> ids, Integer materialState, String cityCode);

	/**
	 * 查询辅材
	 * @param materialProduct
	 * @return List<MaterialProduct>
	 */
	//List<MaterialProduct> queryMaterialProduct(MaterialProduct materialProduct);
	/**
	 * 查询辅材
	 * @param materialProduct
	 * @return PageUtils<MaterialProduct>
	 */
	//RCPageUtils<MaterialProduct> queryMaterialProductPageData(PageQuery<MaterialProduct> materialProduct);

	/**
	 * 查询总数
	 * @param params
	 * @return 查询总数
	 */
	//int queryTotal(Map<String, Object> params);

	/**
	 * 根据 辅材名称、辅材昵称、辅材类型、 品牌、 规格、供应商 查询辅材 用于排重
	 * @param materialProduct 查询对象
	 * @return 符合条件的辅材
	 */
	//List<MaterialProduct> exactQueryMaterialProduct(MaterialProduct materialProduct);

	/**
	 * 更新辅材状态
	 * @param materialState 辅材状态
	 * @param id 辅材id
	 * @return 更新个数
	 */
	//List<Long> updateProductState(Integer materialState, Long id, Long optUserId, String optUserName, Integer productType);

	/**
	 * 批量更新辅材状态
	 * @param materialState
	 * @param ids
	 * @param optUserId
	 * @param optUserName
	 * @param productType
	 * @return
	 */
	//List<Long> batchUpdateProductState(Integer materialState, List<Long> ids, Long optUserId, String optUserName, Integer productType);

	/**
	 * 根据计量单位状态更新辅材状态
	 * @param measureUnitId
	 * @param unitState
	 */
	//void updateMaterialProductStateByUnit(Long measureUnitId, Integer unitState);

	/**
	 * 根据规格状态更新辅材状态
	 * @param materialSpecId
	 * @param specState
	 */
	//void updateMaterialProductStateBySpec(Long materialSpecId, Integer specState);



	/**
	 * 根据供应商状态更新辅材状态
	 * @param materialSupplierId
	 * @param supplierState
	 */
//	void updateMaterialProductStateBySupplier(Long materialSupplierId, Integer supplierState);





	/**
	 * 根据计量单位ID查找指定状态的辅材
	 * @param materialState
	 * @param measureUnitId
	 * @return List<MaterialProduct>
	 */
//	List<MaterialProduct> findMaterialProductByStateAndUnitId(Integer materialState, Long measureUnitId);

	/**
	 * 根据规格ID查找指定状态的辅材
	 * @param materialState
	 * @param materialSpecId
	 * @return List<MaterialProduct>
	 */
//	List<MaterialProduct> findMaterialProductByStateAndSpecId(Integer materialState, Long materialSpecId);

	/**
	 * 根据品牌ID查找指定状态的辅材
	 * @param materialState
	 * @param materialBrandId
	 * @return List<MaterialProduct>
	 */
	List<MaterialProduct> findMaterialProductByStateAndBrandId(Integer materialState, Long materialBrandId);

	/**
	 * 根据供应商ID查找指定状态的辅材
	 * @param materialState
	 * @param materialSupplierId
	 * @return List<MaterialProduct>
	 */
//	List<MaterialProduct> findMaterialProductByStateAndSupplierId(Integer materialState, Long materialSupplierId);

	/**
	 * 更新指定辅材的计量单位名称
	 * @param measureUnitId
	 * @param measureUnitName
	 */
//	void updateMaterialProductUnitNameByUnitId(Long measureUnitId, String measureUnitName);

	/**
	 * 更新指定辅材的规格名称
	 * @param materialSpecId
	 * @param materialSpecName
	 */
//	void updateMaterialProductSpecNameBySpecId(Long materialSpecId, String materialSpecName);



	/**
	 * 更新指定辅材的供应商名称
	 * @param materialSupplierId
	 * @param materialSupplierName
	 */
//	void updateMaterialProductSupplierNameBySupplierId(Long materialSupplierId, String materialSupplierName);
//
	/**
	 * 根据辅材名称、品牌ID、市编码查询辅材列表
	 * @param materialName
	 * @param brandId
	 * @param cityCode
	 * @return
	 */
//	List<MaterialProduct> queryByMaterialNameAndBrandIdAndCityCode(String materialName, Long brandId, String cityCode);

	/**
	 * 根据 辅材名称 品牌 规格查询辅材 用于排重
	 * @param materialDetailImport
	 * @return MaterialProduct
	 */
//	MaterialProduct findMaterialProduct(MaterialDetailImport materialDetailImport);

	/**
	 * 根据excel数据添加辅材
	 * @param materialProduct
	 */
//	void insertMaterialProduct(Materia/lProduct materialProduct);
	/**
	 * 根据条件获取配件
	 * @param params
	 * @return
	 */
//	List<FirmOrderProduct> getAccessory(QueryAccessoryVo vo);

	/**
	 * 查询工具
	 * @param vo
	 * @return
	 */
//	List<FirmOrderProduct> getAccessoryGJ(QueryAccessoryVo vo);
//	List<FirmOrderProduct> getAccessorySearch(QueryAccessoryVo vo);
	/**
	  * 根据配置信息取辅材列表
	  * @author hout
	  * @Title: queryMaterialProductByMaterialNickNames
	  * @Description: 根据code取辅材列表(这里用一句话描述这个方法的作用)
	  * @param  materialNickNames
	  * @param  materialState
	  * @return List<MaterialProduct>
	  * @date   2019年3月29日 上午11:25:52
	 */
//	List<MaterialProduct> queryMaterialProductByMaterialNickNames(String materialNickNames, Integer materialState, String cityCode);

	/**
	 * 获取辅材切割方式
	 * @param materialIds 辅材Ids
	 * @return 符合条件辅材
	 */
//	List<AccessoryVo> queryCutMethodByMaterialIds(List<Long> materialIds);


}
