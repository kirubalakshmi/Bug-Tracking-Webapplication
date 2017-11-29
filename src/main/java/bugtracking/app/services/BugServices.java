package bugtracking.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bugtracking.app.entity.Bug;
import bugtracking.app.repository.BugRepository;
@Service
@Transactional
public class BugServices {
	
		
		    @Autowired
		    private BugRepository bugRepository;

		    public List<Bug> getAllBug() {
		        return bugRepository.findAll();
		    }

		    public void saveBug(Bug bug) {
		    	bugRepository.save(bug);
		    }
		    public Bug getBugId(String bugid) {
		    	return bugRepository.findByBugId(bugid);
		    }
		
			public void updateBug(Bug bug) {
				bugRepository.save(bug);
			}

		


}
