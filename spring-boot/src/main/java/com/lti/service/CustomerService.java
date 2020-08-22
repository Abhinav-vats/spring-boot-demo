package com.lti.service;

import com.lti.entity.Customer;
import com.lti.exception.CustomerServiceException;

public interface CustomerService {

	void register(Customer customer) throws CustomerServiceException;

	Customer login(String email, String password);

}