package com.khans.codes.web;

import com.khans.codes.errors.ProjectValidationError;
import com.khans.codes.model.Project;
import com.khans.codes.services.ProjectService;
import com.khans.codes.services.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
@Slf4j
@CrossOrigin
public class ProjectController {

    private static final String DELETE_PROJECT_MESSAGE = "Project with ID: '%s' was deleted";

    @Autowired
    ProjectService projectService;

    @Autowired
    ValidationService validationService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
        Optional<ProjectValidationError> errors = validationService.validate(result);
        if(errors.isPresent()){
            return new ResponseEntity<ProjectValidationError>(errors.get(),HttpStatus.BAD_REQUEST);
        }
        projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId){
        Project project = projectService.findProjectByIdentifier(projectId);
        return new ResponseEntity<Project>(project,HttpStatus.OK);
    }

    @GetMapping("")
    public Iterable<Project> getProjects(){
        return projectService.findAllProject();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId){
        projectService.deleteProjectByIdentifier(projectId);
        return  new ResponseEntity<String>(String.format(DELETE_PROJECT_MESSAGE,projectId), HttpStatus.OK);
    }
}
