package com.gh.db;

import com.gh.util.api.ParameterUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 分页工具类
 * 
 * @author chenyi
 * @param <K>
 * @param <K>
 * @email 228112142@qq.com
 * @date 2016年11月4日 下午12:59:00
 */
public class RCPageUtils<K> implements Serializable { //implements IPageUtils<K>
	private static final long serialVersionUID = 1L;
	//总记录数
	private long totalCount;
	//每页记录数
	private int pageSize;
	//总页数
	private int totalPage;
	//当前页数
	private int currPage;
	//列表数据
	private List<K> list;
	//其他返回数据
	private Map<String,Object> dataMap;

	public RCPageUtils(){}

	/**
	 * 分页
	 * @param list        列表数据
	 * @param totalCount  总记录数
	 * @param pageSize    每页记录数
	 * @param currPage    当前页数
	 */
	public RCPageUtils(List<K> list, long totalCount, int pageSize, int currPage) {
		this.list = list;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.currPage = currPage;
		this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
	}

	public RCPageUtils(List<K> list, Map<String,Object> dataMap, long totalCount, int pageSize, int currPage) {
		this.list = list;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.currPage = currPage;
		this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
		this.dataMap = dataMap;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	
	//private Class<T> clazz;  
	public List<K> getList() {
		
		
		
		return list;
	}

	
	public void setList(List<K> list) {
		this.list = list;		
	}

	
	public List<K> getList(Class<K> clz) {
		List<K> list = ParameterUtil.mapToList(this.getList(),clz);
		return list;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
}
