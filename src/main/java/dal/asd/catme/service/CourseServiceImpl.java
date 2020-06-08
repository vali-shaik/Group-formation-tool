package dal.asd.catme.service;

import java.util.List;

import dal.asd.catme.beans.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.controller.CatmeController;
import dal.asd.catme.dao.ICourseDao;
import dal.asd.catme.exception.CatmeException;

@Component
public class CourseServiceImpl implements ICourseService
{
	//Creating Logger
	private static final Logger log = LoggerFactory.getLogger(CatmeController.class);

	public CourseServiceImpl(ICourseDao courseDao)
	{
		this.courseDao=courseDao;
	}
	public CourseServiceImpl()
	{
		
	}
	
	ICourseDao courseDao;
	
	@Override
	public List<Course> getCourses(String role) throws CatmeException 
	{
		log.info("Calliing course dao for getting all course");
		courseDao=SystemConfig.instance().getCourseDao();
		return courseDao.getCourses(role);
	}
	@Override
	public Course displayCourseById(String courseId) throws CatmeException 
	{
		log.info("Calliing course dao for displaying course based on course id");
		courseDao=SystemConfig.instance().getCourseDao();
		return courseDao.displayCourseById(courseId);
	}
	@Override
	public String findRoleByCourse(User user,String courseId) throws CatmeException 
	{
		log.info("Calliing course dao for finding role based on course");
		courseDao=SystemConfig.instance().getCourseDao();
		return courseDao.findRoleByCourse(user,courseId);
	}

	@Override
	public List<Student> getEnrolledStudents(String courseId)
	{
		courseDao=SystemConfig.instance().getCourseDao();
		return courseDao.getRegisteredStudents(courseId);
	}


}
