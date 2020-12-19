package com.khans.codes.services;

import com.khans.codes.exceptions.ProjectIdException;
import com.khans.codes.model.Backlog;
import com.khans.codes.model.Project;
import com.khans.codes.repositories.BackLogRepository;
import com.khans.codes.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private static final String PROJECT_EXIST_MESSAGE= "Project ID: '%s' already exist!";
    private static final String PROJECT_DOES_NOT_EXIST_MESSAGE= "Project ID: '%s' dose not exist!";
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BackLogRepository backLogRepository;

    public Project saveOrUpdateProject(Project project){
        try{
            String projectIdentifier = project.getProjectIdentifier().toUpperCase();
            project.setProjectIdentifier(projectIdentifier);
            if(project.getId() == null){
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(projectIdentifier);
            }else{
                project.setBacklog(backLogRepository.findByProjectIdentifier(projectIdentifier));
            }
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException(String.format(PROJECT_EXIST_MESSAGE,project.getProjectIdentifier()));
        }
    }

    public Project findProjectByIdentifier(String projectIdentifier){
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if(project == null){
            throw new ProjectIdException(String.format(PROJECT_DOES_NOT_EXIST_MESSAGE,projectIdentifier));
        }
        return project;
    }

    public Iterable<Project> findAllProject(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectIdentifier){
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if(project == null){
            throw new ProjectIdException(String.format(PROJECT_DOES_NOT_EXIST_MESSAGE,projectIdentifier));
        }
        projectRepository.delete(project);
    }

}
