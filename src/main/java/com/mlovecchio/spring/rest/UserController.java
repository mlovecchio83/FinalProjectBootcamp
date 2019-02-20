package com.mlovecchio.spring.rest;

import com.mlovecchio.spring.Payload.GameRequest;
import com.mlovecchio.spring.Payload.UserRequest;
import com.mlovecchio.spring.dao.UserRepository;
import com.mlovecchio.spring.exceptions.ResourceException;
import com.mlovecchio.spring.model.Game;
import com.mlovecchio.spring.model.User;
import com.mlovecchio.spring.service.UserService;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;


import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path="/users")
public class UserController {
    @Autowired
    private UserService userService;
    /*@GetMapping(path="/addUser")*/
    /*public @ResponseBody String addNewUser(@Valid @RequestParam String name, @Valid @RequestParam String email,
                                           @Valid @RequestParam int dni){
        userService.save(id);
        return "Saved";
    }*/

    @PostMapping(path="/addUser")
    public @ResponseBody void addNewUser(@RequestBody UserRequest userRequest) {
        userService.saveUser(userRequest);

    }

    @GetMapping("/findAllUsers")
    public @ResponseBody List   <User> getAllUsers(){
        return  userService.findAll();
    }

    /*@PostMapping("/postAddUser")
    public void createUser( UserRequest userRequest){
        User user = new User();
        user.setName(userRequest.getName());
        user.setActive(true);
        userService.save(user);
        throw new ResourceException(HttpStatus.OK, "User saved successfuly.");
    }*/

    @GetMapping(path="/findUser/{id}")
    public @ResponseBody User findById(@PathVariable("id") int id){
        return userService.findById(id);
    }

    @PostMapping(path = "/findUserByName")
    public @ResponseBody
    User findUserByName(@RequestBody UserRequest userRequest) {
        return userService.findUserByName(userRequest.getName());
    }

    @DeleteMapping (path="/deleteUser/{id}")
    public @ResponseBody String deleteById(@PathVariable("id") int id){
        if(userService.validateUserExists(id)) {
            userService.deleteById(id);
            return "User deleted";
        }
        return "User doesn't exists.";
    }

    @PostMapping(path = "/updateUser")
    public @ResponseBody String updateUser(@RequestBody User user){
        if(userService.validateUserExists(user.getId())){
            user.setActive(true);
            userService.save(user);
            return "User updated";
        }
        return "There is no user with that ID.";
    }


}
