package org.deegaem.imtusers.boundary;

import org.deegaem.imtusers.domain.User;
import org.deegaem.imtusers.service.UserServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UsersResource {

    UserServiceImpl service;

    public UsersResource(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = this.service.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping({"/{userId}"})
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        return new ResponseEntity<>(this.service.getUserById(userId), HttpStatus.OK);
    }

    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User user1 = this.service.insert(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("user", "/api/v1/user/" + user1.getId().toString());
        return new ResponseEntity<>(user1, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/{userId}"})
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId, @RequestBody User user) {
        this.service.updateUser(userId, user);
        return new ResponseEntity<>(this.service.getUserById(userId), HttpStatus.OK);
    }

    @DeleteMapping({"/{userId}"})
    public ResponseEntity<User> deleteUser(@PathVariable("userId") Long userId) {
        this.service.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
