package bugtracking.app.services;

import bugtracking.app.entity.Bugcomment;
import bugtracking.app.repository.BugCommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class BugCommentServices {
	
			    @Autowired
			    private BugCommentRepository bugcommentRepository;

			    public List<Bugcomment> getAllBugcomment() {
			        return bugcommentRepository.findAll();
			    }

			    public void saveBugcomment(Bugcomment BugComment) {
			    	bugcommentRepository.save(BugComment);
			    }

				public List<Bugcomment> getComments(int userId) {
					// TODO Auto-generated method stub
					return null;
				}			
}
