package com.jira.todolist.todlistbackend.Repositories;

import com.jira.todolist.todlistbackend.beans.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface taskRepository extends JpaRepository<Tasks,Long> {
}
