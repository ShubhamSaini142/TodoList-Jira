package com.jira.todolist.todlistbackend.Controller;

import com.jira.todolist.todlistbackend.Services.Services;
import com.jira.todolist.todlistbackend.beans.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private Services taskservice;
    @PostMapping("/create")
    private ResponseEntity<?> CreateTasks(@RequestBody Tasks tasks , Authentication authentication){
        if (!"TODO".equals(tasks.getTaskStatus()) && !"PROGRESS".equals(tasks.getTaskStatus())
                && !"UNDER-REVIEW".equals(tasks.getTaskStatus()) && !"COMPLETED".equals(tasks.getTaskStatus())) {
            return ResponseEntity.badRequest().body("Please use Correct Status");
        }
        taskservice.SaveTask(tasks , authentication);
        return new ResponseEntity<String>("TASKS HAS BEEN CREATED", HttpStatus.CREATED);

    }

    @GetMapping("/getAll")
    private List<Tasks> GetAllCreatedTasks(Authentication authentication){
       return taskservice.GetTasks(authentication);
    }

    @PatchMapping("/update/{id}")
    private ResponseEntity<?> UpdateStatus(@PathVariable Long id,@RequestBody Tasks tasks,Authentication authentication){
        if (!"TODO".equals(tasks.getTaskStatus()) && !"PROGRESS".equals(tasks.getTaskStatus())
                && !"UNDER-REVIEW".equals(tasks.getTaskStatus()) && !"COMPLETED".equals(tasks.getTaskStatus())) {
            return ResponseEntity.badRequest().body("Please use Correct Status");
        }
      return new ResponseEntity<>(taskservice.updateStatus(id,tasks.getTaskStatus(),authentication),HttpStatus.OK);


    }

}
