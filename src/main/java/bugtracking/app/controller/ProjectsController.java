package bugtracking.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import bugtracking.app.entity.User;
import bugtracking.app.entity.Project;
import bugtracking.app.services.BugCommentServices;
import bugtracking.app.services.BugServices;
import bugtracking.app.services.ProjectServices;
import bugtracking.app.services.UserServices;

import java.util.List;

/**
 * @author Kiruba
 */
@Controller
public class ProjectsController {

	@Autowired
    private UserServices userservices;

    @Autowired
    private ProjectServices projecservices;
    
    @RequestMapping(value = "/projects")
    protected ModelAndView doGet() {
        List<Project> projects = null;
        List<User> users = null;
        try {
            projects = projecservices.getAllProjects();
            users = userservices.getAllUsers();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        ModelMap model = new ModelMap();
        model.addAttribute("projects", projects);
        model.addAttribute("users", users);

        return new ModelAndView("projects", model);
    }

    @RequestMapping(value = "/projects/add", method = RequestMethod.POST)
    protected String doPut(@RequestParam(value = "name") String name,
                           @RequestParam(value = "description", required = false) String description,
                           @RequestParam(value = "manager_id", required = false) int managerId,
                           RedirectAttributes attributes) {

        Project project = new Project();//null, name, description, managerId, null);
        project.setProjectName(name);
        project.setDescription(description);
        User manager = userservices.getUser(managerId);
        project.setUser(manager);
        String message;
        try {
        	projecservices.addProject(project);
            message = "Project " + name + " successfully created";
        } catch (DataAccessException e) {
            message = "Error creating project " + name;
            e.printStackTrace();
        }
        attributes.addFlashAttribute("message", message);

        return "redirect:/projects";
    }


    @RequestMapping(value = "/projects/delete", method = RequestMethod.POST)
    protected String doDelete(@RequestParam(value = "name") String name,
                              RedirectAttributes attributes) {
        String message;

        try {
        	projecservices.deleteProject(name);
            message = "Project " + name + " successfully deleted";
        } catch (DataAccessException e) {
            message = "Error deleting project " + name;
            e.printStackTrace();
        }
        attributes.addFlashAttribute("message", message);

        return "redirect:/projects";
    }
}
