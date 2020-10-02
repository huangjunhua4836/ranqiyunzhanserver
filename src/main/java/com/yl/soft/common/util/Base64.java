package com.yl.soft.common.util;

public class Base64 {

	/** Base64编码表。 */
	private static char base64Code[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
			'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
			'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', '+', '/', };

	/**
	 * 构造方法私有化，防止实例化。
	 */
	private Base64() {
		super();
	}

	/**
	 * Base64编码。将字节数组中字节3个一组编码成4个可见字符。
	 * 
	 * @param bytes 需要被编码的字节数据。
	 * @return 编码后的Base64字符串。
	 */
	public static String encode(byte[] bytes) {
		int a = 0;

		// 按实际编码后长度开辟内存，加快速度
		StringBuffer buffer = new StringBuffer(((bytes.length - 1) / 3) << 2 + 4);

		// 进行编码
		for (int i = 0; i < bytes.length; i++) {
			a |= (bytes[i] << (16 - i % 3 * 8)) & (0xff << (16 - i % 3 * 8));
			if (i % 3 == 2 || i == bytes.length - 1) {
				buffer.append(Base64.base64Code[(a & 0xfc0000) >>> 18]);
				buffer.append(Base64.base64Code[(a & 0x3f000) >>> 12]);
				buffer.append(Base64.base64Code[(a & 0xfc0) >>> 6]);
				buffer.append(Base64.base64Code[a & 0x3f]);
				a = 0;
			}
		}

		// 对于长度非3的整数倍的字节数组，编码前先补0，编码后结尾处编码用=代替，
		// =的个数和短缺的长度一致，以此来标识出数据实际长度
		if (bytes.length % 3 > 0) {
			buffer.setCharAt(buffer.length() - 1, '=');
		}
		if (bytes.length % 3 == 1) {
			buffer.setCharAt(buffer.length() - 2, '=');
		}
		return buffer.toString();
	}

}
