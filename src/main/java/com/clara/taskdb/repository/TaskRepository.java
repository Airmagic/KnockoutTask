package com.clara.taskdb.repository;

import com.clara.taskdb.model.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;



    @Repository
    public interface TaskRepository extends PagingAndSortingRepository<Task, Long>{
        List<Task> findAllByOrderByUrgentDesc();

        @Transactional
        @Modifying
        @Query("UPDATE Task t set t.completed = ?1 where t.id = ?2")
        int setTaskCompleted(boolean comleted, long id);
    }

