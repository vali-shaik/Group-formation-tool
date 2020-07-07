package dal.asd.catme.accesscontrol;

import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.courses.Course;

import org.springframework.stereotype.Component;

@Component
public class AdminServiceImpl implements IAdminService
{
    IAdminDao admin;

    @Override
    public int addCourse(Course course)
    {
        admin = SystemConfig.instance().getAdminDao();
        return admin.addCourse(course);
    }

    @Override
    public int deleteCourse(String courseId)
    {
        admin = SystemConfig.instance().getAdminDao();
        return admin.deleteCourse(courseId);
    }


    @Override
    public int addInstructorToCourse(String user, String course)
    {
        admin = SystemConfig.instance().getAdminDao();
        return admin.addInstructorToCourse(user, course);

    }
}
