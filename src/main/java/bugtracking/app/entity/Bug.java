package bugtracking.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the bug database table.
 * 
 */
@Entity
@Table(name="bug")
@NamedQuery(name="Bug.findAll", query="SELECT b FROM Bug b")
public class Bug implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="bug_id", unique=true, nullable=false, length=8)
	private String bugId;

	@Column(name="bug_description", length=1000)
	private String bugDescription;

	@Column(name="bug_type", nullable=false, length=20)
	private String bugType;

	@Column(name="date_found", nullable=false)
	private Timestamp dateFound;

	@Column(nullable=false, length=50)
	private String location;

	@Column(nullable=false, length=10)
	private String serverity;

	@Column(nullable=false, length=1)
	private String status;

	@Column(name="version_id", length=6)
	private String versionId;

	//bi-directional many-to-one association to Project
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="project", nullable=false)
	private Project projectBean;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="found_by", nullable=false)
	private User foundBy;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="assigned_to", nullable=false)
	private User assignedTo;

	//bi-directional many-to-one association to Bugcomment
	@OneToMany(mappedBy="bug")
	private List<Bugcomment> bugcomments;

	public Bug() {
	}

	public String getBugId() {
		return this.bugId;
	}

	public void setBugId(String bugId) {
		this.bugId = bugId;
	}

	public String getBugDescription() {
		return this.bugDescription;
	}

	public void setBugDescription(String bugDescription) {
		this.bugDescription = bugDescription;
	}

	public String getBugType() {
		return this.bugType;
	}

	public void setBugType(String bugType) {
		this.bugType = bugType;
	}

	public Timestamp getDateFound() {
		return this.dateFound;
	}

	public void setDateFound(Timestamp dateFound) {
		this.dateFound = dateFound;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getServerity() {
		return this.serverity;
	}

	public void setServerity(String serverity) {
		this.serverity = serverity;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVersionId() {
		return this.versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public Project getProjectBean() {
		return this.projectBean;
	}

	public void setProjectBean(Project projectBean) {
		this.projectBean = projectBean;
	}

	public User getFoundBy() {
		return this.foundBy;
	}

	public void setFoundBy(User user1) {
		this.foundBy = user1;
	}

	public User getAssignedTo() {
		return this.assignedTo;
	}

	public void setAssignedTo(User user2) {
		this.assignedTo = user2;
	}

	public List<Bugcomment> getBugcomments() {
		return this.bugcomments;
	}

	public void setBugcomments(List<Bugcomment> bugcomments) {
		this.bugcomments = bugcomments;
	}

	public Bugcomment addBugcomment(Bugcomment bugcomment) {
		getBugcomments().add(bugcomment);
		bugcomment.setBug(this);

		return bugcomment;
	}

	public Bugcomment removeBugcomment(Bugcomment bugcomment) {
		getBugcomments().remove(bugcomment);
		bugcomment.setBug(null);

		return bugcomment;
	}

}