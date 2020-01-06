package com.gh.service.common.utils;

import com.gh.jsoup.SQLFilter;
import com.gh.util.api.ParameterUtil;
import org.apache.commons.collections.map.HashedMap;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * 适用于vo查询
 * @author huangweidan
 * @email huangwd@gonghuikeji.com
 * @date 2018-04-25 18:4:20
 */
@SuppressWarnings("serial")
public class PageQuery<T> implements Serializable {
	
	public PageQuery(){}

    @SuppressWarnings("unchecked")
	public PageQuery(Map<String, Object> params, Class<T> clz){
        Field[] fields = clz.getDeclaredFields();
        Map<String, Object> clzMap = new HashedMap();
        String fieldName;
        if(fields!=null && fields.length>0)
            for (Field f : fields){
                fieldName = f.getName();
                if(params.containsKey(fieldName))
                    clzMap.put(fieldName,params.get(fieldName));
            }
        this.paramModel = (T) ParameterUtil.mapToBean(clzMap,clz);
        this.page = Integer.parseInt(params.get("page").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        this.sidx =  SQLFilter.sqlInject(params.get("sidx").toString(),true);
        this.order = SQLFilter.sqlInject(params.get("order").toString(),false);
    }

    /**
     * 泛型
     */
    private T paramModel;

    /**
     * 页数
     */
    private Integer page = 1;

    /**
     * 一页大小
     */
    private Integer limit = 10;

    /**
     * 排序字段
     */
    private String order;
    /**
     * 排序字段
     */
    private String sidx;

    public T getParamModel() {
        return paramModel;
    }

    public void setParamModel(T paramModel) {
        this.paramModel = paramModel;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }


}
