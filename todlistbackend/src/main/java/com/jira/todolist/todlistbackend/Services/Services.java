package com.jira.todolist.todlistbackend.Services;

import com.jira.todolist.todlistbackend.Repositories.taskRepository;
import com.jira.todolist.todlistbackend.beans.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Services {


    @Autowired
    private taskRepository taskRepository;

    public Tasks SaveTask(Tasks tasks){
        return taskRepository.save(tasks);
    }

    public List<Tasks> GetTasks(){
        return taskRepository.findAll();
    }
}
