package bugtracking.app.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bugtracking.app.entity.*;
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(value= "SELECT * FROM User u", nativeQuery = true)
	List<User> getAllUsers();
	User findByUserName(String userName);
	User findByUserId(Integer userId);	
}
