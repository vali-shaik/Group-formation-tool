package dal.asd.catme.courses;

import dal.asd.catme.accesscontrol.CatmeController;
import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.exception.CatmeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CourseServiceImpl implements ICourseService
{
    private static final Logger log = LoggerFactory.getLogger(CatmeController.class);

    ICourseDao courseDao;

    public CourseServiceImpl(ICourseDao courseDao)
    {
        this.courseDao = courseDao;
    }

    @Override
    public List<ICourse> getCourses(String role) throws CatmeException
    {
        log.info("Calling course dao for getting all course");

        return courseDao.getCourses(role);
    }

    @Override
    public List<ICourse> getAllCourses()
    {
        return courseDao.getAllCourses();
    }

    @Override
    public ICourse displayCourseById(String courseId) throws CatmeException
    {
        log.info("Calling course dao for displaying course based on course id");
        return courseDao.displayCourseById(courseId);
    }

    @Override
    public String findRoleByCourse(IUser user, String courseId) throws CatmeException
    {
        log.info("Calling course dao for finding role based on course");
        return courseDao.findRoleByCourse(user, courseId);
    }

    @Override
    public List<IUser> getEnrolledStudents(String courseId)
    {
        return courseDao.getRegisteredStudents(courseId);
    }

}
