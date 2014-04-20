package com.amos.concurrent;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("md5");
		messageDigest.update("abcd".getBytes());
		System.out.println(messageDigest.digest());
		
		MessageDigest messageDigest2 = MessageDigest.getInstance("sha");
		messageDigest2.update("abcd".getBytes());
		System.out.println(messageDigest2.digest());
		
		
	}
	
	
	
}
