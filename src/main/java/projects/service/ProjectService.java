package projects.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import projects.exception.DbException;
import projects.dao.ProjectDao;
import projects.entity.Project;

public class ProjectService {

	private ProjectDao projectDao = new ProjectDao(); 
	
	public Project addProject(Project project) {

	   return projectDao.insertProject(project); 
	
	}

	public List<Project> fetchAllProjects() {
		// @formatter:off
		return projectDao.fetchAllProjects()
			.stream() 
			.sorted((r1, r2) -> r1.getProjectId() - r2.getProjectId())
			.collect(Collectors.toList());
		// @formatter:on
	}

	public Project fetchProjectById(Integer projectId) {
		return projectDao.fetchProjectById(projectId).orElseThrow(
				() -> new NoSuchElementException("Project with project ID=" + projectId + " does not exist.")); 
	}

	public void modifyProjectDetails(Project project) {
		if(!projectDao.modifyProjectDetails(project)) {
			throw new DbException("Project with ID=" + project.getProjectId() + " does not exist.");
		} 
		
	}

	public void deleteProject(Integer projectId) {
		if(!projectDao.deleteProject(projectId)) {
			throw new DbException("Project with ID=" + projectId + " does not exist.");
		}
		
	}

}
