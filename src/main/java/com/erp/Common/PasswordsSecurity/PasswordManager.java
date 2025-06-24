package com.erp.Common.PasswordsSecurity;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PasswordManager {

	// details for encryption and decryption
	private static final String SECRET_KEY = "123456789";
	private static final String SALTVALUE = "abcdefg";
	private static byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	private static IvParameterSpec ivspec = new IvParameterSpec(iv);

	/* Encryption Method */
	public static String encrypt(String decryptedValue) {
		try {
			/* Create factory for secret keys. */
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			/* PBEKeySpec class implements KeySpec interface. */
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALTVALUE.getBytes(), 65536, 256);
			SecretKey secreteKey = factory.generateSecret(spec);
			SecretKeySpec secretKeySpec = new SecretKeySpec(secreteKey.getEncoded(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivspec);
			/* Retruns encrypted value. */
			return Base64.getEncoder().encodeToString(cipher.doFinal(decryptedValue.getBytes(StandardCharsets.UTF_8)));
		} catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException
				| InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException
				| NoSuchPaddingException e) {
			System.out.println("Error occured during encryption: " + e.toString());
		}
		return null;
	}

	/* Decryption Method */
	public static String decrypt(String encryptedValue) {
		try {
			/* Create factory for secret keys. */
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			/* PBEKeySpec class implements KeySpec interface. */
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALTVALUE.getBytes(), 65536, 256);
			SecretKey secreteKey = factory.generateSecret(spec);
			SecretKeySpec secretKeySpec = new SecretKeySpec(secreteKey.getEncoded(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivspec);
			/* Retruns decrypted value. */
			return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedValue)));
		} catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException
				| InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException
				| NoSuchPaddingException e) {
			System.out.println("Error occured during decryption: " + e.toString());
		}
		return null;
	}
}
