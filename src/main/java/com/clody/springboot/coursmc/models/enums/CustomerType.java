package com.clody.springboot.coursmc.models.enums;

public enum CustomerType {
	PHYSICALPERSON(1, "Physical Customer"),
	LEGALPERSON(2, "Legal Customer");
	private int code;
	private String description;
	private CustomerType(int code, String description) {
		this.code = code;
		this.description = description;
	}
	public int getCod() {
		return code;
	}
	
	public String getDescription () {
		return description;
	}
	public static CustomerType toEnum(Integer code) {
		if (code == null) {
			return null;
		}
		for (CustomerType x : CustomerType.values()) {
			if (code.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Inavlid code: "+ code);
	}
}
