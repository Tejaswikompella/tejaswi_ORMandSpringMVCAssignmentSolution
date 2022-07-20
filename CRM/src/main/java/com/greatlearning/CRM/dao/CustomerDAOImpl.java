package com.greatlearning.CRM.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.greatlearning.CRM.entity.Customer;

@Repository
@Transactional
@EnableTransactionManagement
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Customer> getCustomers() {
		
				
		Session currentSession = sessionFactory.getCurrentSession();
				
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by firstName", Customer.class);
 				
		List<Customer> customers = theQuery.getResultList();
		
				
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

				
		Session currentSession = sessionFactory.getCurrentSession();
		
				
		currentSession.saveOrUpdate(theCustomer);
		
		
	}

	@Override
	public Customer getCustomer(int theId) {
				
		Session currentSession = sessionFactory.getCurrentSession();
		
				
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}
	@Override
	public void deleteCustomer(int theId) {
		
				
		Session currentSession = sessionFactory.getCurrentSession();
		
				
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();
		
	}

}
