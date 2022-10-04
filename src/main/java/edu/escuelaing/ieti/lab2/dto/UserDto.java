package edu.escuelaing.ieti.lab2.dto;

import edu.escuelaing.ieti.lab2.enums.RoleEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String email;
    private String lastName;
    private Date createdAt;
    private String password;
    private List<RoleEnum> roles;

    public UserDto(String userName, String password, String userEmail, String userLastName){
        this.name = userName;
        this.password = password;
        this.email = userEmail;
        this.lastName = userLastName;
    }

    public UserDto(){}

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

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public List<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEnum> roles) {
        this.roles = roles;
    }

}
