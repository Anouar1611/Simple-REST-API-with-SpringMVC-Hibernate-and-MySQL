package com.anouar.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Customer extends User {

    private String name;
    private String address;

    @JsonManagedReference//
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL /*,fetch = FetchType.EAGER*/)
    private List<Orders> listOrders = new ArrayList<>();

    @JsonManagedReference//@JsonBackReference
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "customerr",cascade = CascadeType.ALL /*,fetch = FetchType.EAGER*/)
    private List<ShoppingCart> listShoppingCart= new ArrayList<>();

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", listOrders=" + listOrders +
                ", listShoppingCart=" + listShoppingCart +
                '}';
    }

    public Customer(String name, String address, List<Orders> listOrder, List<ShoppingCart> listShoppingCart) {
        this.name = name;
        this.address = address;
        this.listOrders = listOrder;
        this.listShoppingCart = listShoppingCart;
    }

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Orders> getListOrders() {
        return listOrders;
    }

    public void setListOrders(List<Orders> listOrders) {
        this.listOrders = listOrders;
    }

    public List<ShoppingCart> getListShoppingCart() {
        return listShoppingCart;
    }

    public void setListShoppingCart(List<ShoppingCart> listShoppingCart) {
        this.listShoppingCart = listShoppingCart;
    }
}

