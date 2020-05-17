package com.anouar.Controllers;


import com.anouar.Model.User;
import com.anouar.Services.UserService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public List<User> getAllUsers(){
        return userService.AllUsers();
    }

    @GetMapping(value = "/user/{id}")
    public User getUser(@PathVariable("id") int Userid){
        return userService.UserById(Userid);
    }

    @PostMapping(value = "/adduser")
    public void addUser(@RequestParam(value ="email") String email, @RequestParam(value = "password") String pass) {
        userService.add(email, pass);
    }

    @PatchMapping(value = "/updateUser/{id}")
    public void update(@PathVariable("id") int UserId){
        userService.updateuser(UserId);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public void deleteUser(@PathVariable("id") int useid){
       userService.deleteUser(useid);
    }
}
