package com.anouar.Controllers;

import com.anouar.Model.OrderDetails;
import com.anouar.Model.Orders;
import com.anouar.Services.OrdersService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @GetMapping("/orders")
    public List<Orders> getAllOrders(){ return ordersService.AllOrders(); }

    @GetMapping("/order/{id}")
    public Orders getOrderById(@PathVariable("id") int OrId){ return ordersService.OrderById(OrId); }

    @PostMapping(value = "/addorder")
    public void addOrd(@RequestBody Orders orders){ ordersService.addOrder(orders);}

    @PostMapping(value = "/DetailsToOrder/{id}")
    public void addDetailsToOrder(@RequestBody OrderDetails orderDetails,@PathVariable("id") int OrdId){
        ordersService.addDetailsToOrder(orderDetails,OrdId);
    }

}
