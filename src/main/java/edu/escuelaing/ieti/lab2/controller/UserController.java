package edu.escuelaing.ieti.lab2.controller;

import edu.escuelaing.ieti.lab2.dto.UserDto;
import edu.escuelaing.ieti.lab2.entities.User;
import edu.escuelaing.ieti.lab2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping( "/v1/user" )
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> listUsersDto = new ArrayList<>();
        List<User> listUsers =  userService.getAll();
        for (User usuarios : listUsers) {
           listUsersDto.add(userService.mapToDto(usuarios));
        }
        return new ResponseEntity<List<UserDto>>(listUsersDto, HttpStatus.OK);
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<UserDto> findById( @PathVariable String id ) {
        User user =  userService.findById(id);
        return new ResponseEntity<UserDto>(userService.mapToDto(user), HttpStatus.OK);
    }

    @GetMapping( "/findUsersWithNameOrLastNameLike/{queryparam}" )
    public ResponseEntity<List<UserDto>> findUsersWithNameOrLastNameLike( @PathVariable String queryparam ) {
        List<UserDto> listUsersDto = new ArrayList<>();
        List<User> user =  userService.findUsersWithNameOrLastNameLike(queryparam);
        for (User usuarios : user) {
            listUsersDto.add(userService.mapToDto(usuarios));
        }
        return new ResponseEntity<List<UserDto>>(listUsersDto, HttpStatus.OK);
    }

    @GetMapping( "/findUsersCreatedAfter/{startDate}" )
    public ResponseEntity<List<UserDto>> findUsersCreatedAfter( @PathVariable Date startDate ) {
        List<UserDto> listUsersDto = new ArrayList<>();
        List<User> user =  userService.findUsersCreatedAfter(startDate);
        for (User usuarios : user) {
            listUsersDto.add(userService.mapToDto(usuarios));
        }
        return new ResponseEntity<List<UserDto>>(listUsersDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> create( @RequestBody UserDto userDto ) {
        List<User> users = userService.getAll();
        String id = "1";
        if (users.size() > 0){
            id = String.valueOf((Integer.parseInt(users.get(users.size()-1).getId())+1));
        }
        User newUser = userService.create(new User(userDto, id, new Date()));
        return new ResponseEntity<UserDto>(userService.mapToDto(newUser), HttpStatus.CREATED);
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<UserDto> update( @RequestBody UserDto user, @PathVariable String id ) {
        User updateUser = new User(user, id, userService.findById(id).getCreatedAt());
        return new ResponseEntity<UserDto>(userService.mapToDto(userService.update(updateUser, id)), HttpStatus.CREATED);
    }

    @PostMapping( "/{id}" )
    @RolesAllowed("ADMIN")
    public ResponseEntity<Boolean> delete( @PathVariable String id ) {
        try {
            userService.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
