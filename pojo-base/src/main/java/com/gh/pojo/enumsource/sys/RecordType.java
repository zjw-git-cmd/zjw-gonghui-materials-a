package com.gh.pojo.enumsource.sys;


import com.gh.pojo.enumsource.EnumData;

/**
 * 
 * <b>记录类型</b><br/>
 *
 * @author Seal
 * @time 2018年7月5日 下午2:18:140
 */
public enum RecordType implements EnumData{

	REAL(1, "正式"),
	OPERATION(2, "运营"),
	TEST(3, "测试");
	
	private Integer key;
	private String value;
	
	public String getValue() {
		return value;
	}

	public Integer getKey() {
		return key;
	}

	private RecordType(Integer key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * 通过index获取enmu对象
	 * 
	 * @param oridal
	 * @return
	 */
	public static RecordType get(int oridal) {
		for (RecordType dot : RecordType.values()) {
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
	public static RecordType getByKey(int key) {
		for (RecordType dot : RecordType.values()) {
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
		for (RecordType dot : RecordType.values()) {
			if (dot.getKey().intValue() == key.intValue()) {
				return dot.getValue();
			}
		}
		return null;
	}
}