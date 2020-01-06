package com.gh.pojo.entity.firm;

import java.io.Serializable;

/**
 * 导入辅材信息表
 * @author hout
 * @author Administrator
 *
 */
public class MaterialDetailImport implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**辅材id**/
	private Long materialId;
	/** 辅材名称 **/
	private String materialName;
	/** 辅材昵称 **/
	private String materialNickName;
	/** 辅材品牌名称 **/
	private String brandName;
	/** 辅材详情 **/
	private String specName;
	/** 辅材计量单位 **/
	private String unitName;
	/** 材料类型 **/
	private String materialType;
    /** 供应商名称 **/
	private String supplierName;
	/** 联系人 **/
	private String contactPerson;
	/** 联系方式 **/
	private String contactPhone;
	/** 联系地址 **/
	private String address;
	/** 付款方式 **/
    private String payType;
    /** 成本价 **/
    private String unitCostPrice;
    /** 销售价 **/
    private String unitSalesPrice;
    /** 工艺名称 **/
    private String craftName;
    /** 工艺ID **/
    private Long workCraftId;
    /** 施工位置 **/
    private Integer workPosition;
    /** 单位用量 **/
    private String usageAmount;
    /** 施工项价格 **/
    private String workItemPrice;
    /**唯一码(辅材名称+品牌+规格)**/
	private String uniqueness;
	/** 供应商sql **/
    private String saveSupplier;
    /** 品牌sql **/
    private String saveMaterialBrand;
    /** 计量sql **/
    private String saveMeasureUnit;
    /** 规格sql **/
    private String saveMaterialSpec;
    
    
    
	public Long getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}
	public String getSaveMaterialSpec() {
		return saveMaterialSpec;
	}
	public void setSaveMaterialSpec(String saveMaterialSpec) {
		this.saveMaterialSpec = saveMaterialSpec;
	}
	public String getSaveMeasureUnit() {
		return saveMeasureUnit;
	}
	public void setSaveMeasureUnit(String saveMeasureUnit) {
		this.saveMeasureUnit = saveMeasureUnit;
	}
	public String getSaveMaterialBrand() {
		return saveMaterialBrand;
	}
	public void setSaveMaterialBrand(String saveMaterialBrand) {
		this.saveMaterialBrand = saveMaterialBrand;
	}
	public String getSaveSupplier() {
		return saveSupplier;
	}
	public void setSaveSupplier(String saveSupplier) {
		this.saveSupplier = saveSupplier;
	}
	public String getUniqueness() {
		return uniqueness;
	}
	public void setUniqueness(String uniqueness) {
		this.uniqueness = uniqueness;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
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
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getMaterialType() {
		return materialType;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getUnitCostPrice() {
		return unitCostPrice;
	}
	public void setUnitCostPrice(String unitCostPrice) {
		this.unitCostPrice = unitCostPrice;
	}
	public String getUnitSalesPrice() {
		return unitSalesPrice;
	}
	public void setUnitSalesPrice(String unitSalesPrice) {
		this.unitSalesPrice = unitSalesPrice;
	}
	public String getCraftName() {
		return craftName;
	}
	public void setCraftName(String craftName) {
		this.craftName = craftName;
	}
	public Integer getWorkPosition() {
		return workPosition;
	}
	public void setWorkPosition(Integer workPosition) {
		this.workPosition = workPosition;
	}
	public String getUsageAmount() {
		return usageAmount;
	}
	public void setUsageAmount(String usageAmount) {
		this.usageAmount = usageAmount;
	}
	public String getWorkItemPrice() {
		return workItemPrice;
	}
	public void setWorkItemPrice(String workItemPrice) {
		this.workItemPrice = workItemPrice;
	}
	public Long getWorkCraftId() {
		return workCraftId;
	}
	public void setWorkCraftId(Long workCraftId) {
		this.workCraftId = workCraftId;
	}
	public String getMaterialNickName() {
		return materialNickName;
	}
	public void setMaterialNickName(String materialNickName) {
		this.materialNickName = materialNickName;
	}
    
	
	
	

	
}
