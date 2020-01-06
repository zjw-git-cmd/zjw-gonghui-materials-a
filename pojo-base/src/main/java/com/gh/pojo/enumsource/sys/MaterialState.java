package com.gh.pojo.enumsource.sys;

/**
 * 
 * <b>辅材、品牌、规格、计量单位状态</b><br/>
 *
 * @author Seal
 * @time 2018年4月9日 下午3:38:54
 */
public enum MaterialState {
	ONLINE(0, "已上线"),
	OFFLINE(1, "已下线"),
	NOTONLINE(2, "未上线");

	private Integer key;
	private String value;
	
	public String getValue() {
		return value;
	}

	public Integer getKey() {
		return key;
	}

	private MaterialState(Integer key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * 通过index获取enmu对象
	 * 
	 * @param oridal
	 * @return
	 */
	public static MaterialState get(int oridal) {
		for (MaterialState dot : MaterialState.values()) {
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
	public static MaterialState getByKey(int key) {
		for (MaterialState dot : MaterialState.values()) {
			if (dot.getKey().intValue() == key) {
				return dot;
			}
		}
		return null;
	}
	
	/**
	 * 通过键获取值
	 */
	public static String getValueByKey(Integer key) {
		if (key == null) {
			return null;
		}
		for (MaterialState dot : MaterialState.values()) {
			if (dot.getKey().intValue() == key.intValue()) {
				return dot.getValue();
			}
		}
		return null;
	}
}