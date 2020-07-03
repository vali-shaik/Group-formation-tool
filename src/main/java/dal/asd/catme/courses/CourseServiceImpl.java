package dal.asd.catme.courses;

import dal.asd.catme.accesscontrol.CatmeController;
import dal.asd.catme.accesscontrol.Student;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.exception.CatmeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CourseServiceImpl implements ICourseService
{
    //Creating Logger
    private static final Logger log = LoggerFactory.getLogger(CatmeController.class);

    public CourseServiceImpl(ICourseDao courseDao)
    {
        this.courseDao = courseDao;
    }

    public CourseServiceImpl()
    {

    }

    ICourseDao courseDao;

    @Override
    public List<Course> getCourses(String role) throws CatmeException
    {
        log.info("Calliing course dao for getting all course");

        return courseDao.getCourses(role);
    }

    @Override
    public Course displayCourseById(String courseId) throws CatmeException
    {
        log.info("Calliing course dao for displaying course based on course id");
        return courseDao.displayCourseById(courseId);
    }

    @Override
    public String findRoleByCourse(User user, String courseId) throws CatmeException
    {
        log.info("Calliing course dao for finding role based on course");
        return courseDao.findRoleByCourse(user, courseId);
    }

    @Override
    public List<Student> getEnrolledStudents(String courseId)
    {
        return courseDao.getRegisteredStudents(courseId);
    }


}
