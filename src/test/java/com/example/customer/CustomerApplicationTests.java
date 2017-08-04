package com.example.customer;

import com.example.customer.common.CustomerUtils;
import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.example.customer.common.CustomerUtils.createTestCustomer;
import static com.example.customer.common.CustomerUtils.findInList;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerApplicationTests {

	@Autowired
	CustomerRepository customerRepository;

	@Test
	public void testAddGet() {
		// Get unique names every time this test runs
		String firstName = Long.toString(System.currentTimeMillis());
		String lastName = Long.toString(System.currentTimeMillis());
		String email = "test@test.com";
		String phone = "404-404-4040";

		Customer person1 = new Customer();
		person1.setFirstName(firstName);
		person1.setLastName(lastName);
		person1.setEmail( email );
		person1.setPhone( phone );

		customerRepository.add(person1);

		List<Customer> person = customerRepository.get();

		Customer person2 = findInList(person, firstName, lastName, phone, email);
		assertNotNull(person2);

		Customer person3 = customerRepository.getById(person2.getId());
		assertNotNull(person3);
		Assert.assertEquals(firstName, person3.getFirstName());
		Assert.assertEquals(lastName, person3.getLastName());
	}

	@Test
	public void testUpdate() {
		Customer customer1 = createTestCustomer();
		customerRepository.add(customer1);

		List<Customer> person = customerRepository.get();

		Customer customer2 = findInList(person, customer1.getFirstName(), customer1.getLastName(), customer1.getPhone(), customer1.getEmail());
		assertNotNull(customer2);

		String updateFirstName = Long.toString(System.currentTimeMillis());
		String updateLastName = Long.toString(System.currentTimeMillis());
		String updateemail = "test@test.com";
		String updatephone = "404-404-4040";

		customer2.setFirstName(updateFirstName);
		customer2.setLastName(updateLastName);
		customer2.setPhone( updatephone );
		customer2.setEmail( updateemail );
		customerRepository.update(customer2);

		person = customerRepository.get();

		Customer customer3 = findInList(person, updateFirstName, updateLastName, updatephone, updateemail );
		assertNotNull(customer3);
		Assert.assertEquals(customer2.getId(), customer3.getId());
	}

	@Test
	public void testDelete() {
		Customer person1 = createTestCustomer();
		customerRepository.add(person1);

		List<Customer> person = customerRepository.get();

		Customer person2 = findInList(person, person1.getFirstName(), person1.getLastName(), person1.getPhone(), person1.getEmail());
		assertNotNull(person2);

		customerRepository.delete(person2.getId());

		person = customerRepository.get();
		Customer person3 = CustomerUtils.findInList(person, person1.getFirstName(), person1.getLastName(), person1.getPhone(), person1.getEmail());
		Assert.assertNull(person3);
	}

}
