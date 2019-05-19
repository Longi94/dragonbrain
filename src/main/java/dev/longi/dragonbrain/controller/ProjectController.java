package dev.longi.dragonbrain.controller;

import dev.longi.dragonbrain.entity.Project;
import dev.longi.dragonbrain.repository.ProjectRepository;
import dev.longi.dragonbrain.service.AuthService;
import dev.longi.dragonbrain.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final ProjectService projectService;
    private final AuthService authService;

    public ProjectController(ProjectRepository projectRepository, ProjectService projectService, AuthService authService) {
        this.projectRepository = projectRepository;
        this.projectService = projectService;
        this.authService = authService;
    }

    @GetMapping("")
    public List<Project> getProjects() {
        return projectRepository.findAllByOrderByOrderBy();
    }

    @PostMapping("")
    public Project createProject(HttpServletRequest request, @RequestBody Project project) {
        authService.checkRequest(request);
        return projectService.createProject(project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(HttpServletRequest request, @PathVariable("id") Long id) {
        authService.checkRequest(request);
        projectService.deleteProject(id);
    }

    @PostMapping("/{id}/move")
    public void moveProject(HttpServletRequest request, @PathVariable("id") Long id, @RequestParam("up") boolean up) {
        authService.checkRequest(request);
        projectService.moveProject(id, up);
    }

    @PutMapping("/{id}")
    public Project editProject(HttpServletRequest request, @PathVariable("id") Long id, @RequestBody Project project) {
        authService.checkRequest(request);
        return projectService.updateProject(id, project);
    }

    @GetMapping("/{id}")
    public Project getProject(HttpServletRequest request, @PathVariable("id") Long id) {
        authService.checkRequest(request);
        return projectService.getProject(id);
    }
}
