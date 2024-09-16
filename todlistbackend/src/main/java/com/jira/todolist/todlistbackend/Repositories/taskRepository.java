package com.jira.todolist.todlistbackend.Repositories;

import com.jira.todolist.todlistbackend.beans.Tasks;
import com.jira.todolist.todlistbackend.beans.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface taskRepository extends JpaRepository<Tasks,Long> {
    List<Tasks> findByUser(Users user);
}
