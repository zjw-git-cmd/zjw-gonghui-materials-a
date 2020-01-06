package com.gh.pojo.entity.material;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gh.pojo.enumsource.sys.MaterialState;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <b>辅材规格表</b><br/>
 *
 */
public class MaterialSpec implements Serializable {

	private static final long serialVersionUID = 4992675801649422698L;

	private Long id;
	// 规格状态  规格状态。0，已上线；1，已下线；2，未上架。
	private Integer specState;
	// 规格名称
	private String specName;
	// 排序
	private Integer sort;
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
	//数据类型：1，正式；2，运营；3，测试
	private Integer recordType;

	/////////////以下属性非数据库字段////////////////////////////
		
	// 状态名称
	private String specStateText;
	
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

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public Integer getSpecState() {
		return specState;
	}

	public void setSpecState(Integer specState) {
		this.specState = specState;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getSpecStateText() {
		specStateText = MaterialState.getValueByKey(specState);
		return specStateText;
	}

	public void setSpecStateText(String specStateText) {
		this.specStateText = specStateText;
	}

	public Integer getRecordType() {
		return recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}

}
