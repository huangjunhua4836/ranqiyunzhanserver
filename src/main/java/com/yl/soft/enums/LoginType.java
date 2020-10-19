package com.yl.soft.enums;

public enum LoginType {
	微信(1), QQ(2),苹果(3);

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
			case 1:
				return 微信;
			case 2:
				return QQ;
			case 3:
				return 苹果;
			}
		}
		return null;
	}

}