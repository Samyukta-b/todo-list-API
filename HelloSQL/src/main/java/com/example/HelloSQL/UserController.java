package com.example.HelloSQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    UserRepository userRepository;

    @GetMapping("/all")
    public Iterable<UserModel> getAll() {
        return userService.getAll();
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String name,@RequestParam String email)
    {
        userService.addUser(name, email);
        return "Saved!";
    }

    @PostMapping("/updaten")
    public String updateUser(@RequestParam int id, @RequestParam String name, @RequestParam String email)
    {
        userService.updateUser(id, name, email);
        return "Saved!";
    }

    @DeleteMapping("/delete")
    public String delUser(@RequestParam int id) {
        userService.delete(id);
        return "User Deleted!";
    }
}
