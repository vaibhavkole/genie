package com.vabsssss.repository;

import com.vabsssss.models.TaskIdShipmentMapping;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by dheeraj.mittal on 25/02/17.
 */
public interface TaskIdShipmentMappingRepository  extends CrudRepository<TaskIdShipmentMapping, Integer> {
    List<TaskIdShipmentMapping> findByTaskId(Integer taskId);
}
