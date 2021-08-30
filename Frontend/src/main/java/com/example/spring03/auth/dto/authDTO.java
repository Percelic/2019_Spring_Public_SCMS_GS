package com.example.spring03.auth.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class authDTO extends User {
	
	private String user_id;
	private String t_group_id;
	
	public authDTO(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, String user_id, String t_group_id) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.user_id = user_id;
		this.t_group_id = t_group_id;
	}
	
	public String getT_group_id() {
		return t_group_id;
	}

	public void setT_group_id(String t_group_id) {
		this.t_group_id = t_group_id;
	}

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "authDTO [user_id=" + user_id + "]";
	}
	
		

	
	
}
