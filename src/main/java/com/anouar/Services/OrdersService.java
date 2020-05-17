package com.anouar.Services;

import com.anouar.Model.Customer;
import com.anouar.Model.OrderDetails;
import com.anouar.Model.Orders;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;

@Repository
public class OrdersService {

    @Autowired
    SessionFactory sessionFactory;

    public List<Orders> AllOrders(){
        List<Orders> orders = new ArrayList<Orders>();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria cr = session.createCriteria(Orders.class);
        orders = cr.list();
        session.getTransaction().commit();
        session.close();
        return orders;
    }

    public Orders OrderById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Orders orders = session.get(Orders.class,id);
        session.getTransaction().commit();
        session.close();
        return orders;
    }

    public void addOrder(Orders orders){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(orders);
        session.getTransaction().commit();
        session.close();
    }

    public void addDetailsToOrder(OrderDetails orderDetails,int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Orders orders = session.get(Orders.class,id);
        orderDetails.setOrders(orders);
        session.save(orderDetails);
        session.save(orders);
        session.getTransaction().commit();
        session.close();
    }
}

