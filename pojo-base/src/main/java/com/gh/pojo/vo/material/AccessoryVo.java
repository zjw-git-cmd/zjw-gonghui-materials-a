package com.gh.pojo.vo.material;

import com.gh.pojo.entity.material.MaterialProduct;

import java.math.BigDecimal;

public class AccessoryVo extends MaterialProduct {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//价格ID
	private Long goodsId;
	// 省编码
	private String provinceCode;
	// 省名称
	private String provinceName;
	// 市编码
	private String cityCode;
	// 市名称
	private String cityName;
	// 施工位置
	private Integer workPosition;
	// 成本单价
	private BigDecimal unitCostPrice;
	// 销售单价
	private BigDecimal unitSalesPrice;

	//临时Id
	private Long id;
	// 是否有裁切
	private String cutFlag;
	// 裁切信息
	private String cutMethod;


	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Integer getWorkPosition() {
		return workPosition;
	}
	public void setWorkPosition(Integer workPosition) {
		this.workPosition = workPosition;
	}
	public BigDecimal getUnitCostPrice() {
		return unitCostPrice;
	}
	public void setUnitCostPrice(BigDecimal unitCostPrice) {
		this.unitCostPrice = unitCostPrice;
	}
	public BigDecimal getUnitSalesPrice() {
		return unitSalesPrice;
	}
	public void setUnitSalesPrice(BigDecimal unitSalesPrice) {
		this.unitSalesPrice = unitSalesPrice;
	}

	public String getCutFlag() {
		return cutFlag;
	}

	public void setCutFlag(String cutFlag) {
		this.cutFlag = cutFlag;
	}

	public String getCutMethod() {
		return cutMethod;
	}

	public void setCutMethod(String cutMethod) {
		this.cutMethod = cutMethod;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
}
