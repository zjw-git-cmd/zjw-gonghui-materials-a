package com.gh.pojo.vo.material;

import com.gh.pojo.entity.material.MaterialBrand;

/**
 * 接收前端页面的数据
 * 适合前端数据的查询
 */
public class MaterialBrandVo extends MaterialBrand {
    //接收传递进来的页码
    private Integer currPage;

    public Integer getCurrentPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }
}
