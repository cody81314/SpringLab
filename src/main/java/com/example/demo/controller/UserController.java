package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.DemoService;
import com.example.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    private DemoService demoService;

    @RequestMapping(method = GET)
    public List<UserVO> getUsers() {
        return demoService.getAllUser();
    }

    @RequestMapping(path = "/{userSeq}", method = GET)
    public UserVO getUser(@PathVariable("userSeq") int userSeq) {
        return demoService.getUser(userSeq);
    }

    @RequestMapping(method = POST)
    public void addUser(@RequestBody User user) {
        demoService.addUser(user);
    }

    @RequestMapping(method = PUT)
    public void editUser(@RequestBody User user) {
        demoService.updateUserByDomain(user);
    }

    @RequestMapping(path = "/{userSeq}", method = DELETE)
    public void deleteUser(@PathVariable("userSeq") int userSeq) {
        demoService.deleteUser(userSeq);
    }
}
