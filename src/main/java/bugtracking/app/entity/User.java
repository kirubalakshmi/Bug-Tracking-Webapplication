package bugtracking.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	private int userId;
	
	@Column(name="email")
	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;
	
	@Column(name="password")
	private String password;

	@Column(name="username")
	private String userName;

	//bi-directional many-to-one association to Bug
	@OneToMany(mappedBy="foundBy")
	private List<Bug> bugs1;

	//bi-directional many-to-one association to Bug
	@OneToMany(mappedBy="assignedTo")
	private List<Bug> bugs2;

	//bi-directional many-to-one association to Bugcomment
	@OneToMany(mappedBy="user")
	private List<Bugcomment> bugcomments;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="user")
	private List<Project> projects;

	public User() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Bug> getBugs1() {
		return this.bugs1;
	}

	public void setBugs1(List<Bug> bugs1) {
		this.bugs1 = bugs1;
	}

	public Bug addBugs1(Bug bugs1) {
		getBugs1().add(bugs1);
		bugs1.setFoundBy(this);

		return bugs1;
	}

	public Bug removeBugs1(Bug bugs1) {
		getBugs1().remove(bugs1);
		bugs1.setFoundBy(null);

		return bugs1;
	}

	public List<Bug> getBugs2() {
		return this.bugs2;
	}

	public void setBugs2(List<Bug> bugs2) {
		this.bugs2 = bugs2;
	}

	public Bug addBugs2(Bug bugs2) {
		getBugs2().add(bugs2);
		bugs2.setAssignedTo(this);

		return bugs2;
	}

	public Bug removeBugs2(Bug bugs2) {
		getBugs2().remove(bugs2);
		bugs2.setAssignedTo(null);

		return bugs2;
	}

	public List<Bugcomment> getBugcomments() {
		return this.bugcomments;
	}

	public void setBugcomments(List<Bugcomment> bugcomments) {
		this.bugcomments = bugcomments;
	}

	public Bugcomment addBugcomment(Bugcomment bugcomment) {
		getBugcomments().add(bugcomment);
		bugcomment.setUser(this);

		return bugcomment;
	}

	public Bugcomment removeBugcomment(Bugcomment bugcomment) {
		getBugcomments().remove(bugcomment);
		bugcomment.setUser(null);

		return bugcomment;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setUser(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setUser(null);

		return project;
	}

}