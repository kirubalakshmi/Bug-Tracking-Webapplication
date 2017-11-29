package bugtracking.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import bugtracking.app.entity.*;
import bugtracking.app.entity.Bug;
import bugtracking.app.services.BugCommentServices;
import bugtracking.app.services.BugServices;
import bugtracking.app.services.ProjectServices;
import bugtracking.app.services.UserServices;

import java.util.List;

/**
 * @author Kiruba
  */
@Controller
public class IndexController {

	@Autowired
    private BugServices bugservices;

    @Autowired
    private BugCommentServices bugcommentservices;

    @Autowired
    private UserServices userservices;

    @Autowired
    private ProjectServices projecservices;

    @RequestMapping(value = {"/index", "/"})
    public ModelAndView getIndex() {

        ModelMap model = new ModelMap();
        List<Bugcomment> comments = bugcommentservices.getComments(userservices.getCurrentUser().getUserId());
//        for (Bugcomment comment : comments) {
//            Integer author = userservices.getUser(comment.getCreatedBy());
//            String bugid = null;
//			String bug = bugservices.getBugId(bugid);
//            comment.setCreatedBy(author);;
//            comment.setBugId();
//        }
        model.addAttribute("comments", comments);
       
		
        return new ModelAndView("index", model);
    }
}
