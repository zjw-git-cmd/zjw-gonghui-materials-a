package com.gh.pojo.entity.material;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gh.pojo.enumsource.sys.MaterialState;
import com.gh.pojo.enumsource.sys.MountingsType;
//import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * <b>辅材表</b><br/>
 */
public class MaterialProduct implements Serializable {

	private static final long serialVersionUID = -3335022023188387717L;

	private Long id;
	// 辅材状态
	private Integer materialState;
	// 记录类型:1,正式;2,运营;3,测试
	private Integer recordType;
	/**辅材类型：1，辅材；2，配件包；3，配件。**/
	//@ApiModelProperty("辅材类型：1，辅材；2，配件包；3，配件。")
	private Integer productType;
	// 材料类型
	private Integer materialType;
	// 品牌ID
	private Long brandId;
	// 品牌名称
	private String brandName;
	// 规格ID
	private Long specId;
	// 规格名称
	private String specName;
	// 辅材名称
	private String materialName;
	// 辅材昵称
	private String materialNickName;
	// 单位ID
	private Long unitId;
	// 单位名称
	private String unitName;
	// 供应商ID
	private Long supplierId;
	// 供应商名称
	private String supplierName;
	/**辅材颜色**/
	//@ApiModelProperty("辅材颜色")
	private String materialColor;
	/**辅材长度**/
	//@ApiModelProperty("辅材长度")
	private String materialLength;
	/**辅材重量**/
	//@ApiModelProperty("辅材重量")
	private String materialWeight;
	/**辅材宽度**/
	//@ApiModelProperty("辅材宽度")
	private String materialWidth;
	/**辅材高度**/
	//@ApiModelProperty("辅材高度")
	private String materialHeight;
	/**配件包信息**/
	//@ApiModelProperty("配件包信息")
	private String materialPartsPackageInfo;
	// 创建时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	// 更新时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	// 操作人ID
	private Long optUserId;
	// 操作人姓名
	private String optUserName;

	//包装单位
	private String packingUnit;
	//包装规格
	private BigDecimal packagingSpec;
	/////////////以下属性非数据库字段////////////////////////////

	//所在的供应商状态
	private String supplierState;
	// 辅材状态
	private String materialStateText;
	// 材料类型
	private String materialTypeText;
	// 配送范围
	private String supplierAreaStr;
	// 配送省范围
	private String provinceCode;
	// 配送市范围
	private String cityCode;
	// 辅材图片路径
	private String imgUrl;
	// 辅材图片ID
	private String imgFileId;
	// 查询关键词
	private String keyWork;
	/** 开始时间 **/
	private String startDate;
	/** 结束时间 **/
	private String endDate;
	//辅材类型id
	private Long materialNickId;
	//辅材颜色id
	private Long materialColorId;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getOptUserId() {
		return optUserId;
	}

	public void setOptUserId(Long optUserId) {
		this.optUserId = optUserId;
	}

	public String getOptUserName() {
		return optUserName;
	}

