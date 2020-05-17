package com.anouar.Services;

import com.anouar.Model.Customer;
import com.anouar.Model.Orders;
import com.anouar.Model.ShoppingCart;
import com.anouar.Model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerService {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Customer> AllCustomers(){
        List<Customer> customers = new ArrayList<Customer>();
        Session session = sessionFactory.openSession();
        Criteria cr = session.createCriteria(Customer.class);
        customers = cr.list();
        return customers;
    }

    public Customer CustomerById(int CustomId){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class,CustomId);
        session.getTransaction().commit();
        session.close();
        return customer;
    }

    public void addCust(Customer customer){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(customer);
        session.getTransaction().commit();
        session.close();
    }

    public void updateCustomer(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        customer.setName("ronaldo");
        session.update(customer);
        session.getTransaction().commit();
        session.close();
    }
    public void DelCust(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class,id);
        session.delete(customer);
        session.getTransaction().commit();
        session.close();
    }

    public List<Orders> AllOrdersOfCust(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class,id);
        Query query = session.createQuery("from Orders where customer = :customerid");
        query.setInteger("customerid",customer.getId());
        List<Orders> result = query.list();
        return result;
    }

    public List<Orders> getOrderOfCustomer(int id1, int id2){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class,id1);
        Orders orders = session.get(Orders.class,id2);
        Query query = session.createQuery("from Orders where customer = :customerid and id = :orderid");
        query.setInteger("customerid",customer.getId());
        query.setInteger("orderid",orders.getId());
        List<Orders> orders1 = query.list();
        return orders1;
    }

    public void addOrderToCustomer(int id,Orders orders){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class,id);
        orders.setCustomer(customer);
        customer.getListOrders().add(orders);
        session.update(customer);
        session.getTransaction().commit();
        session.close();
    }

    public void addShoppingCartToCustomer(int id, ShoppingCart shoppingCart){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class,id);
        shoppingCart.setCustomerr(customer);
        customer.getListShoppingCart().add(shoppingCart);
        session.update(customer);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteOrderOfCust(int id1,int id2){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class,id1);
        Orders orders = session.get(Orders.class,id2);
        Query query1 = session.createQuery("delete from OrderDetails where orders = :orderId ");
        query1.setInteger("orderId",orders.getId());
        query1.executeUpdate();
        Query query = session.createQuery("delete from Orders where customer = :customerid and id = :orderid");
        query.setInteger("customerid",customer.getId());
        query.setInteger("orderid",orders.getId());
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }


}
