package net.continuumsecurity.webapp.controller;

import net.continuumsecurity.Constants;
import net.continuumsecurity.dao.SearchException;
import net.continuumsecurity.model.User;
import net.continuumsecurity.service.RoleManager;
import net.continuumsecurity.service.UserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/makeadmin")
public class MakeAdminController {
    private UserManager userManager = null;
	private RoleManager roleManager;

	@Autowired
	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}
	
    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView handleRequest(@RequestParam(required = true, value = "username") String username) throws Exception {
        Model model = new ExtendedModelMap();
        try {
        	User user = userManager.getUserByUsername(username);
        	user.addRole(roleManager.getRole("ROLE_ADMIN"));
        	userManager.saveUser(user);
            model.addAttribute(Constants.USERNAME, username);
        } catch (SearchException se) {
            model.addAttribute("error", se.getMessage());
        }
        return new ModelAndView("admin/makeadmin", model.asMap());
    }
}