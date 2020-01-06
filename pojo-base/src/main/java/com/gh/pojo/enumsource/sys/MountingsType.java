package com.gh.pojo.enumsource.sys;

import com.gh.pojo.enumsource.EnumData;

/**
 * 
 * <b>材料类型</b><br/>
 *
 * @author Seal
 * @time 2018年4月10日 下午5:34:10
 */
public enum MountingsType implements EnumData {

	SG(1, "水工材料"),
	DG(2, "电工材料"),
	MG(3, "木工材料"),
	WG(4, "瓦工材料"),
	YG(5, "油工材料"),
	GJ(6, "工具");
	
	private Integer key;
	private String value;
	
	public String getValue() {
		return value;
	}

	public Integer getKey() {
		return key;
	}

	MountingsType(Integer key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * 通过index获取enmu对象
	 * 
	 * @param oridal
	 * @return
	 */
	public static MountingsType get(int oridal) {
		for (MountingsType dot : MountingsType.values()) {
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
	public static MountingsType getByKey(int key) {
		for (MountingsType dot : MountingsType.values()) {
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
		for (MountingsType dot : MountingsType.values()) {
			if (dot.getKey().intValue() == key.intValue()) {
				return dot.getValue();
			}
		}
		return null;
	}
	
	/**
	 * 根据值取Key
	 * @param value
	 * @return
	 */
	public static Integer getKeyByValue(String value) {
		for (MountingsType dot : MountingsType.values()) {
			if (dot.getValue().equals(value)) {
				return dot.getKey();
			}
		}
		return null;
	}
	
}