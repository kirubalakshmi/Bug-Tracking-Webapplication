package bugtracking.app.services;
import bugtracking.app.entity.Project;
import bugtracking.app.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ProjectServices {
				@Autowired
			    private ProjectRepository projectRepository;

			    @Transactional(readOnly = true)
			    public List<Project> getAllProjects() {
			        return projectRepository.findAll();
			    }

				public void addProject(Project project) {
			    	projectRepository.save(project);					
				}
				public void deleteProject(String projectname) {
					Project project = projectRepository.findByProjectName(projectname);
					projectRepository.delete(project);					
				}

}
