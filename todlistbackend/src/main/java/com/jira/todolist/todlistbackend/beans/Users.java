package com.jira.todolist.todlistbackend.beans;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "users")
@Data // Lombok annotation to generate getters, setters, toString, etc.
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;
    @OneToMany(mappedBy = "user")
    private Set<Tasks> tasks;
}
