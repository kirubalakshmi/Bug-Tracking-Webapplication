package bugtracking.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bugtracking.app.entity.*;


public interface BugCommentRepository extends JpaRepository<Bugcomment, Integer> {
	Bugcomment findById (Integer CommentID);
}
