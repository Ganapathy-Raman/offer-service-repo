package com.rts.tap.utils;

import java.security.SecureRandom;

public class OtpUtils {

	public static String generateOTP() {
		SecureRandom random = new SecureRandom();
		int otp = 100000 + random.nextInt(900000);
		return String.valueOf(otp);
	}
}
