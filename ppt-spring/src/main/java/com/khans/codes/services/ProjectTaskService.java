package com.khans.codes.services;

import com.khans.codes.model.ProjectTask;
import com.khans.codes.repositories.BackLogRepository;
import com.khans.codes.repositories.ProjectRepository;
import com.khans.codes.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectTaskRepository projectTaskRepository;

    @Autowired
    BackLogRepository backLogRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){

        //PTs to be added to specific Project which means project!=null and BL exist
        //Set the BL to PT

        return null;
    }
}
