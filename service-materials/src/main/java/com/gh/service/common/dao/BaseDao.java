package com.gh.service.common.dao;

import com.gh.db.IBaseDao;

import java.util.List;
import java.util.Map;

/**
 * 基础Dao(还需在XML文件里，有对应的SQL语句)
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2016年9月18日 上午9:31:36
 */

public interface BaseDao<T> extends IBaseDao<T>  /*extends Mapper<T>, MySqlMapper<T>*/{
	//添加
	void save(T t);
	//更新
	int update(T t);

	//======================以下暂未使用====================================
	
	void save(Map<String, Object> map);
	//批量添加
	void saveBatch(List<T> list);

	
	int update(Map<String, Object> map);
	//删除
	int delete(Object id);
	
	int delete(Map<String, Object> map);
	//批量删除
	int deleteBatch(Object[] id);

	T queryObject(Object id);

	T get(Object id);
	
	List<T> queryList(Map<String, Object> map);
	
	List<T> getList(Map<String, Object> map);
	
	List<T> queryList(Object id);
	
	int queryTotal(Map<String, Object> map);

	int getCount(Map<String, Object> map);

	int queryTotal();
}