	public void setOptUserName(String optUserName) {
		this.optUserName = optUserName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getMaterialState() {
		return materialState;
	}

	public void setMaterialState(Integer materialState) {
		this.materialState = materialState;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Long getSpecId() {
		return specId;
	}

	public void setSpecId(Long specId) {
		this.specId = specId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialNickName() {
		return materialNickName;
	}

	public void setMaterialNickName(String materialNickName) {
		this.materialNickName = materialNickName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Integer getMaterialType() {
		return materialType;
	}

	public void setMaterialType(Integer materialType) {
		this.materialType = materialType;
	}

	public String getMaterialStateText() {
		materialStateText = MaterialState.getValueByKey(materialState);
		return materialStateText;
	}

	public void setMaterialStateText(String materialStateText) {
		this.materialStateText = materialStateText;
	}

	public String getMaterialTypeText() {
		materialTypeText = MountingsType.getValueByKey(materialType);
		return materialTypeText;
	}

	public void setMaterialTypeText(String materialTypeText) {
		this.materialTypeText = materialTypeText;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getPackingUnit() {
		return packingUnit;
	}

	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}

	public BigDecimal getPackagingSpec() {
		return packagingSpec;
	}

	public void setPackagingSpec(BigDecimal packagingSpec) {
		this.packagingSpec = packagingSpec;
	}

	public String getSupplierAreaStr() {
		return supplierAreaStr;
	}

	public void setSupplierAreaStr(String supplierAreaStr) {
		this.supplierAreaStr = supplierAreaStr;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getKeyWork() {
		return keyWork;
	}

	public void setKeyWork(String keyWork) {
		this.keyWork = keyWork;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSupplierState() {
		return supplierState;
	}

	public void setSupplierState(String supplierState) {
		this.supplierState = supplierState;
	}

	public String getImgFileId() {
		return imgFileId;
	}

	public void setImgFileId(String imgFileId) {
		this.imgFileId = imgFileId;
	}

	public String getMaterialColor() {
		return materialColor;
	}

	public void setMaterialColor(String materialColor) {
		this.materialColor = materialColor;
	}

	public String getMaterialLength() {
		return materialLength;
	}

	public void setMaterialLength(String materialLength) {
		this.materialLength = materialLength;
	}

	public String getMaterialWeight() {
		return materialWeight;
	}

	public void setMaterialWeight(String materialWeight) {
		this.materialWeight = materialWeight;
	}

	public String getMaterialWidth() {
		return materialWidth;
	}

	public void setMaterialWidth(String materialWidth) {
		this.materialWidth = materialWidth;
	}

	public String getMaterialHeight() {
		return materialHeight;
	}

	public void setMaterialHeight(String materialHeight) {
		this.materialHeight = materialHeight;
	}
	
	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public String getMaterialPartsPackageInfo() {
		return materialPartsPackageInfo;
	}

	public void setMaterialPartsPackageInfo(String materialPartsPackageInfo) {
		this.materialPartsPackageInfo = materialPartsPackageInfo;
	}

	public Integer getRecordType() {
		return recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}

	public Long getMaterialNickId() {
		return materialNickId;
	}

	public void setMaterialNickId(Long materialNickId) {
		this.materialNickId = materialNickId;
	}

	public Long getMaterialColorId() {
		return materialColorId;
	}

	public void setMaterialColorId(Long materialColorId) {
		this.materialColorId = materialColorId;
	}

	@Override
	public String toString() {
		return "MaterialProduct [id=" + id + ", materialState=" + materialState + ", recordType=" + recordType
				+ ", productType=" + productType + ", materialType=" + materialType + ", brandId=" + brandId
				+ ", brandName=" + brandName + ", specId=" + specId + ", specName=" + specName + ", materialName="
				+ materialName + ", materialNickName=" + materialNickName + ", unitId=" + unitId + ", unitName="
				+ unitName + ", supplierId=" + supplierId + ", supplierName=" + supplierName + ", materialColor="
				+ materialColor + ", materialLength=" + materialLength + ", materialWeight=" + materialWeight
				+ ", materialWidth=" + materialWidth + ", materialHeight=" + materialHeight
				+ ", materialPartsPackageInfo=" + materialPartsPackageInfo + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", optUserId=" + optUserId + ", optUserName=" + optUserName
				+ ", packingUnit=" + packingUnit + ", packagingSpec=" + packagingSpec + ", supplierState="
				+ supplierState + ", materialStateText=" + materialStateText + ", materialTypeText=" + materialTypeText
				+ ", supplierAreaStr=" + supplierAreaStr + ", provinceCode=" + provinceCode + ", cityCode=" + cityCode
				+ ", imgUrl=" + imgUrl + ", imgFileId=" + imgFileId + ", keyWork=" + keyWork + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
	
	
}
