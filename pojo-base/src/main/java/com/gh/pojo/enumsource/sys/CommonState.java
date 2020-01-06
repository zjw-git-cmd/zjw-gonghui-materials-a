package com.gh.pojo.enumsource.sys;

import com.gh.pojo.enumsource.EnumData;

/**
 *
 * <b>辅材、品牌、规格、计量单位状态</b><br/>
 *
 * @author Seal
 * @time 2018年4月9日 下午3:38:54
 */
public enum CommonState implements EnumData {

	NORMAL(0, "已上线"),
	OFFLINE(1, "已下线"),
	UNUSED(2, "未上线");
	private Integer key;
	private String value;

	public String getValue() {
		return value;
	}

	public Integer getKey() {
		return key;
	}

	CommonState(Integer key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * 通过index获取enmu对象
	 * 
	 * @param oridal
	 * @return
	 */
	public static CommonState get(int oridal) {
		for (CommonState dot : CommonState.values()) {
			if (oridal == dot.ordinal()) {
				return dot;
			}
		}
		return null;
	}

	/**
	 * 通过值获取enmu对象
	 * 
	 * @return
	 */
	public static CommonState getByKey(int key) {
		for (CommonState dot : CommonState.values()) {
			if (dot.getKey().intValue() == key) {
				return dot;
			}
		}
		return null;
	}
	
	/**
	 * 通过值获取值
	 * @param key
	 * @return
	 */
	public static String getValueByKey(Integer key) {
		if (key == null) {
			return null;
		}
		for (CommonState dot : CommonState.values()) {
			if (dot.getKey().intValue() == key.intValue()) {
				return dot.getValue();
			}
		}
		return null;
	}
}