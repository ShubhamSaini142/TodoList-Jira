package com.jira.todolist.todlistbackend.Services;

import com.jira.todolist.todlistbackend.Repositories.taskRepository;
import com.jira.todolist.todlistbackend.beans.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<?> updateStatus(Long taskID,String status){
      Tasks task = taskRepository.findById(taskID).orElse(null);
      if(task == null){
          return ResponseEntity.badRequest().body("Tasks with this ID not FOUND");
      }
      task.setTaskStatus(status);
      taskRepository.save(task);
        return ResponseEntity.ok("Status has been updated");


    }
}
