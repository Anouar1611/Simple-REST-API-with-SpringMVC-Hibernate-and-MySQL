package com.anouar.Controllers;

import com.anouar.Model.Customer;
import com.anouar.Model.Orders;
import com.anouar.Model.ShoppingCart;
import com.anouar.Model.User;
import com.anouar.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerService.AllCustomers();
    }

    @GetMapping(value = "/customer/{idCust}/{idOrd}")
    public List<Orders> getOrderOfCustomerById(@PathVariable("idCust") int CustId,@PathVariable("idOrd") int OrdId){
        return customerService.getOrderOfCustomer(CustId,OrdId);
    }

    @GetMapping(value = "/customerOr/{id}")
    public List<Orders> getAllOrdersOfCust(@PathVariable("id") int Id){return customerService.AllOrdersOfCust(Id);}


    @GetMapping(value = "/customer/{id}")
    public Customer getCustomerById(@PathVariable("id") int CustId){
        return customerService.CustomerById(CustId);
    }

    @PostMapping(value = "/addorder/{id}")
    public void AddOrderToCust(@PathVariable("id") int custid,@RequestBody Orders orders){
        customerService.addOrderToCustomer(custid,orders);
    }

    @DeleteMapping(value = "/deleteorder/{id}")
    public void DelOrderOfCustomer(@PathVariable("id") int Cusid){
        customerService.DelCust(Cusid);
    }

    @PostMapping(value = "/addshoppingcart/{id}")
    public void AddShoppingCartToCust(@PathVariable("id") int custid,@RequestBody ShoppingCart shoppingCart){
        customerService.addShoppingCartToCustomer(custid,shoppingCart);
    }

    @PostMapping(value = "/addcustomer")
    public void addCustomer(@RequestBody Customer customer) { customerService.addCust(customer); }

    @PatchMapping(value = "/updateCustomer/{id}")
    public void updateCust(@PathVariable("id") int Id){
        customerService.updateCustomer(Id);
    }

    @DeleteMapping(value = "/deletecustomer/{id}")
    public void DelCustomer(@PathVariable("id") int Cusid){
            customerService.DelCust(Cusid);
    }

    @DeleteMapping(value = "/deleteOrOfCustomer/{idC}/{idO}")
    public void DeleteOrderOfCust(@PathVariable("idC") int custId,@PathVariable("idO") int ordId){
        customerService.deleteOrderOfCust(custId,ordId);
    }

}
