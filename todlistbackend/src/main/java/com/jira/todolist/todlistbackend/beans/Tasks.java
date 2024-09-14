package com.jira.todolist.todlistbackend.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long taskId;
    private String taskName;
    private String taskDetails;
    private String taskStatus = "Todo";
}
