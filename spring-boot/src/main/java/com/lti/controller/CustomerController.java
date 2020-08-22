package com.lti.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.controller.CustomerController.Status.StatusType;
import com.lti.dto.CustomerDto;
import com.lti.dto.LoginDto;
//import com.lti.controller.CustomerController.Status.StatusType;
import com.lti.entity.Customer;
import com.lti.exception.CustomerServiceException;
import com.lti.service.CustomerService;

@RestController
@CrossOrigin
public class CustomerController {

	@Autowired
	//@Qualifier("custServ")
	private CustomerService customerService;
	@PostMapping(path="/register", consumes = "multipart/form-data")
	public Status register( CustomerDto customerDto) {
		try {
			Customer customer = new Customer();
			//instead of copying the data from the dto to entity manually,
			//we can use BeanUtill helper class
			BeanUtils.copyProperties(customerDto, customer);
			
			String ROOT_DIR = "d:/uploads";
			
			String fileName = customerDto.getProfilePic().getOriginalFilename();
			String targetFile = ROOT_DIR +fileName;
			try {
				FileCopyUtils.copy(customerDto.getProfilePic().getInputStream()	, new FileOutputStream(targetFile));
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				//should return status indicating  failure
				e.printStackTrace();
			}
			
			customer.setProfilePic(fileName);
			customerService.register(customer);
			
			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Registration Successfull");
			return status;
		}
		catch(CustomerServiceException e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/login")
	public LoginStatus login(@RequestBody LoginDto loginDto) {
		try {
			Customer customer = customerService.login(loginDto.getEmail(), loginDto.getPassword());
			LoginStatus ls = new LoginStatus();
			ls.setStatus(StatusType.SUCCESS);
			ls.setMessage("Login Successfull!");
			ls.setCustomerId(customer.getId());
			ls.setName(customer.getName());
			return ls;
		}
		catch(CustomerServiceException e) {
			LoginStatus ls = new LoginStatus();
			ls.setStatus(StatusType.FAILURE);
			ls.setMessage(e.getMessage());
			return ls;
			
		}
		
	}
	
	public static class LoginStatus extends Status{
		private int customerId;
		private String name;
		public int getCustomerId() {
			return customerId;
		}
		public String getName() {
			return name;
		}
		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	public static class Status{
		
		private StatusType status;
		private String message;
		
		
		
		public StatusType getStatus() {
			return status;
		}



		public String getMessage() {
			return message;
		}



		public void setStatus(StatusType status) {
			this.status = status;
		}



		public void setMessage(String message) {
			this.message = message;
		}



		public static enum StatusType{
			SUCCESS, FAILURE;
		}
		
	}

}
