package org.deegaem.imtusers.boundary;

import org.deegaem.imtusers.domain.User;
import org.deegaem.imtusers.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UsersResource {

    @Autowired
    UserServiceImpl service;

    //public UsersResource(UserServiceImpl service) {
      //  this.service = service;
    //}

    //The function receives a GET request, processes it and gives back a list of Todo as a response.
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = this.service.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //The function receives a GET request, processes it, and gives back a list of Todo as a response.
    @GetMapping({"/{userId}"})
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        return new ResponseEntity<>(this.service.getUserById(userId), HttpStatus.OK);
    }

    //The function receives a POST request, processes it, creates a new Todo and saves it to the database, and returns a resource link to the created user.    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User user1 = this.service.insert(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("user", "/api/v1/user/" + user1.getId().toString());
        return new ResponseEntity<>(user1, httpHeaders, HttpStatus.CREATED);
    }

    //The function receives a PUT request, updates the Todo with the specified Id and returns the updated Todo
    @PutMapping({"/{userId}"})
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId, @RequestBody User user) {
        this.service.updateUser(userId, user);
        return new ResponseEntity<>(this.service.getUserById(userId), HttpStatus.OK);
    }

    //The function receives a DELETE request, deletes the Todo with the specified Id.
    @DeleteMapping({"/{userId}"})
    public ResponseEntity<User> deleteUser(@PathVariable("userId") Long userId) {
        this.service.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
