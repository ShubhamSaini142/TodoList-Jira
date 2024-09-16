package com.jira.todolist.todlistbackend.beans;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long taskId;
    private String taskName;
    private String taskDetails;
    private String taskStatus = "Todo";
    @ManyToOne
    private Users user;

}
