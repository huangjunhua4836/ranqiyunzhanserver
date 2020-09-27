package com.yl.soft.enums;

public enum LoginType {
	微信(1), QQ(2),;

	private final int value;

	public int getValue() {
		return value;
	}

	private LoginType(int value) {
		this.value = value;
	}

	public static LoginType of(Integer value) {
		if (value != null) {
			switch (value) {
			case 0:
				return 微信;
			case 1:
				return QQ;
			}
		}
		return null;
	}

}