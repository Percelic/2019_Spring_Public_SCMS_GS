package com.example.spring03.UserDetails;

import java.security.PrivateKey;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.spring03.service.UserAuthenticationService;


public class MyAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserAuthenticationService  userAuthService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		PrivateKey key = (PrivateKey) request.getSession().getAttribute("__rsaPrivateKey__");
		
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		UserDetails user = null;
		
		try {
			 user = (UserDetails)userAuthService.loadUserByUsername(RSADecryption(key,username)); 
			 
			if(!passwordEncoder.matches(RSADecryption(key,password), user.getPassword()))
				throw new BadCredentialsException("비밀번호 불일치");
			
		}	 catch(UsernameNotFoundException e) {
				e.printStackTrace();
				throw new UsernameNotFoundException(e.getMessage());
		}	catch(BadCredentialsException e) {
			e.printStackTrace();
			throw new BadCredentialsException(e.getMessage());
		}	catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
				 
		
		return new UsernamePasswordAuthenticationToken(user, RSADecryption(key,password), user.getAuthorities());
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public MyAuthenticationProvider() {
		// TODO Auto-generated constructor stub
	}
	
	private String RSADecryption(PrivateKey key, String encrypted) {
		
		if(key == null) {
			//ra.addFlashAttribute("resultMsg","비정상적인 접근입니다.");
			//return "redirect:/"; //"/User_auth/Login";
			throw new UsernameNotFoundException(encrypted);
		}
		request.getSession().removeAttribute("__rsaPrivateKey__"); 

		Cipher cipher;
		byte[] decryptedBytes;
		String decrypted = "";
		
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, key);
			decryptedBytes = cipher.doFinal(hexToByteArray(encrypted));
			decrypted = new String(decryptedBytes, "UTF-8");
		} catch(Exception e) {
			throw new UsernameNotFoundException(encrypted);
		}
		
		return decrypted;
	}
	
	private byte[] hexToByteArray(String hex) {
        if (hex == null || hex.length() % 2 != 0) {
            return new byte[] {};
        }
 
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            byte value = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
            bytes[(int) Math.floor(i / 2)] = value;
        }
        return bytes;
    }

}
