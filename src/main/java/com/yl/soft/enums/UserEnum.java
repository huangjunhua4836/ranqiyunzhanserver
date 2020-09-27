package com.yl.soft.enums;

public class UserEnum {
	public enum State {
		启用(1), 禁用(0),;

		private final int value;

		public int getValue() {
			return value;
		}

		private State(int value) {
			this.value = value;
		}

		public static State of(Integer value) {
			if (value != null) {
				switch (value) {
				case 0:
					return 禁用;
				case 1:
					return 启用;
				}
			}
			return null;
		}

	}

	public enum Qualification {
		已认证(1), 未认证(0),;

		private final int value;

		public int getValue() {
			return value;
		}

		private Qualification(int value) {
			this.value = value;
		}

		public static Qualification of(Integer value) {
			if (value != null) {
				switch (value) {
				case 1:
					return 已认证;
				case 0:
					return 未认证;
				}
			}
			return null;
		}

	}

	public enum Gender {
		未指定(0), 男(1), 女(2),;

		private final int value;

		public int getValue() {
			return value;
		}

		private Gender(int value) {
			this.value = value;
		}

		public static Gender of(Integer value) {
			if (value != null) {
				switch (value) {
				case 0:
					return 未指定;
				case 1:
					return 男;
				case 2:
					return 女;
				}
			}
			return null;
		}
	}

	public enum Type {
		医生(1), 其他(0),;

		private final int value;

		public int getValue() {
			return value;
		}

		private Type(int value) {
			this.value = value;
		}

		public static Type of(Integer value) {
			if (value != null) {
				switch (value) {
				case 0:
					return 其他;
				case 1:
					return 医生;
				}
			}
			return null;
		}
	}

	public enum Employee {
		内部员工(1), 非员工(0),;

		private final int value;

		public int getValue() {
			return value;
		}

		private Employee(int value) {
			this.value = value;
		}
	}

	public enum PhoneVerified {
		已验证(1), 未验证(0),;

		private final int value;

		public int getValue() {
			return value;
		}

		private PhoneVerified(int value) {
			this.value = value;
		}
	}
}
