package com.gh.service.material.dao;

import com.gh.pojo.entity.firm.MaterialDetailImport;
import com.gh.pojo.entity.material.MaterialProduct;
import com.gh.pojo.vo.material.AccessoryVo;
import com.gh.service.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * <b>辅材</b><br/>
 *
 * @author Seal
 * @time 2018年4月9日 下午2:21:22
 */
@Mapper
public interface MaterialProductDao extends BaseDao<MaterialProduct> {


	/**
	 * 根据品牌ID/规格ID/计量单位ID更新参数名称
	 * @param params
	 */
	void updateMaterialByParam(Map<String, Object> params);





	//=================以下暂时未使用================================



	/**
	 * 添加辅材
	 * @param materialProduct
	 * @return id
	 */
	void insertMaterialProduct(MaterialProduct materialProduct);
    
	/**
	 * 根据 辅材名称 品牌 规格 查询辅材 用于排重
	 * @param materialDetailImport
	 * @return findMaterialProduct
	 */
	MaterialProduct findMaterialProduct(MaterialDetailImport materialDetailImport);

	List<MaterialProduct> queryMaterialProductByGoodsIds(@Param("ids") List<Long> ids, @Param("goodsType") Integer goodsType);

	List<MaterialProduct> queryMaterialProductByIdsAndCityCode(@Param("ids") List<Long> ids, @Param("materialState") Integer materialState,
                                                               @Param("cityCode") String cityCode);


	List<MaterialProduct> queryMaterialProduct(MaterialProduct materialProduct);

	int queryMaterialProductTotal(MaterialProduct materialProduct);

	/**
	 * 根据 辅材名称、辅材昵称、辅材类型、 品牌、 规格、供应商 查询辅材 用于排重
	 * @param materialProduct 查询对象
	 * @return 符合条件的辅材
	 */
	List<MaterialProduct> exactQueryMaterialProduct(MaterialProduct materialProduct);

	/**
	 * 更新辅材状态
	 * @param materialState 辅材状态
	 * @param id 辅材id
	 * @return 更新个数
	 */
	int updateProductState(@Param("materialState") Integer materialState, @Param("id") Long id, @Param("optUserId") Long optUserId, @Param("optUserName") String optUserName);

	/**
	 * 批量更新辅材状态
	 * @param materialState 辅材状态
	 * @param ids 辅材id
	 * @return 更新个数
	 */
	int batchUpdateProductState(@Param("materialState") Integer materialState, @Param("ids") List<Long> ids, @Param("optUserId") Long optUserId, @Param("optUserName") String optUserName);

	/**
	 * 更新辅材状态
	 * @param materialState 辅材状态
	 * @param supplierId 辅材supplierId
	 * @return 更新个数
	 */
	int updateProductStateBySupplierId(@Param("materialState") Integer materialState, @Param("supplierId") Long supplierId, @Param("optUserId") Long optUserId, @Param("optUserName") String optUserName);

	/**
	 * 获取配件
	 * @param params
	 * @return
	 */
	List<AccessoryVo> getAccessory(Map<String, Object> params);

	List<AccessoryVo> getAccessorySearch(Map<String, Object> params);

	List<MaterialProduct> queryMaterialProductByMaterialNickNames(@Param("materialNickNames") String materialNickNames, @Param("materialState") Integer materialState,
                                                                  @Param("cityCode") String cityCode);

	/**
	  * 更新辅材库供应商名称
	  * @author hout
	  * @Title: updateSupplierName
	  * @param supplierId
	  * @param supplierName
	  * @date   2019年4月26日 下午4:58:48
	 */
	void updateSupplierName(@Param("supplierId") Long supplierId, @Param("supplierName") String supplierName);


	List<Map<String, Long>> getCountByObj(MaterialProduct materialProduct);

	/**
	 * 根据供应商id级联更新 配件包 配件包价格
	  * @author hout
	  * @Title: updateMaterialState
	  * @param  state
	  * @param  supplierId
	  * @param  userId
	  * @param  userName
	  * @date   2019年5月14日 下午3:28:09
	 */
	void updateMaterialState(@Param("state") Integer state, @Param("supplierId") Long supplierId, @Param("userId") Long userId, @Param("userName") String userName);

	/**
	 * 级联更新配件包 配件包价格
	  * @author hout
	  * @Title: updateMountingsState
	  * @param  materialState
	  * @param  id
	  * @param  optUserId
	  * @param  optUserName
	  * @date   2019年5月14日 下午8:33:20
	 */
	void updateMountingsState(@Param("materialState") Integer materialState, @Param("id") Long id, @Param("optUserId") Long optUserId, @Param("optUserName") String optUserName);

	/**
	 * 级联批量更新配件包 配件包价格
	 * @author hout
	 * @Title: updateMountingsState
	 * @param  materialState
	 * @param  ids
	 * @param  optUserId
	 * @param  optUserName
	 * @date   2019年5月14日 下午8:33:20
	 */
	void batchUpdateMountingsState(@Param("materialState") Integer materialState, @Param("ids") List<Long> ids, @Param("optUserId") Long optUserId, @Param("optUserName") String optUserName);
	
	List<AccessoryVo> queryCutMethodByMaterialIds(@Param("materialIds") List<Long> materialIds);

}
