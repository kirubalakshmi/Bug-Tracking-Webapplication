package bugtracking.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the bugcomments database table.
 * 
 */
@Entity
@Table(name="bugcomments")
@NamedQuery(name="Bugcomment.findAll", query="SELECT b FROM Bugcomment b")
public class Bugcomment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private Timestamp created;

	@Lob
	@Column(nullable=false)
	private String notes;

	//bi-directional many-to-one association to Bug
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bug_id", nullable=false)
	private Bug bug;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="created_by", nullable=false)
	private User user;

	public Bugcomment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Bug getBug() {
		return this.bug;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}