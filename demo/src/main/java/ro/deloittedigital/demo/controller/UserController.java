package ro.deloittedigital.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ro.deloittedigital.demo.exception.UserNotFoundException;
import ro.deloittedigital.demo.model.domain.User;
import ro.deloittedigital.demo.model.dto.UserBasicInfoDTO;
import ro.deloittedigital.demo.model.dto.UserDTO;
import ro.deloittedigital.demo.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

//TODO: Get user profile: never return the password + return more infos if the user asks for his own profile - check the principal from Spring Security
                                                    //TODO: din Get(id) sa fie 2 metode apelate in service, in caz ca vrea profilul lui sa scoata ceva
                                                    //TODO: si daca vrea profilul altcuiva sa returneze mai putine informatii
//TODO: Login user - GET/POST with variables + check the variables with the database before logging him.


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
/*
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAvailableUsers(){
        return ResponseEntity.ok(userService.getAvailableUsers()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList()));
    }*/
    @GetMapping
    public ResponseEntity<List<UserBasicInfoDTO>> getAvailableUsers(){
        return ResponseEntity.ok(userService.getAvailableUsers()
            .stream()
            .map(user -> modelMapper.map(user, UserBasicInfoDTO.class))
            .collect(Collectors.toList()));
}

    @PostMapping("/add")
    public ResponseEntity<UserDTO> add(@RequestBody UserDTO userDTO){
        User user = modelMapper.map(userDTO, User.class);
        //String encodedPassword = passwordEncoder.encode(user.getPassword());
        //user.setPassword(encodedPassword);
        User savedUser = userService.add(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(savedUser, UserDTO.class));

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable("id") Long id, @RequestBody UserDTO userDTO){
        User foundUser = userService.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        userDTO.setId(foundUser.getId());
        User updatedUser = userService.update(modelMapper.map(userDTO, User.class));
        return ResponseEntity.ok(modelMapper.map(updatedUser, UserDTO.class));

    }

    @DeleteMapping("/{email}")
    public ResponseEntity<UserDTO> delete(@PathVariable("email") String email){
        User foundUser = userService.get(email).orElseThrow(() -> new UserNotFoundException());

        userService.delete(foundUser);
        return ResponseEntity.noContent().build();
    }



}
