package com.vabsssss.repository;

import com.vabsssss.models.Task;
import com.vabsssss.models.TempModel;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dheeraj.mittal on 25/02/17.
 */
public interface TaskRepository  extends CrudRepository<Task, Integer> {
    TempModel findByEntityId(String entityId);
}
