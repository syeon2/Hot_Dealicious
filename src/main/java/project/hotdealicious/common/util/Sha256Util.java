package project.hotdealicious.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Sha256Util {

	private static final String ENCRYPTION_TYPE = "SHA-256";

	public static String getEncrypt(String source, String salt) {
		return getEncrypt(source, salt.getBytes());
	}

	public static String getEncrypt(String source, byte[] salt) {
		String result = "";

		byte[] bytes = new byte[source.getBytes().length + salt.length];

		try {
			MessageDigest md = MessageDigest.getInstance(ENCRYPTION_TYPE);
			md.update(bytes);

			byte[] byteData = md.digest();

			StringBuilder sb = new StringBuilder();
			for (byte byteDatum : byteData) {
				sb.append(Integer.toString((byteDatum & 0xFF) + 0x100, 16).substring(1));
			}

			result = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("SHA-256 암호화 에러");
		}

		return result;
	}

	public static String generateSalt() {
		Random random = new Random();

		byte[] salt = new byte[8];
		random.nextBytes(salt);

		StringBuilder sb = new StringBuilder();
		for (byte b : salt) {
			sb.append(String.format("%02x", b));
		}

		return sb.toString();
	}
}
