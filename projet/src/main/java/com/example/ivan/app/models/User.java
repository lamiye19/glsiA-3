package com.example.ivan.app.models;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String password;

    @NotNull
    private String nom;

    @NotNull
    private String prenom;

    private int active;

    private int blocked;

    @Column(nullable = false, unique = true)
    private String role = "";

    @NotNull
    private String permission = "";



    public User(String username, String password, String nom, String prenom, String role, String permission){
        this.username = username;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.active = 1;
        this.blocked  = 0;
        this.role = role;
        this.permission = permission;
    }

    protected User(){
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getActive() {
        return active;
    }

    public int getBlocked() {
        return blocked;
    }

    public String getRole() {
        return role;
    }

    public String getPermission() {
        return permission;
    }

    public List<String> getRoleList() {
        if(this.role.length() > 0) {
            return Arrays.asList(this.role.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList() {
        if(this.permission.length() > 0) {
            return Arrays.asList(this.permission.split(","));
        }
        return new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setBlocked(int blocked) {
        this.blocked = blocked;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
