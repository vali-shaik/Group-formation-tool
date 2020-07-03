package dal.asd.catme.accesscontrol;

import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.courses.Course;
import dal.asd.catme.courses.IListCourseService;
import dal.asd.catme.util.CatmeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("admin/")
public class AdminController
{

    IAdminService adminServiceImpl;

    IListCourseService listCourseService;
    IListUserService listUserService;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping("dashboard")
    public String adminPage()
    {
        logger.info("****Admin Controller - Admin Page Invoked*****");
        return CatmeUtil.ADMIN;
    }

    @RequestMapping("addCourse")
    public String addCourse(Model model)
    {
        logger.info("****Admin Controller - Add Course Invoked*****");
        model.addAttribute("course", new Course());
        return CatmeUtil.ADD_COURSE;
    }

    @PostMapping("addCourseToDatabase")
    public String addCourseToDatabase(@ModelAttribute Course course)
    {
        logger.info("****Admin Controller - Add Course to Database Invoked*****");
        adminServiceImpl = SystemConfig.instance().getAdminServie();
        int result = adminServiceImpl.addCourse(course);
        if (result == 1)
        {
            return CatmeUtil.SUCCESS_PAGE;
        } else if (result > 1)
        {
            return CatmeUtil.USER_PAGE;
        } else
        {
            return CatmeUtil.ERROR;
        }
    }

    @ModelAttribute("courses")
    public List<Course> getCourseList()
    {
        listCourseService = SystemConfig.instance().getListCourseService();
        return listCourseService.getAllCourses();
    }

    @ModelAttribute("users")
    public List<User> getUsersList()
    {
        Course course = new Course();
        listUserService = SystemConfig.instance().getListUserService();
        return listUserService.getUsers(course);
    }

    @PostMapping(value = "deleteCourse")
    public String deleteCourse(@RequestParam String course)
    {
        logger.info("****Admin Controller - Delete Course Invoked*****");
        adminServiceImpl = SystemConfig.instance().getAdminServie();
        int result = adminServiceImpl.deleteCourse(course);
        if (result > 0)
        {
            return CatmeUtil.SUCCESS_PAGE;
        } else
        {
            return CatmeUtil.ERROR;
        }
    }

    @PostMapping("addInstructor")
    public String addInstructor(@RequestParam String course, @RequestParam String user)
    {
        logger.info("****Admin Controller - Add Instructor Invoked*****");
        adminServiceImpl = SystemConfig.instance().getAdminServie();
        int result = adminServiceImpl.addInstructorToCourse(user, course);
        if (result == 1)
        {
            return CatmeUtil.SUCCESS_PAGE;
        } else if (result > 1)
        {
            return CatmeUtil.USER_PAGE;
        } else
        {
            return CatmeUtil.ERROR;
        }
    }
}
