package dal.asd.catme.accesscontrol;

import java.util.List;

import dal.asd.catme.config.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dal.asd.catme.config.CatmeSecurityConfig;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.util.CatmeUtil;

@Controller
@RequestMapping("/")
public class CatmeController
{
    IUserService userService;

    CatmeSecurityConfig catmeServiceConfig;

    private static final Logger log = LoggerFactory.getLogger(CatmeController.class);

    @RequestMapping("admin")
    public String adminPage()
    {

        return CatmeUtil.ADMIN_PAGE;
    }

    @GetMapping("register")
    public String signupPage(Model model)
    {
        model.addAttribute("user", new User());
        return CatmeUtil.SIGNUP_PAGE;
    }

    @RequestMapping("signup")
    public String signupPage(@ModelAttribute User user, Model model)
    {
        userService = AccessControlAbstractFactoryImpl.instance().getUserService();
        String message = userService.addUser(user);
        model.addAttribute("message", message);
        return CatmeUtil.SIGNEDUP_PAGE;
    }

    @RequestMapping("access")
    public String findLandingPage() throws CatmeException
    {
        log.info("Finding the landing page of application based on access level");

        String page = "";

        catmeServiceConfig = SystemConfig.instance().getCatmeServiceConfig();
        List<String> roles = catmeServiceConfig.fetchRolesHomePage();

        if (roles != null)
        {
            if (roles.contains("ROLE_ADMIN"))
            {
                log.info("User idetified as Admin");
                return "redirect:/" + CatmeUtil.ADMIN_HOME;
            }
            if (roles.contains("ROLE_INSTRUCTOR"))
            {
                log.info("User idetified as Instructor");
                return "redirect:/" + CatmeUtil.INSTRUCTOR_HOME;
            }
            if (roles.contains("ROLE_TA"))
            {
                log.info("User idetified as TA");
                return "redirect:/" + CatmeUtil.TA_HOME;
            }
            if (roles.contains("ROLE_STUDENT"))
            {
                log.info("User idetified as Student");
                return "redirect:/" + CatmeUtil.STUDENT_HOME;
            }
            if (roles.contains("ROLE_GUEST"))
            {
                log.info("User idetified as Guest");
                return "redirect:/" + CatmeUtil.GUEST_HOME;
            }
        }
        if (page.trim().length() == 0)
        {
            page = "error";
            throw new CatmeException("User Role is not found in Database");
        }
        return page;
    }

    @RequestMapping("login")
    public String loginPage()
    {
        log.info("Redirected to Login page");
        return CatmeUtil.LOGIN_PAGE;
    }
}
