package com.gh.pojo.entity.material;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gh.pojo.enumsource.sys.MaterialState;

import java.io.Serializable;
import java.util.Date;

/**
 * 辅材品牌表(sys_material_brand)
 *
 */
public class MaterialBrand implements Serializable {

    /**主键**/
    private Long id;
    /**品牌状态(数据状态)。0，已上线；1，已下线；2，未上线。**/
    private Integer brandState;
    /**记录类型(数据类型):1,正式;2,运营;3,测试**/
    private Integer recordType;
    /**品牌名称**/
    private String brandName;
    /**排序**/
    private Integer sort;
    /**创建时间**/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**更新时间**/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**操作人ID**/
    private Long optUserId;
    /**操作人姓名**/
    private String optUserName;

    /////////////以下属性非数据库字段////////////////////////////

    // 状态名称
    private String brandStateText;



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

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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

    public Integer getBrandState() {
        return brandState;
    }

    public void setBrandState(Integer brandState) {
        this.brandState = brandState;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getBrandStateText() {
        brandStateText = MaterialState.getValueByKey(brandState);
        return brandStateText;
    }

    public void setBrandStateText(String brandStateText) {
        this.brandStateText = brandStateText;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    @Override
    public String toString() {
        return "MaterialBrand{" +
                "id=" + id +
                ", brandState=" + brandState +
                ", recordType=" + recordType +
                ", brandName='" + brandName + '\'' +
                ", sort=" + sort +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", optUserId=" + optUserId +
                ", optUserName='" + optUserName + '\'' +
                ", brandStateText='" + brandStateText + '\'' +
                '}';
    }
}
