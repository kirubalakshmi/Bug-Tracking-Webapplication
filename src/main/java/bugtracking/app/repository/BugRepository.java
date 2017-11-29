package bugtracking.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bugtracking.app.entity.Bug;

@Repository
@Transactional
public interface BugRepository extends JpaRepository<Bug, Integer> {
	Bug findByBugId(String Budid);
}
