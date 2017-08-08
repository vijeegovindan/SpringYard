package com.example.customer.service;

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
import static com.example.customer.repository.CustomerRepository.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

}
