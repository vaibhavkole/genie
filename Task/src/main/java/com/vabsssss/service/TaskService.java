package com.vabsssss.service;

import com.vabsssss.dto.CreateSheetModel;
import com.vabsssss.models.Task;
import com.vabsssss.models.TaskIdShipmentMapping;
import com.vabsssss.repository.TaskIdShipmentMappingRepository;
import com.vabsssss.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by dheeraj.mittal on 25/02/17.
 */

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;


    @Autowired
    TaskIdShipmentMappingRepository taskIdShipmentMappingRepository;

    public Task createTripSheet(CreateSheetModel createSheetModel) {
        Task task = new Task();
        task.setLocationId(createSheetModel.getLocationId());
        task.setTaskDescription(createSheetModel.getTaskDescription());
        task.setDate(new Date());
        task.setActive(true);
        task = taskRepository.save(task);
        createShipmentRunsheetMapping(task,createSheetModel);
        return task;
    }

    private void createShipmentRunsheetMapping(Task task, CreateSheetModel createSheetModel) {
        TaskIdShipmentMapping taskIdShipmentMapping;
        for(Integer shipmentId : createSheetModel.getShipmentIds()) {
            taskIdShipmentMapping = new TaskIdShipmentMapping();
            taskIdShipmentMapping.setShipmentId(shipmentId);
            taskIdShipmentMapping.setTaskId(task.getId());
            taskIdShipmentMapping.setActive(true);
            taskIdShipmentMappingRepository.save(taskIdShipmentMapping);
        }
    }

}
