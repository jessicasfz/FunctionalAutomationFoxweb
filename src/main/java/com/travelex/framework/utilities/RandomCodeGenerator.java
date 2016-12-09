package com.travelex.framework.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomCodeGenerator {

	public static String randomNameGenerator() {
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("dd/MMM/YYYY hh:mm:ss:ms");
		String newRandomString = f.format(date).replace("/", "").replace(" ", "").replace(":", "");
		String lastChars = "0123456789";
		Random random = new Random();
		StringBuilder randomNumber = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			sb1.append(lastChars.charAt(random.nextInt(lastChars.length())));
		}
		randomNumber.append(newRandomString);
		randomNumber.append(sb1);
		return randomNumber.toString();
	}

	public static String getTimeStamp() {
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("dd/MMM/YYYY hh:mm:ss:ms");
		return f.format(date).replace("/", "").replace(" ", "").replace(":", "");
	}

	public static String randomNumberGenerator(int length) {
		String Chars = "123456789";
		Random random = new Random();
		StringBuilder randomNumber = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb1.append(Chars.charAt(random.nextInt(Chars.length())));
		}
		randomNumber.append(sb1);
		return randomNumber.toString();
	}

	public static String randomNameGeneratorUsingCharacter() {
		String firstFourChars = "AUTO";
		String secondSevenChars = "abcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		StringBuilder sb1 = new StringBuilder();
		sb1.append(firstFourChars);
		for (int i = 0; i < 5; i++) {
			sb1.append(secondSevenChars.charAt(random.nextInt(secondSevenChars.length())));
		}
		return sb1.toString();

	}

	public static String randomNameGeneratorUsingCharacter(int numOfChars) {
		String allChars = "abcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		StringBuilder sb1 = new StringBuilder();
		for (int i = 1; i <= numOfChars; i++) {
			sb1.append(allChars.charAt(random.nextInt(allChars.length())));
		}
		return sb1.toString();
	}

}
