package bugtracking.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import bugtracking.app.services.*;
import bugtracking.app.entity.*;
import bugtracking.app.exception.ResourceNotFoundException;
import bugtracking.app.utils.MailService;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Kiruba
 */
@Controller
public class BugController {

    @Autowired
    private BugServices bugservices;

    @Autowired
    private BugCommentServices bugcommentservices;

    @Autowired
    private UserServices userservices;

//    @Autowired
//    private MailService mailService;

    @RequestMapping(value = "/bug/{id}", method = RequestMethod.POST)
    protected ModelAndView doPost(@PathVariable("id") String bugId,
                                  @RequestParam(value = "Notes") String body,
                                  ModelMap model)  {

    	Bugcomment comment = new Bugcomment();
        comment.setUser(userservices.getCurrentUser());
        comment.setNotes(body);
        comment.setBug(bugservices.getBugId(bugId));
        try {
        	bugcommentservices.saveBugcomment(comment);
//            Bug bug = bugDao.getBug(bugId);
//            bug.sendMessages(mailService, "Notification message: project " + bug.getProjectName(),
//                    "Added comment to bug #" + bug.getId() + " " + bug.getTitle());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return doGet(model, bugId);
    }

    @RequestMapping(value = "/bug/{id}", method = RequestMethod.GET)
    protected ModelAndView doGet(ModelMap model,
                                 @PathVariable("id") String bugId) {

        try {
            Bug bug = bugservices.getBugId(bugId);
            if (bug == null) {
                throw new ResourceNotFoundException();
            }
            bug.getAssignedTo();
            bug.getFoundBy();
            bug.getProjectBean();
            model.addAttribute("bug", bug);
            model.addAttribute("statuses", BugStatus.values());
            model.addAttribute("users", userservices.getAllUsers());
            model.addAttribute("bugs", null);
            model.addAttribute("BugComments", bug.getBugcomments());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return new ModelAndView("bug", model);
    }

    @RequestMapping(value = "/bug/{id}/edit", method = RequestMethod.POST)
    protected String doClose(HttpServletResponse response,
                             @PathVariable("bug_id") String id,
                             @RequestParam(value = "status") BugStatus status,
                             @RequestParam(value = "serverity") String serverity,
                             @RequestParam(value = "assigned_to") Integer assignedto) throws IOException {
        String bugId;
        int responsibleId;
        try {
            bugId = String.valueOf(id);
            responsibleId = assignedto;
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        try {
        	Bug bug = bugservices.getBugId(id);
        	User user = userservices.getUser(assignedto);
        	bug.setStatus(status.name());
        	bug.setServerity(serverity);
        	bug.setAssignedTo(user);
            bugservices.updateBug(bug);
            
                      
        } catch (DataAccessException dat) {
            dat.printStackTrace();
        }
        return "redirect:/bugs";
    }
}
