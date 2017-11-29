package bugtracking.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import bugtracking.app.entity.*;
import bugtracking.app.entity.Bug;
import bugtracking.app.entity.User;
import bugtracking.app.services.*;


/**
 * @author Kiruba
 */
@Controller
public class BugListController {
	@Autowired
    private BugServices bugservices;

    @Autowired
    private UserServices userservices;

    @Autowired
    private ProjectServices projectServices;
    
//    @RequestMapping(value = "/bugs", method = RequestMethod.GET)
//    protected ModelAndView doGet(@RequestParam(value = "project_id", required = false) Integer projectId,
//                                 @RequestParam(value = "showclosed", required = false) Boolean showClosed,
//                                 @RequestParam(value = "priority", required = false) Boolean prioritySort) {
//        List<Bug> bugs = null;
//        Integer users = null;
//        List<Project> projects = null;
//        try {
//            bugs = bugservices.getBugs(projectId, showClosed, prioritySort, null);
//            for (Bug bug : bugs) {
//                int project = Project.getProjectId();
//                int responsible = User.getUserId(bug.getFoundBy());
//                bug.getServerity();
//                bug.getStatus();
//            }
//            users = User.getUserId(users);
//            int project = Project.getProjectId();
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//        }
//
//        ModelMap model = new ModelMap();
//        model.addAttribute("bugs", bugs);
//        model.addAttribute("users", users);
//        model.addAttribute("projects", projects);
//
//        return new ModelAndView("bugs", model);
//    }

    @RequestMapping(value = "/bugs/add", method = RequestMethod.GET)
    public ModelAndView addBugForm() {
        ModelMap model = new ModelMap();
        model.addAttribute("users", userservices.getAllUsers());
        model.addAttribute("projects", projectServices.getAllProjects());
        model.addAttribute("Serverity", BugPriority.values());
        model.addAttribute("types", BugType.values());
        model.addAttribute("statuses", BugStatus.values());

        return new ModelAndView("add_bug", model);
    }

    @RequestMapping(value = "/bugs/add", method = RequestMethod.POST)
    protected String doPut(@RequestParam(value = "Serverity", defaultValue = "MAJOR") BugPriority serverity,
                           @RequestParam(value = "location") String location,
                           @RequestParam(value = "description") String description,
                           @RequestParam(value = "responsible_id") Integer responsibleId,
                           @RequestParam(value = "project_id") Integer projectId,
                           @RequestParam(value = "issue_type") String issueType) {
        int creatorId = userservices.getCurrentUser().getUserId();
        if (responsibleId == 0) {
            responsibleId = userservices.getCurrentUser().getUserId();
        }
        try {
            Bug bug = new Bug();
            bug.setServerity(serverity.name());
            bug.setLocation(location);
            bug.setBugDescription(description);
            bug.setAssignedTo(userservices.getUser(responsibleId));
            bug.setBugType(issueType);
            bugservices.saveBug(bug);
           
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return "redirect:/bugs?project_id=" + projectId;
    }
}
