package com.example.customer.common;

import com.example.customer.model.Customer;
import java.util.List;
import java.util.Random;

public class CustomerUtils {

    private static Random random = new Random();

    public static Customer createTestCustomer(){

        String firstName = Long.toString( System.currentTimeMillis() );
        String lastName = Long.toString( System.currentTimeMillis() );
        String phone = "404-404-4040";
        String email = "test@test.com";


        Customer customer = new Customer();
        customer.setFirstName( firstName );
        customer.setLastName( lastName );
        customer.setPhone( phone );
        customer.setEmail( email );

        return customer;
    }


    public static Customer findInList(List<Customer> compareCustomer, String first, String last, String phone, String email){
        for(Customer customer: compareCustomer ){
            if(customer.getFirstName().equals( first ) && customer.getLastName().equals( last )){
                return customer;
            }
        }
        return null;
    }


}
