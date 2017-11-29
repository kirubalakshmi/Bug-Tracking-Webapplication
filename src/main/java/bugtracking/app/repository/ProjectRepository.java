package bugtracking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bugtracking.app.entity.*;
@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{
	Project findByProjectName(String projectName);
	Project findByUser(Integer creator_id);
	}
