package com.jira.todolist.todlistbackend.Controller;

import com.jira.todolist.todlistbackend.Services.Services;
import com.jira.todolist.todlistbackend.beans.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private Services taskservice;

    @PostMapping("/create")
    private ResponseEntity<?> CreateTasks(@RequestBody Tasks tasks) {
        if (!"TODO".equals(tasks.getTaskStatus()) && !"INPROGRESS".equals(tasks.getTaskStatus())
                && !"UNDERREVIEW".equals(tasks.getTaskStatus()) && !"COMPLETED".equals(tasks.getTaskStatus())) {
            return ResponseEntity.badRequest().body("Please use Correct Status");
        }
        if(tasks.getTaskName().isEmpty() && tasks.getTaskDetails().isEmpty()){
            return ResponseEntity.badRequest().body("Please Fill the Data");
        }
        taskservice.SaveTask(tasks);
        return new ResponseEntity<String>("TASKS HAS BEEN CREATED", HttpStatus.OK);

    }

    @GetMapping("/getAll")
    private List<Tasks> GetAllCreatedTasks() {
        return taskservice.GetTasks();
    }

    @PatchMapping("/update/{id}")
    private ResponseEntity<?> UpdateStatus(@PathVariable Long id, @RequestBody Tasks tasks) {
        if (!"TODO".equals(tasks.getTaskStatus()) && !"IN_PROGRESS".equals(tasks.getTaskStatus())
                && !"UNDER_REVIEW".equals(tasks.getTaskStatus()) && !"DONE".equals(tasks.getTaskStatus())) {
            return ResponseEntity.badRequest().body("Please use Correct Status");
        }
      return new ResponseEntity<>(taskservice.updateStatus(id,tasks.getTaskStatus()),HttpStatus.OK);


    }


}
