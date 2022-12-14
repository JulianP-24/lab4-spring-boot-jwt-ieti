package edu.escuelaing.ieti.lab2.entities;

import edu.escuelaing.ieti.lab2.dto.UserDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import edu.escuelaing.ieti.lab2.enums.RoleEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Document
public class User {
    @Id
    private String id;

    private String name;

    @Indexed( unique = true )
    private String email;

    private String lastName;

    private Date createdAt;

    private String passwordHash;

    private List<RoleEnum> roles = new ArrayList<>();

    public User(UserDto userDto, String id, Date createdAt){
        this.id = id;
        this.name = userDto.getName();
        this.email = userDto.getEmail();
        this.lastName = userDto.getLastName();
        this.createdAt = createdAt;
        this.passwordHash = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
        addRole(RoleEnum.USER);
    }

    public User(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public List<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEnum> roles) {
        this.roles = roles;
    }

    public void addRole( RoleEnum roleEnum ){
        if( !roles.contains( roleEnum)) roles.add(roleEnum);
    }
}
