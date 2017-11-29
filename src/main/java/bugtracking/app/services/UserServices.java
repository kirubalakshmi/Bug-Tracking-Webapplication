package bugtracking.app.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bugtracking.app.entity.*;
import bugtracking.app.repository.UserRepository;
@Service
@Repository
@Transactional
public class UserServices {
	
	    @Autowired
	    private static UserRepository userRepository;

	    public List<User> getAllUsers() {
	    	System.out.println("test:"+userRepository);
	        return userRepository.getAllUsers();
	    }

	    public void addUser(User user) {
	        userRepository.save(user);
	    }

	    public  User getCurrentUser( ) {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if (authentication == null) {
	            throw new IllegalStateException("No authentication in current context");
	        }

	        return userRepository.findByUserName(authentication.getName());
	    }

		public User getUser(Integer userId) {
	        return userRepository.findByUserId(userId);
		}

		public void deleteUser(String userName) {
			User user = userRepository.findByUserName(userName);
			userRepository.delete(user);
			
		}

		public void updateUser(User user) {
			userRepository.save(user);
		}
	

}
