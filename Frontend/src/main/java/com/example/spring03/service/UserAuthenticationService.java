package com.example.spring03.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.spring03.UserDetails.MyUserDetails;
import com.example.spring03.auth.dto.authDTO;


public class UserAuthenticationService implements UserDetailsService {
	 
	@Inject
	SqlSessionTemplate sqlSession;

	@Inject
	BCryptPasswordEncoder passwordEncoder;
	
	public UserAuthenticationService() {}
	public UserAuthenticationService(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
		//this.session = session;
	}
	//로그인 인증을 처리하는 코드
	// 파라미터로 입력된 아이디 값에 해당하는 테이블의 레코드를 읽어옴
	//정보가 없으면 예외를 발생시킴
	//정보가 있으면 해당 정보가 map(dto)로 리턴됨
	
	
	
	@Override
	public UserDetails loadUserByUsername(String user_id) 
			throws 	UsernameNotFoundException {
//		Map<String, Object> user = sqlSession.selectOne("auth.selectUser", user_ID); // o
		MyUserDetails user = (MyUserDetails)sqlSession.selectOne("auth.selectUser_191010", user_id); // o
		
		// 아이디가 없으면 예외 발생
		if (user == null) throw new UsernameNotFoundException(user_id); //해당 사용자를 찾지 못했습니다.
		
		// 사용권한 목록
		
		List<GrantedAuthority> authority = new ArrayList<>();
		
		System.out.println(authority);
		//오라클에서는 필드명을 대문자로 적어야 함.
		//오라클에서는 Biginter 관련 오류가 발생할 수 있으므로 아래와 같이 처리

		
		authority.add(new SimpleGrantedAuthority(user.getAuthorities().toString()));
		
		return new authDTO(
				user.getUsername(),
				user.getPassword(),
				user.isEnabled(),
				user.isAccountNonExpired(),
				user.isCredentialsNonExpired(),
				user.isAccountNonLocked(),
				user.getAuthorities(),
				user.getUsername(),
				user.getT_group_id());			
				
		
//  	** When structure of user was Map<String, Object>
		
//		authority.add(
//			new SimpleGrantedAuthority(user.get("authority").toString())); // 필드명은 대문자로
//		
//		return new authDTO(
//				user.get("username").toString(),
//				user.get("password").toString(),
//				(Integer) Integer.valueOf(user.get("enabled").toString()) == 1, true, true, true, authority,
//				user.get("username").toString(), user.get("t_group_id").toString());
	}

}
