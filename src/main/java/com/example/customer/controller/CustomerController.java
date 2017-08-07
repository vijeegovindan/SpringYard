package com.example.customer.controller;

import com.example.customer.model.Customer;
import com.example.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    public CustomerController() {
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/customers")
    public String listCustomers(Model model) {
        List<Customer> listOfCustomers = customerService.get();
        model.addAttribute( "customers", listOfCustomers );
        return "list_customers";
    }

    @RequestMapping(path="/customers/add")
    public String addCustomer(){
        return "add_customer";
    }

    @RequestMapping("/addCustomer")
    public ModelAndView addCustomerPost(@RequestParam(value = "firstname") String firstName,
                              @RequestParam(value = "lastname") String lastName,
                              @RequestParam(value = "email") String email,
                              @RequestParam(value = "phone") String phone, Model model){
        Customer customer = new Customer();
        customer.setFirstName( firstName );
        customer.setLastName( lastName );
        customer.setPhone( phone );
        customer.setEmail( email );
        customerService.add(customer);
        return new ModelAndView("redirect:/customers");
    }

    @RequestMapping("/customers/{id}")
    public String getById(@PathVariable("id") Integer id, Model model) {
        Customer customerById = customerService.getById( id );
        model.addAttribute( "customer", customerById);
        return "display_customer";
    }

    /**** FUTURE USE
    @RequestMapping("/customers/delete/{id}")
    public String deleteById(@PathVariable("id") Integer id, Model model) {
        customerService.delete( id );
        return "delete";
    }

    @RequestMapping("/customers/update/{id}")
    public String updateById(@PathVariable("id") Integer id, @RequestParam(value = "firstName") String firstName,
                             @RequestParam(value = "lastName") String lastName,
                             @RequestParam(value = "email") String email,
                             @RequestParam(value = "phone") String phone) {

        Customer updateCustomer = new Customer();
        updateCustomer.setFirstName( firstName );
        updateCustomer.setLastName( lastName );
        updateCustomer.setEmail( email );
        updateCustomer.setPhone( phone );

        customerService.update( updateCustomer );
        return "update";
    }
    **********/
}
