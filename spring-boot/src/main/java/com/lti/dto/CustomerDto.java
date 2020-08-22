package com.lti.dto;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

public class CustomerDto {
	
	private String name;
	private LocalDate dateOfBirth;
	private String email;
	private String password;
	
	private MultipartFile profilePic;

	public String getName() {
		return name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public MultipartFile getProfilePic() {
		return profilePic;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setProfilePic(MultipartFile profilePic) {
		this.profilePic = profilePic;
	}
	
	
}
