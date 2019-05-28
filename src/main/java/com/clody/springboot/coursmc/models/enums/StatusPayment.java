package com.clody.springboot.coursmc.models.enums;

public enum StatusPayment {
	PENDING(1, "PENDING"),
	REMOVAL(2, "REMOVAL"),
	CANCELED(3, "CANCELED");
	private int code;
	private String description;
	
	private StatusPayment(int code, String description) {
		this.code = code;
		this.description = description;
	}
	public int getCode() {
		return code;
	}
	
	public String getDescription () {
		return description;
	}
	public static StatusPayment toEnum(Integer code) {
		if (code == null) {
			return null;
		}
		for (StatusPayment x : StatusPayment.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Inavlid code: "+ code);
	}
}
