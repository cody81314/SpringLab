package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.DemoService;
import com.example.demo.service.UserService;
import com.example.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserVO> getUsers() {
        return userService.getAllUser();
    }

    @GetMapping(path = "/{userSeq}")
    public UserVO getUser(@PathVariable("userSeq") int userSeq) {
        return userService.getUser(userSeq);
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PutMapping
    public void editUser(@RequestBody User user) {
        userService.updateUserByDomain(user);
    }

    @DeleteMapping(path = "/{userSeq}")
    public void deleteUser(@PathVariable("userSeq") int userSeq) {
        userService.deleteUser(userSeq);
    }
}
