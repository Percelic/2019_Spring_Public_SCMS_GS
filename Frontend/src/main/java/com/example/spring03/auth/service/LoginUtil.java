package com.example.spring03.auth.service;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;


public class LoginUtil {
	
	public static String encryptPassword(String user_id, String user_pw)throws Exception{
		
		if(user_pw == null) {
			return "";
		}
		byte[] hashValue = null;
		
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		
		md.reset();
		md.update(user_id.getBytes());
		
		hashValue = md.digest(user_pw.getBytes());
		
		return new String(Base64.encodeBase64(hashValue));
		
	}

}
