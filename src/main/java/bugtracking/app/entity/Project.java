package bugtracking.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the project database table.
 * 
 */
@Entity
@Table(name="project")
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="project_id")
	private int projectId;

	@Column(name="description")
	private String description;

	@Column(name="project_name")
	private String projectName;

	//bi-directional many-to-one association to Bug
	@OneToMany(mappedBy="projectBean")
	private List<Bug> bugs;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="created_by")
	private User user;

	public Project() {
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<Bug> getBugs() {
		return this.bugs;
	}

	public void setBugs(List<Bug> bugs) {
		this.bugs = bugs;
	}

	public Bug addBug(Bug bug) {
		getBugs().add(bug);
		bug.setProjectBean(this);

		return bug;
	}

	public Bug removeBug(Bug bug) {
		getBugs().remove(bug);
		bug.setProjectBean(null);

		return bug;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}