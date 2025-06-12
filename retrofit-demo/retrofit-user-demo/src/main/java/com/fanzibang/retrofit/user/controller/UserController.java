package com.fanzibang.retrofit.user.controller;

import com.fanzibang.retrofit.user.entity.User;
import com.fanzibang.retrofit.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/users")
    public List<User> listUsers(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return userService.listUsers(pageNo, pageSize);
    }

    @PostMapping("/user")
    public Boolean addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/user/{userId}")
    public void updateUser(@PathVariable("userId") String userId, String sex, String mobile) {
        userService.updateUser(userId, sex, mobile);
    }

}
