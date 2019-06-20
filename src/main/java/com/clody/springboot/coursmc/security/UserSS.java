package com.clody.springboot.coursmc.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.clody.springboot.coursmc.models.enums.Profile;

public class UserSS implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	
	
	
	public UserSS() {}
	
	
	
	public UserSS(Integer id, String email, String password,Set<Profile> profiles) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = profiles.stream().map(profile -> new SimpleGrantedAuthority(profile.getDescription())).collect(Collectors.toList());
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return authorities;
	}
	public Integer getId() {		
		return id;
	}

	@Override
	public String getPassword() {		
		return password;
	}

	@Override
	public String getUsername() {		
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}



	public boolean hasRole(Profile profile) {		 
		return getAuthorities().contains(new SimpleGrantedAuthority(profile.getDescription()));
	}

}
