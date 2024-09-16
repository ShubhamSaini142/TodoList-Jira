package com.jira.todolist.todlistbackend.Services;

import com.jira.todolist.todlistbackend.Repositories.taskRepository;
import com.jira.todolist.todlistbackend.Repositories.userRepository;
import com.jira.todolist.todlistbackend.beans.Tasks;
import com.jira.todolist.todlistbackend.beans.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Services {


    @Autowired
    private taskRepository taskRepository;

    @Autowired
    private  UserService userService;

    @Autowired
    private userRepository userRep;

    public Tasks SaveTask(Tasks tasks , Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Users user = userRep.findByUsername(username).orElse(null);
        if(user == null){
            throw new UsernameNotFoundException("Not found");
        }

        tasks.setUser(user);
        return taskRepository.save(tasks);
    }

    public List<Tasks> GetTasks(Authentication authentication){
        String username = authentication.getName();
        Users user = userRep.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return taskRepository.findByUser(user);
    }

    public ResponseEntity<?> updateStatus(Long taskID,String status,Authentication authentication){
      Tasks task = taskRepository.findById(taskID).orElse(null);
      if(task == null){
          return ResponseEntity.badRequest().body("Tasks with this ID not FOUND");
      }
      task.setTaskStatus(status);
      taskRepository.save(task);
        return ResponseEntity.ok("Status has been updated");


    }
}
