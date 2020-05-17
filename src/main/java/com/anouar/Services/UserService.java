package com.anouar.Services;

import com.anouar.Model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserService {

    @Autowired
    private SessionFactory sessionFactory;

    public List<User> AllUsers(){
        List<User> users= new ArrayList<User>();
        Session session = sessionFactory.openSession();
        Criteria cr = session.createCriteria(User.class);
        users = cr.list();
        return users;
    }

    public User UserById(int Usid){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class,Usid);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public void add(String email,String pass){
        User user = new User();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        user.setEmail(email);
        user.setPassword(pass);
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    public void updateuser(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class,id);
        user.setEmail("email");
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteUser(int userid){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class,userid);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }
}
