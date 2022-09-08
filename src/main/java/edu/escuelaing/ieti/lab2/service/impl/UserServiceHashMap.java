package edu.escuelaing.ieti.lab2.service.impl;


import edu.escuelaing.ieti.lab2.dto.UserDto;
import edu.escuelaing.ieti.lab2.entities.User;
import edu.escuelaing.ieti.lab2.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;



public class UserServiceHashMap implements UserService {
    private final Map<String, User> usuarios = new HashMap<>();

    public UserServiceHashMap(){
        usuarios.put("1", new User(new UserDto("Julian","Julian@gmail.com","pena"), "1", new Date()));
    }

    @Override
    public User create(User user) {
        return usuarios.put(user.getId(), user);
    }

    @Override
    public User findById(String id) {
        return usuarios.get(id);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<User>(usuarios.values());
    }

    @Override
    public void deleteById(String id) {
        try {
            usuarios.remove(id);
        } catch (Exception e){
            new Exception("El usuario con id: " + id + "no existe",e);
        }
    }

    @Override
    public User update(User user, String userId) {
        return usuarios.put(userId, user);
    }

    @Override
    public UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setLastName(user.getLastName());
        userDto.setCreatedAt(user.getCreatedAt());
        return userDto;
    }

    @Override
    public User mapToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setLastName(userDto.getLastName());
        user.setCreatedAt(userDto.getCreatedAt());
        return user;
    }

    @Override
    public List<User> findUsersWithNameOrLastNameLike(String queryText) {
        return null;
    }

    @Override
    public List<User> findUsersCreatedAfter(Date startDate) {
        return null;
    }
}
