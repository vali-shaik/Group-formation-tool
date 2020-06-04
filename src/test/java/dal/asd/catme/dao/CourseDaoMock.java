package dal.asd.catme.dao;
import java.sql.Connection;
import java.util.List;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Student;
import dal.asd.catme.beans.User;
import dal.asd.catme.dao.ICourseDao;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.util.CatmeUtil;

public class CourseDaoMock implements ICourseDao
{
	List<Course> listOfCourses;
	
	public CourseDaoMock(List<Course> listOfCourses)
	{
		this.listOfCourses=listOfCourses;
	}
	
	@Override
	public List<Course> getCourses(String role) throws CatmeException 
	{
		if(role.equals(CatmeUtil.GUEST_ROLE))
		{
			//listOfCourses=formCourses();	
		}
		else
		{
			listOfCourses=null;
		}
		return listOfCourses;
	}

	@Override
	public Course displayCourseById(String courseId) throws CatmeException 
	{
		Course course=new Course();
		for(Course c:listOfCourses)
		{
			if(c.getCourseId().equals(courseId))
			{
				course=c;
			}
		}
			return course;
	}

	@Override
	public String findRoleByCourse(User user, String courseId) throws CatmeException 
	{
		String role="";
		
		if(user.getBannerId().equals("B00835822") && courseId.equals("5409"))
		{
			role=CatmeUtil.STUDENT_ROLE;
		}
		if(user.getBannerId().equals("B00835822") && courseId.equals("5308"))
		{
			role=CatmeUtil.TA_ROLE;
		}
		if(user.getBannerId().equals("B00835822") && courseId.equals("5306"))
		{
			role=CatmeUtil.GUEST_ROLE;
		}
		return role;
	}

	@Override
	public List<Student> getRegisteredStudents(String courseId)
	{
		return null;
	}

	@Override
	public int checkCourseRegistration(String bannerId, int courseId, Connection con)
	{
		return 0;
	}


}
