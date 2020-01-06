package com.gh.db;

import com.gh.util.api.ParameterUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 * 
 * @author chenyi
 * @param <K>
 * @param <K>
 * @email 228112142@qq.com
 * @date 2016年11月4日 下午12:59:00
 */
public class PageUtils<K> implements Serializable { //implements IPageUtils<K>
	private static final long serialVersionUID = 1L;
	//总记录数
	private long totalCount;
	//每页记录数
	private int pageSize;
	//总页数
	private int totalPage;
	//当前页数
	private int currPage;

	//====================================
	//统计上线数量
	private int onLineCount;
	//统计下线数量
	private int offLineCount;
	//====================================

	//列表数据
	private List<K> list;





	public int getOnLineCount() {
		return onLineCount;
	}

	public void setOnLineCount(int onLineCount) {
		this.onLineCount = onLineCount;
	}

	public int getOffLineCount() {
		return offLineCount;
	}

	public void setOffLineCount(int offLineCount) {
		this.offLineCount = offLineCount;
	}

	public PageUtils(){}
	
	/**
	 * 分页
	 * @param list        列表数据
	 * @param totalCount  总记录数
	 * @param pageSize    每页记录数
	 * @param currPage    当前页数
	 */
	public PageUtils(List<K> list, long totalCount, int pageSize, int currPage) {
		this.list = list;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.currPage = currPage;
		this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
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

	
}
