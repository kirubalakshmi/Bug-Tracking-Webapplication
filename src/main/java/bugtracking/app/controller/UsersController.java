package bugtracking.app.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import bugtracking.app.exception.*;
import bugtracking.app.entity.User;
import bugtracking.app.services.BugCommentServices;
import bugtracking.app.services.BugServices;
import bugtracking.app.services.ProjectServices;
import bugtracking.app.services.UserServices;

import java.util.List;

/**
 * @author Kiruba
 */
@Controller
public class UsersController {
	@Autowired
    private BugServices bugservices;

    @Autowired
    private BugCommentServices bugcommentservices;

    @Autowired
    private UserServices userservices;

    @Autowired
    private ProjectServices projecservices;
    @Autowired
    private PasswordEncoder passwordEncoder;

   

    @RequestMapping(value = "/users/delete", method = RequestMethod.POST)
    protected String doDelete(@RequestParam(value = "username") String username,
                              RedirectAttributes attributes) {
        String message;

        try {
        	userservices.deleteUser(username);
            message = "Users " + username + " successfully deleted";
        } catch (DataAccessException e) {
            message = "Error deleting user " + username;
            e.printStackTrace();
        }
        attributes.addFlashAttribute("message", message);

        return "redirect:/users";
    }

    @RequestMapping(value = "/users/update/{id}", method = RequestMethod.POST)
    protected String updateUser(@RequestParam(value = "first_name", required = false) String firstname,
    							@RequestParam(value = "last_name", required = false) String lastname,
                                @RequestParam(value = "email", required = false) String email,
                                @RequestParam(value = "password") String password,
                                @RequestParam(value = "admin", required = false) Boolean admin,
                                @RequestParam(value = "backUrl", required = false, defaultValue = "/") String backUrl,
                                @PathVariable("id") Integer userId) {

        String encodedPassword = StringUtils.isNotBlank(password) ? passwordEncoder.encode(password) : null;
        User user = new User();
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        
        try {
        	userservices.addUser(user);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return "redirect:" + backUrl;
    }

    @RequestMapping(value = "/users/update/{id}", method = RequestMethod.GET)
    protected ModelAndView showUser(@PathVariable("id") Integer userId,
                                    @RequestParam(value = "backUrl", required = false) String backUrl) {
        ModelMap mm = new ModelMap();
        try {
            User user = userservices.getUser(userId);
            mm.addAttribute("user", user);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new ResourceNotFoundException();
        }
        if (StringUtils.isNotBlank(backUrl)) {
            mm.addAttribute("backUrl", backUrl);
        }

        return new ModelAndView("user", mm);
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public ModelAndView me() {
        return new ModelAndView("user", "user", userservices.getCurrentUser());
    }

    @RequestMapping(value = "/me", method = RequestMethod.POST)
    protected String updateMe(@RequestParam(value = "first_name", required = false) String firstname,
    						  @RequestParam(value = "last_name", required = false) String lastname,
                              @RequestParam(value = "email", required = false) String email,
                              @RequestParam(value = "password") String password,
                              @RequestParam(value = "admin", required = false) Boolean admin,
                              @RequestParam(value = "backUrl", required = false, defaultValue = "/") String backUrl) {
        User user = userservices.getCurrentUser();
        return updateUser(firstname, lastname,email, password, admin, backUrl, user.getUserId());
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    protected String doPut(@RequestParam(value = "username") String username,
                           @RequestParam(value = "first_name", required = false) String firstname,
                           @RequestParam(value = "last_name", required = false) String lastname,
                           @RequestParam(value = "email", required = false) String email,
                           @RequestParam(value = "admin") Boolean isAdmin,
                           @RequestParam(value = "password") String password,
                           RedirectAttributes attributes) {

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstname);
        user.setUserName(username);
        user.setLastName(lastname);
        user.setPassword(encodedPassword);
        String message;
        try {
        	userservices.updateUser(user);
            message = "User " + username + " successfully created";
        } catch (DataAccessException e) {
            message = "Error creating user " + username;
            e.printStackTrace();
        }
        attributes.addFlashAttribute("message", message);

        return "redirect:/users";
    }

    @RequestMapping(value = "/users")
    protected ModelAndView doGet() {
        List<User> users = null;
        try {
            users = userservices.getAllUsers();
            
            for(User u:users){
            	System.out.println("The list of users are:"+u.getUserId());
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return new ModelAndView("users", "users", users);
    }
}
