package org.deegaem.imtusers.boundary;

import org.deegaem.imtusers.domain.User;
import org.deegaem.imtusers.service.UserServiceImpl;
import org.deegaem.imtusers.service.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/imt/users")
public class UsersResource {

    private UserServiceImpl service;

    private ModelMapper modelMapper;

    public UsersResource(UserServiceImpl service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = this.service.getUsers().stream().map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping({"/{userId}"})
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
        User user = this.service.getUserById(userId);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        User userRequest = modelMapper.map(userDTO, User.class);
        User user = this.service.insert(userRequest);
        UserDTO userResponse = modelMapper.map(user, UserDTO.class);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping({"/{userId}"})
    public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") Long userId, @RequestBody UserDTO userDTO) {
        User userRequest = modelMapper.map(userDTO, User.class);
        User user = this.service.updateUser(userId, userRequest);
        UserDTO userResponse = modelMapper.map(user, UserDTO.class);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping({"/{userId}"})
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("userId") Long userId) {
        this.service.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
