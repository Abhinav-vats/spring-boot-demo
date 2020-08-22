package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;


import com.lti.entity.Customer;


@Repository
public class CustomerRepositoryImp implements CustomerRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	
	@Transactional
	public void save(Customer customer) {
		// TODO Auto-generated method stub
		entityManager.merge(customer);

	}

	@Override
	public Customer findById(int id) {
		// TODO Auto-generated method stub
		return  entityManager.find(Customer.class, id);
	}

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return entityManager
				.createNamedQuery("fetch-all")
				.getResultList();
	}
	
	public boolean isCustomerAvailable(String email) {
		return (Long)
				entityManager.createQuery("select count(c.id) from Customer as c where c.email= :em")
				.setParameter("em", email)
				.getSingleResult() ==1 ? true:false;
	}

	@Override
	public int findByEmailAndPassword(String email, String password) {
		return (Integer) entityManager
				.createQuery("select c.id from Customer c where c.email =  :em and c.password= :pw")
				.setParameter("em", email)
				.setParameter("pw", password).getSingleResult();
	}

}
