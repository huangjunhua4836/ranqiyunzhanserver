package com.yl.soft.enums;

public interface FeedbackEnum {

	public enum Readed {
		已处理(1), 未处理(0),;

		private final int value;

		public int getValue() {
			return value;
		}

		private Readed(int value) {
			this.value = value;
		}

		public static Readed of(Integer value) {
			if (value != null) {
				switch (value) {
				case 1:
					return 已处理;
				case 0:
					return 未处理;
				}
			}
			return null;
		}
	}
}
